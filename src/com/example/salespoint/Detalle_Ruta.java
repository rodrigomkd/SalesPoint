package com.example.salespoint;

import com.example.entidades.Ruta;
import com.example.sql.SQLiteQuery;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Detalle_Ruta extends Activity {
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	private TextView txtIdruta;
	private TextView txtRuta;
	private TextView txtEstado;
	private TextView txtCiudad;
	private TextView txtMunicipio;
	private int idruta;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle__ruta);
		
		 Bundle reicieveParams = getIntent().getExtras();
		    idruta = Integer.parseInt(reicieveParams.getString("idruta").trim());
		    txtIdruta = (TextView) findViewById(R.id.textView2);
		    txtRuta = (TextView) findViewById(R.id.textView4);
		    txtEstado = (TextView) findViewById(R.id.textView6);
		    txtMunicipio = (TextView) findViewById(R.id.textView8);
		    txtCiudad = (TextView) findViewById(R.id.textView10);
		    
		    //text color
		    txtIdruta.setTextColor(Color.DKGRAY); 
		    txtRuta.setTextColor(Color.DKGRAY); 
		    txtEstado.setTextColor(Color.DKGRAY); 
		    txtMunicipio.setTextColor(Color.DKGRAY); 
		    txtCiudad.setTextColor(Color.DKGRAY); 

			SQLiteQuery sql = new SQLiteQuery(this);
			Ruta r = sql.getRutaPorId(idruta);
			 txtIdruta.setText(""+r.getIdruta());
			    txtRuta.setText(r.getRuta());
			    txtEstado.setText(r.getEstado());
			    txtMunicipio.setText(r.getMunicipio());
			    txtCiudad.setText(r.getCiudad());
			    
			    ActionBar actionBar = getActionBar();
		        actionBar.setDisplayHomeAsUpEnabled(true);
		        actionBar.setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.detalle__ruta, menu);
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Modificar Ruta");
        menu3.setAlphabeticShortcut('m');
        MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Eliminar Ruta");
        menu2.setAlphabeticShortcut('e');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
			 case android.R.id.home:
			 		Intent intentHome = new Intent(Detalle_Ruta.this,Rutas.class);
			 		startActivity(intentHome);
			 		return true;
	        case MENU1:
	        	Intent intent = new Intent(Detalle_Ruta.this,Update_Ruta.class);
	        	intent.putExtra("idruta",""+idruta);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);    
	    			// set title
	    			alertDialogBuilder.setTitle("Advertencia");     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("¿Desea eliminar la ruta?")
	    				.setCancelable(false)
	    				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, close
	    						// current activity	    
	    						SQLiteQuery sql = new SQLiteQuery(Detalle_Ruta.this);
	    						sql.eliminar("ruta", "idruta", idruta);
	    						//Toast.makeText(this, "Se ha eliminado el cliente...", Toast.LENGTH_LONG).show();
	    	    				Intent intent2 = new Intent(Detalle_Ruta.this,Rutas.class);
	    	    	    		startActivity(intent2);   
	    					}
	    				  })
	    				.setNegativeButton("No",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						dialog.cancel();	    						
	    					}
	    				});	     
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();	     
	    				// show it
	    				alertDialog.show(); 	    		    				
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}
