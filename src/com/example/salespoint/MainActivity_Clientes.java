package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;
import com.example.entidades.cliente;
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

public class MainActivity_Clientes extends Activity {
	private static final int MENU1 = 1;
	private static final int MENU2 = 2;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity__clientes);
		
		listView = (ListView) findViewById(R.id.listView1);
		SQLiteQuery sql = new SQLiteQuery(this);
		List<String> items = new ArrayList<String>();
		List<cliente> clientes = sql.getClientes();
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
		                
		                ActionBar actionBar = getActionBar();
		                actionBar.setDisplayHomeAsUpEnabled(true);
		                actionBar.setHomeButtonEnabled(true);
		}
		
		listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
	        		Intent intent = new Intent(MainActivity_Clientes.this,Detalle_cliente.class);
	        		//int idcliente = Integer.parseInt((items.get(position).toString().split("-")[0]));
	        		intent.putExtra("idcliente", ""+ids.get(position));
	        		startActivity(intent);   		
	        }
	    });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main_activity__clientes, menu);
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Alta Cliente");
        menu3.setAlphabeticShortcut('c');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
        /*
        SubMenu menu4 = menu.addSubMenu(Menu.NONE, MENU4, 4,"Menu No. 4");
        menu4.add(GROUP1, SUBMENU1, 1, "SubMenu No. 1");
        menu4.add(GROUP1, SUBMENU2, 2, "SubMenu No. 2");
        menu4.setGroupCheckable(GROUP1,true,true);
        
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionsmenu, menu);
        */
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 	case android.R.id.home:
		 		Intent intentHome = new Intent(MainActivity_Clientes.this,MainActivity.class);
		 		startActivity(intentHome);
		 		return true;
	        case MENU1:
	        	Intent intent = new Intent(MainActivity_Clientes.this,Alta_cliente.class);
	        	intent.putExtra("idcliente","0");
	        	intent.putExtra("activity", "MainActivity_Clientes");
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(MainActivity_Clientes.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}
	
}
