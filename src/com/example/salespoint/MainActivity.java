package com.example.salespoint;



import java.util.ArrayList;
import java.util.List;


import com.example.metodos.ListArrayAdapterMenu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends Activity {
	private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
              
        listView = (ListView) findViewById(R.id.listView1);
        List<String> items = new ArrayList<String>();
        items.add("Clientes");
        items.add("Productos");
        items.add("Rutas");
        items.add("Vendedores");
        items.add("Ventas");
        items.add("Realizar venta");
        
        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, items);
        list.setAdapter(adapter);
        listView = list;
        */
        
        ArrayAdapter<String> adapter = new ListArrayAdapterMenu(this,items);
		 listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new OnItemClickListener(){
	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
	        	if(position == 0){
	        		Intent intent = new Intent(MainActivity.this,MainActivity_Clientes.class);
	    			startActivity(intent);
	    			return;
	        	}
	        	if(position == 1){
	        		Intent intent = new Intent(MainActivity.this,MainActivity_Productos.class);
	    			startActivity(intent); 
	    			return;
	        	}
	        	if(position == 2){
	        		Intent intent = new Intent(MainActivity.this,Rutas.class);
	    			startActivity(intent); 
	    			return;
	        	}
	        	if(position == 3){
	        		Intent intent = new Intent(MainActivity.this,Vendedores.class);
	    			startActivity(intent); 
	    			return;
	        	}
	        	if(position == 4){
	        		Intent intent = new Intent(MainActivity.this,Ventas.class);
	    			startActivity(intent); 
	    			return;
	        	}
	        	if(position == 5){
	        		Intent intent = new Intent(MainActivity.this,Select_Vendedor_Venta.class);
	    			startActivity(intent);
	        	}
	        }
	    });
        
     }
        
    public void inicio(View view){
    	Intent intent = new Intent(MainActivity.this,MainActivity_Clientes.class);
    			startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    
}
