package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Vendedor;
import com.example.sql.SQLiteQuery;

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

public class Select_Vendedor_Venta extends Activity {
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select__vendedor__venta);
		
		listView = (ListView) findViewById(R.id.listView1);
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<Vendedor> vendedores = sql.getVendedores();
		if(vendedores.size() == 0){
			 Toast.makeText(this, "No existen Vendedores registrados.", Toast.LENGTH_LONG).show();		
		}else{
			for(Vendedor v : vendedores){
				items.add(v.getNombre()+" "+v.getApepat()+" "+v.getApemat());
				ids.add(v.getIdvendedor());		
			}	
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					   android.R.layout.simple_list_item_1, items);
		                listView.setAdapter(adapter);
		                listView.setAdapter(adapter);
		}
	
		listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
	        		Intent intent = new Intent(Select_Vendedor_Venta.this,Select_Ruta_Venta.class);
	        		intent.putExtra("idvendedor", ""+ids.get(position));
	        		startActivity(intent);   		
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
