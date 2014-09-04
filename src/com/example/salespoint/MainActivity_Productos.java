package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Producto;
import com.example.sql.SQLiteQuery;

import android.os.Bundle;
import android.app.ActionBar;
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

public class MainActivity_Productos extends Activity {
	private static final int MENU1 = 1;
	private static final int MENU2 = 2;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity__productos);
		
		listView = (ListView) findViewById(R.id.listView1);
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<Producto> productos = sql.getProductos();
		if(productos.size() == 0){
			 Toast.makeText(this, "No existen Productos registrados.", Toast.LENGTH_LONG).show();		
		}else{
			for(Producto p : productos){
				items.add(p.getProducto());
				this.ids.add(p.getIdproducto());		
			}		
			   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					   android.R.layout.simple_list_item_1, items);
		                listView.setAdapter(adapter);
		                listView.setAdapter(adapter);
		                
		                ActionBar actionBar = getActionBar();
		    	        actionBar.setDisplayHomeAsUpEnabled(true);
		    	        actionBar.setHomeButtonEnabled(true);
		}

		
		listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
	        		Intent intent = new Intent(MainActivity_Productos.this,Detalle_Producto.class);
	        		intent.putExtra("idproducto", ""+ids.get(position));
	        		intent.putExtra("activity", "MainActivity_Productos");
	        		startActivity(intent);   		
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Alta Producto");
        menu3.setAlphabeticShortcut('c');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
			 case android.R.id.home:
			 		Intent intentHome = new Intent(MainActivity_Productos.this,MainActivity.class);
			 		startActivity(intentHome);
			 		return true;
	        case MENU1:
	        	Intent intent = new Intent(MainActivity_Productos.this,Update_Producto.class);
	        	intent.putExtra("idproducto","0");
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(MainActivity_Productos.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}
