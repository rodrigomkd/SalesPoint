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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Ventas extends Activity {
	private List<Integer> ids = new ArrayList<Integer>();
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventas);
		
		SQLiteQuery sql = new SQLiteQuery(this);
		List<Vendedor> vendedores = sql.getVendedores();
		List<String> items = new ArrayList<String>();
		if(vendedores.isEmpty()){
			Toast.makeText(this, "No hay clientes existentes.", Toast.LENGTH_LONG).show();
			return;
		}
		for(Vendedor v : vendedores){
			items.add(v.getNombre()+" "+v.getApepat()+" "+v.getApemat());
			ids.add(v.getIdvendedor());
		}
		listView = (ListView) this.findViewById(R.id.listView1);
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				   android.R.layout.simple_list_item_1, items);
	                listView.setAdapter(adapter);
	                listView.setAdapter(adapter);
	
	               listView = (ListView) this.findViewById(R.id.listView1);
	  listView.setOnItemClickListener(new OnItemClickListener(){
       public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
       		Intent intent = new Intent(Ventas.this,Mis_Ventas.class);
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
