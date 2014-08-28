package com.example.salespoint;

import com.example.entidades.Ruta;
import com.example.entidades.Venta;
import com.example.entidades.cliente;
import com.example.metodos.Metodos;
import com.example.sql.SQLiteQuery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Detalle_Venta extends Activity {
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	private int idvendedor;
	private TextView tvIdventa;
	private TextView tvCliente;
	private TextView tvRuta;
	private TextView tvSubtotal;
	private TextView tvIva;
	private TextView tvTotal;
	private TextView tvFecha;
	private int idventa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle__venta);
		
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
			   idventa = Integer.parseInt(reicieveParams.getString("idventa"));
		  }
		  
		  tvIdventa = (TextView) findViewById(R.id.textView2);
		  tvFecha = (TextView) findViewById(R.id.textView4);
		  tvCliente = (TextView) findViewById(R.id.textView8);
		  tvRuta = (TextView) findViewById(R.id.textView10);
		  tvSubtotal = (TextView) findViewById(R.id.textView12);
		  tvIva = (TextView) findViewById(R.id.textView14);
		  tvTotal = (TextView) findViewById(R.id.textView6);
		  
		  tvIdventa.setTextColor(Color.DKGRAY);
		  tvFecha.setTextColor(Color.DKGRAY);
		  tvCliente.setTextColor(Color.DKGRAY);
		  tvRuta.setTextColor(Color.DKGRAY);
		  tvSubtotal.setTextColor(Color.DKGRAY);
		  tvIva.setTextColor(Color.DKGRAY);
		  tvTotal.setTextColor(Color.DKGRAY);
		  
		  SQLiteQuery sql = new SQLiteQuery(this);
		  Venta v = sql.getVentaPorIdVenta(idventa);
		  tvIdventa.setText(""+v.getIdventa());
		  tvFecha.setText(Metodos.convertirFecha(v.getFecha()));
		  cliente c = sql.consultaClientesPorId(v.getIdcliente());
		  Log.i("idcliente",""+ v.getIdcliente());
		  tvCliente.setText(c.getNombre()+" "+c.getApepat()+" "+c.getApemat());
		  Ruta r = sql.getRutaPorId(v.getIdruta());
		  tvRuta.setText(r.getRuta());
		  tvSubtotal.setText(""+v.getSubtotal());
		  tvIva.setText(""+v.getIva());
		  tvTotal.setText(""+v.getTotal());
	}

	public void onCancelClick(View view){
		Intent intent = new Intent(Detalle_Venta.this,Mis_Ventas.class);
    	intent.putExtra("idvendedor",""+idvendedor);
		startActivity(intent);   
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Ver detalles");
        menu3.setAlphabeticShortcut('d');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	Intent intent = new Intent(Detalle_Venta.this,Productos_Detalle_Venta.class);
	        	intent.putExtra("idvendedor",""+idvendedor);
	        	intent.putExtra("idventa",""+idventa);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(Detalle_Venta.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}
}
