package com.example.salespoint;

import com.example.entidades.Producto;
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

public class Detalle_Producto extends Activity {
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	private TextView txtIdproducto;
	private TextView txtProducto;
	private TextView txtDescripcion;
	private TextView txtPcompra;
	private TextView txtPventa;
	private TextView txtCantidad;
	private int idproducto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle__producto);
		
		 Bundle reicieveParams = getIntent().getExtras();
		    idproducto = Integer.parseInt(reicieveParams.getString("idproducto").trim());
			txtIdproducto = (TextView) findViewById(R.id.textView2);
			txtProducto = (TextView) findViewById(R.id.textView4);
			txtDescripcion = (TextView) findViewById(R.id.textView6);
			txtPcompra = (TextView) findViewById(R.id.textView8);
			txtPventa = (TextView) findViewById(R.id.textView10);
			txtCantidad = (TextView) findViewById(R.id.textView12);
			txtIdproducto.setTextColor(Color.DKGRAY);
			txtProducto.setTextColor(Color.DKGRAY);
			txtDescripcion.setTextColor(Color.DKGRAY);
			txtPcompra.setTextColor(Color.DKGRAY);
			txtPventa.setTextColor(Color.DKGRAY);
			txtCantidad.setTextColor(Color.DKGRAY);
			
			SQLiteQuery sql = new SQLiteQuery(this);
			Producto p = sql.getProductoPorId(idproducto);
			txtIdproducto.setText(""+p.getIdproducto());
			txtDescripcion.setText(p.getDescripcion());
			txtPcompra.setText(""+p.getPcompra());
			txtPventa.setText(""+p.getPventa());
			txtCantidad.setText(""+p.getCantidad());
			txtProducto.setText(p.getProducto());
			
			 ActionBar actionBar = getActionBar();
 	        actionBar.setDisplayHomeAsUpEnabled(true);
 	        actionBar.setHomeButtonEnabled(true);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.detalle__producto, menu);
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Modificar Producto");
        menu3.setAlphabeticShortcut('m');
        MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Eliminar Producto");
        menu2.setAlphabeticShortcut('e');
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 case android.R.id.home:
		 		Intent intentHome = new Intent(Detalle_Producto.this,MainActivity_Productos.class);
		 		startActivity(intentHome);
		 		return true;
	        case MENU1:
	        	Intent intent = new Intent(Detalle_Producto.this,Update_Producto.class);
	        	intent.putExtra("activity", "Detalle_Producto");
	        	intent.putExtra("idproducto",""+idproducto);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);    
	    			// set title
	    			alertDialogBuilder.setTitle("Advertencia");     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("¿Desea eliminar el producto?")
	    				.setCancelable(false)
	    				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, close
	    						// current activity	    
	    						SQLiteQuery sql = new SQLiteQuery(Detalle_Producto.this);
	    						sql.eliminar("producto", "idproducto", idproducto);
	    						//Toast.makeText(this, "Se ha eliminado el cliente...", Toast.LENGTH_LONG).show();
	    	    				Intent intent2 = new Intent(Detalle_Producto.this,MainActivity_Productos.class);
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
