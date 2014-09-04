package com.example.salespoint;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.sql.SQLiteQuery;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Detalle_Producto_Venta extends Activity {
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	private final int MENU3 = 3;
	private int idvendedor;
	private int idventa;
	private int iddetalleventa;
	private TextView tvIdproducto;
	private TextView tvProducto;
	private TextView tvDescripcion;
	private TextView tvPrecio;
	private TextView tvCantidad;
	private TextView tvSubtotal;
	private TextView tvIva;
	private TextView tvTotal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle__producto__venta);
		
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
			   idventa = Integer.parseInt(reicieveParams.getString("idventa"));
			   iddetalleventa = Integer.parseInt(reicieveParams.getString("iddetalleventa"));
		  }
		  
		  tvIdproducto = (TextView) this.findViewById(R.id.textView2);
		  tvProducto = (TextView) this.findViewById(R.id.textView16);
		  tvDescripcion = (TextView) this.findViewById(R.id.textView5);
		  tvPrecio = (TextView) this.findViewById(R.id.textView7);
		  tvCantidad = (TextView) this.findViewById(R.id.textView9);
		  tvIva = (TextView) this.findViewById(R.id.textView13);
		  tvSubtotal = (TextView) this.findViewById(R.id.textView11);
		  tvTotal = (TextView) this.findViewById(R.id.textView15);
		  
		  tvIdproducto.setTextColor(Color.DKGRAY);
		  tvProducto.setTextColor(Color.DKGRAY);
		  tvDescripcion.setTextColor(Color.DKGRAY);
		  tvPrecio.setTextColor(Color.DKGRAY);
		  tvCantidad.setTextColor(Color.DKGRAY);
		  tvIva.setTextColor(Color.DKGRAY);
		  tvSubtotal.setTextColor(Color.DKGRAY);
		  tvTotal.setTextColor(Color.DKGRAY);
		  
		  SQLiteQuery sql = new SQLiteQuery(this);
		  Detalleventa dv = sql.getDetalleventaPorId(iddetalleventa);
		  tvIdproducto.setText(""+dv.getIdproducto());
		  Producto p = sql.getProductoPorId(dv.getIdproducto());
		  tvProducto.setText(p.getProducto());
		  tvDescripcion.setText(p.getDescripcion());
		  tvPrecio.setText(""+p.getPventa());
		  tvCantidad.setText(""+dv.getCantidad());
		  double subtotal = (p.getPventa()*dv.getCantidad());
		  tvSubtotal.setText(""+subtotal);
		  tvIva.setText(""+(subtotal*0.16));
		  tvTotal.setText(""+(subtotal*1.16));
		  
		  ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);

	}

	public void onClickAceptar(View view){
		Intent intent = new Intent(Detalle_Producto_Venta.this,Productos_Detalle_Venta.class);
    	intent.putExtra("idvendedor",""+idvendedor);
    	intent.putExtra("idventa",""+idventa);
		startActivity(intent);   	 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu1 = menu.add(Menu.NONE, MENU1, 3, "Detalle venta");
        menu1.setAlphabeticShortcut('d');
        MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Ventas");
        menu2.setAlphabeticShortcut('v');
        MenuItem menu3 = menu.add(Menu.NONE, MENU3, 3, "Menú");
        menu3.setAlphabeticShortcut('m');
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 case android.R.id.home:
		 		Intent intentVentas = new Intent(Detalle_Producto_Venta.this,Productos_Detalle_Venta.class);          
		 		intentVentas.putExtra("idvendedor",""+idvendedor);
		 		intentVentas.putExtra("idventa",""+idventa);
		 		startActivity(intentVentas);
		 	return true;
	        case MENU1:
	        	Intent intent = new Intent(Detalle_Producto_Venta.this,Detalle_Venta.class);
	        	intent.putExtra("idvendedor",""+idvendedor);
	        	intent.putExtra("idventa",""+idventa);
	    		startActivity(intent);   	        		
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(Detalle_Producto_Venta.this,Mis_Ventas.class);
	        	intent2.putExtra("idvendedor",""+idvendedor);
	    		startActivity(intent2);   	        		
	            return true;
	        case MENU3:
	        	Intent intent3 = new Intent(Detalle_Producto_Venta.this,MainActivity.class);
	    		startActivity(intent3);   	        		
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }	
	}
}
