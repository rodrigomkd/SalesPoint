package com.example.salespoint;

import com.example.entidades.Vendedor;
import com.example.sql.SQLiteQuery;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Detalle_Vendedor extends Activity {
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	private final int MENU3 = 3;
	private final int MENU4 = 4;
	private final int MENU5 = 5;
	private TextView txtIdvendedor;
	private TextView txtNombre;
	private TextView txtApepat;
	private TextView txtApemat;
	private TextView txtDomicilio;
	private TextView txtCelular;
	private TextView txtEmail;
	private TextView txtRfc;
	private int idvendedor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle__vendedor);
		
		 	Bundle reicieveParams = getIntent().getExtras();
		    idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor").trim());
		    txtIdvendedor = (TextView) findViewById(R.id.textView2);
		    txtNombre = (TextView) findViewById(R.id.textView4);
		    txtApepat = (TextView) findViewById(R.id.textView6);
		    txtApemat = (TextView) findViewById(R.id.textView8);
		    txtDomicilio = (TextView) findViewById(R.id.textView10);
		    txtCelular = (TextView) findViewById(R.id.textView12);
		    txtEmail = (TextView) findViewById(R.id.textView14);
		    txtRfc = (TextView) findViewById(R.id.textView16);
		    
		    //text color
		    txtIdvendedor.setTextColor(Color.DKGRAY);
		    txtNombre.setTextColor(Color.DKGRAY);
		    txtApepat.setTextColor(Color.DKGRAY);
		    txtApemat.setTextColor(Color.DKGRAY);
		    txtDomicilio.setTextColor(Color.DKGRAY);
		    txtCelular.setTextColor(Color.DKGRAY);
		    txtEmail.setTextColor(Color.DKGRAY);
		    txtRfc.setTextColor(Color.DKGRAY);
		    
			SQLiteQuery sql = new SQLiteQuery(this);
			Vendedor v = sql.getVendedorPorId(idvendedor);
			
			txtIdvendedor.setText(""+v.getIdvendedor());
		    txtNombre.setText(v.getNombre()); 
		    txtApepat.setText(v.getApepat());  
		    txtApemat.setText(v.getApemat());   
		    txtDomicilio.setText(v.getDomicilio());  
		    txtCelular.setText(v.getCelular());  ;
		    txtEmail.setText(v.getEmail());  
		    txtRfc.setText(v.getRfc());  
     	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.detalle__vendedor, menu);
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Modificar Vendedor");
        menu3.setAlphabeticShortcut('m');
        MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Eliminar Vendedor");
        menu2.setAlphabeticShortcut('e');
        MenuItem menu4 = menu.add(Menu.NONE, MENU3, 3, "Ver Rutas");
        menu4.setAlphabeticShortcut('r');
        MenuItem menu5 = menu.add(Menu.NONE, MENU4, 3, "Vendedores");
        menu5.setAlphabeticShortcut('v');
        MenuItem menu6 = menu.add(Menu.NONE, MENU5, 3, "Menú");
        menu6.setAlphabeticShortcut('p');
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	//modificar cliente
	        	Intent intent = new Intent(Detalle_Vendedor.this,Update_Vendedores.class);
	        	intent.putExtra("idvendedor",""+idvendedor);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);    
	    			// set title
	    			alertDialogBuilder.setTitle("Advertencia");
	     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("¿Desea eliminar el vendedor?")
	    				.setCancelable(false)
	    				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, close
	    						// current activity	    
	    						SQLiteQuery sql = new SQLiteQuery(Detalle_Vendedor.this);
	    						sql.eliminar("vendedor", "idvendedor", idvendedor);
	    						//Toast.makeText(this, "Se ha eliminado el cliente...", Toast.LENGTH_LONG).show();
	    	    				Intent intent2 = new Intent(Detalle_Vendedor.this,Vendedores.class);
	    	    	    		startActivity(intent2);   
	    					}
	    				  })
	    				.setNegativeButton("No",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, just close
	    						// the dialog box and do nothing

	    						dialog.cancel();
	    						
	    					}
	    				});	     
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();	     
	    				// show it
	    				alertDialog.show(); 	    	    				
	            return true;
	        case MENU3:
	        	//ver rutas
	        	Intent intentVendedores = new Intent(Detalle_Vendedor.this,Seleccionar_Ruta.class);
	        	intentVendedores.putExtra("idvendedor",""+idvendedor);
	    		startActivity(intentVendedores);   
	            return true;
	        case MENU4:
	        	//vendedores
	        	Intent intent4 = new Intent(Detalle_Vendedor.this,Vendedores.class);
	    		startActivity(intent4);   
	            return true;
	        case MENU5:
	        	//menu
	        	Intent intent5 = new Intent(Detalle_Vendedor.this,MainActivity.class);
	    		startActivity(intent5);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}
}
