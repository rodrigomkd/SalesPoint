package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;
import com.example.entidades.Ruta;
import com.example.sql.SQLiteQuery;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Rutas extends Activity {
	private static final int MENU1 = 1;
	private static final int MENU2 = 2;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rutas);
		
		listView = (ListView) findViewById(R.id.listView1);
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<Ruta> rutas = sql.getRutas();
		if(rutas.size() == 0){
			 Toast.makeText(this, "No existen Rutas registradas.", Toast.LENGTH_LONG).show();		
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
	        		Intent intent = new Intent(Rutas.this,Detalle_Ruta.class);
	        		intent.putExtra("idruta", ""+ids.get(position));
	        		startActivity(intent);   		
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.rutas, menu);
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Alta Ruta");
        menu3.setAlphabeticShortcut('c');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	Intent intent = new Intent(Rutas.this,Update_Ruta.class);
	        	intent.putExtra("idruta","0");
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(Rutas.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}
