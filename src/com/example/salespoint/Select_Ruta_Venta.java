package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;
import com.example.entidades.Ruta;
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

public class Select_Ruta_Venta extends Activity {
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private int idvendedor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select__ruta__venta);
		
		listView = (ListView) findViewById(R.id.listView1);
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
		  }
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<Ruta> rutas = sql.getRutasPorIdVendedor(idvendedor);
		
		if(rutas.size() == 0){
			 Toast.makeText(this, "No hay rutas disponibles.", Toast.LENGTH_LONG).show();		
		}else{
			for(Ruta r : rutas){
				items.add(r.getRuta());
				this.ids.add(r.getIdruta());		
			}		
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					   android.R.layout.simple_list_item_1, items);
		                listView.setAdapter(adapter);
		                listView.setAdapter(adapter);
		}

		
		listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
	        		Intent intent = new Intent(Select_Ruta_Venta.this,Select_Cliente_Venta.class);
	        		intent.putExtra("idruta", ""+ids.get(position));
	        		intent.putExtra("idvendedor", ""+idvendedor);
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
