package com.example.salespoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.entidades.cliente;
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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Select_Cliente_Venta extends Activity {
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private int idruta;
	private int idvendedor;
	private List<Detalleventa> detalleventas = new ArrayList<Detalleventa>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select__cliente__venta);
		
		listView = (ListView) findViewById(R.id.listView1);
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
			   idruta = Integer.parseInt(reicieveParams.getString("idruta"));
		  }
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<cliente> clientes = sql.getClientesPorIdRuta(idruta);
		
		if(clientes.size() == 0){
			 Toast.makeText(this, "No existen Clientes registrados.", Toast.LENGTH_LONG).show();		
		}else{
			for(cliente c : clientes){
				items.add(c.getNombre()+" "+c.getApepat()+" "+c.getApemat());
				this.ids.add(c.getIdcliente());		
			}		
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					   android.R.layout.simple_list_item_1, items);
		                listView.setAdapter(adapter);
		                listView.setAdapter(adapter);
		                
			    		List<Producto> productos = sql.getProductos();
			    		for(Producto p : productos){
			    			Detalleventa dv = new Detalleventa();
			    			dv.setCantidad(0);
			    			dv.setIddetalleventa(0);
			    			dv.setIdventa(0);
			    			dv.setIdproducto(p.getIdproducto());	
			    			detalleventas.add(dv);
			    		}	
		}
		
		ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        
		listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {  	        	
	        		Intent intent = new Intent(Select_Cliente_Venta.this,Select_Producto_Venta.class);
	        		intent.putExtra("idcliente", ""+ids.get(position));
	        		intent.putExtra("idruta", ""+idruta);
	        		intent.putExtra("idvendedor", ""+idvendedor);
	        		intent.putExtra("detalleventas",(Serializable) detalleventas);
	        		//startActivity(intent); 
	        		startActivityForResult(intent, 0);
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 case android.R.id.home:
		 		Intent intentHome = new Intent(Select_Cliente_Venta.this,Select_Ruta_Venta.class);
		 		intentHome.putExtra("idvendedor", ""+idvendedor);
		 		startActivity(intentHome);
		 		return true;
		 default: 
			 return super.onOptionsItemSelected(item);
		 }
	}
}
