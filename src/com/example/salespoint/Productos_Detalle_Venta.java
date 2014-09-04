package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.metodos.ListArrayAdapterCantidad;
import com.example.sql.SQLiteQuery;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Productos_Detalle_Venta extends Activity {
	private int idvendedor;
	private int idventa;
	private ListView listView;
	private List<Integer> ids = new ArrayList<Integer>();
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_productos__detalle__venta);
		
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
			   idventa = Integer.parseInt(reicieveParams.getString("idventa"));
		  }	
		  
		  SQLiteQuery sql = new SQLiteQuery(this);
		  List<Detalleventa> dventas = sql.getDetalleventaPorIdVenta(idventa);
		  if(dventas.isEmpty()){
			  Toast.makeText(this, "No hay productos.", Toast.LENGTH_LONG).show();
			  return;
		  }
		  List<Producto> productos = new ArrayList<Producto>();
		  for(Detalleventa dv : dventas){
			  ids.add(dv.getIddetalleventa());
			  Producto p = sql.getProductoPorId(dv.getIdproducto());
			  productos.add(p);
		  }

		  listView = (ListView) this.findViewById(R.id.listView1);
			 ArrayAdapter<Detalleventa> adapter = new ListArrayAdapterCantidad(this,dventas,productos);
			 listView.setAdapter(adapter);
			 listView.setOnItemClickListener(new OnItemClickListener(){
			        public void onItemClick(AdapterView<?> parent, View view, int position,long id) { 
			        		Intent intent = new Intent(Productos_Detalle_Venta.this,Detalle_Producto_Venta.class);
			             	intent.putExtra("idvendedor", ""+idvendedor);
			             	intent.putExtra("idventa", ""+idventa);	
			             	intent.putExtra("iddetalleventa", ""+ids.get(position));	
			        		startActivity(intent);   		
			        }
			    });
			 
			 ActionBar actionBar = getActionBar();
	 	        actionBar.setDisplayHomeAsUpEnabled(true);
	 	        actionBar.setHomeButtonEnabled(true);
		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Ventas");
        menu3.setAlphabeticShortcut('v');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 case android.R.id.home:
		 		Intent intentVentas = new Intent(Productos_Detalle_Venta.this,Detalle_Venta.class);          
		 		intentVentas.putExtra("idvendedor",""+idvendedor);
		 		intentVentas.putExtra("idventa",""+idventa);
		 		startActivity(intentVentas);
		 	return true;
	        case MENU1:
	        	Intent intent = new Intent(Productos_Detalle_Venta.this,Mis_Ventas.class);
	        	intent.putExtra("idvendedor",""+idvendedor);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(Productos_Detalle_Venta.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}
}
