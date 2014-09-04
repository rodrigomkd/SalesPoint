package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;
import com.example.entidades.Vendedor;
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

public class Vendedores extends Activity {
	private static final int MENU1 = 1;
	private static final int MENU2 = 2;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vendedores);
		
		listView = (ListView) findViewById(R.id.listView1);
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<Vendedor> vendedores = sql.getVendedores();
		if(vendedores.size() == 0){
			 Toast.makeText(this, "No existen Vendedores registrados.", Toast.LENGTH_LONG).show();		
		}else{
			for(Vendedor v : vendedores){
				items.add(v.getNombre()+" "+v.getApepat()+" "+v.getApemat());
				this.ids.add(v.getIdvendedor());		
			}	
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					   android.R.layout.simple_list_item_1, items);
		                listView.setAdapter(adapter);
		                listView.setAdapter(adapter);
		}
	
		 ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);
	        
		listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
	        		Intent intent = new Intent(Vendedores.this,Detalle_Vendedor.class);
	        		intent.putExtra("idvendedor", ""+ids.get(position));
	        		startActivity(intent);   		
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.vendedores, menu);
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Alta Vendedor");
        menu3.setAlphabeticShortcut('a');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 case android.R.id.home:
		 		Intent intentHome = new Intent(Vendedores.this,MainActivity.class);
		 		startActivity(intentHome);
		 		return true;
	        case MENU1:
	        	Intent intent = new Intent(Vendedores.this,Update_Vendedores.class);
	        	intent.putExtra("idvendedor","0");
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(Vendedores.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}
