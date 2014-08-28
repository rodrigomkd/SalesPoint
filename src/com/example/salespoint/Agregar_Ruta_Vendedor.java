package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Ruta;
import com.example.metodos.ListArrayAdapter;
import com.example.sql.SQLiteQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;


public class Agregar_Ruta_Vendedor extends Activity {
	private int idvendedor;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private final int MENU1 = 1;
	private boolean hayDatos = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregar__ruta__vendedor);
		
		 Bundle reicieveParams = getIntent().getExtras();
		  idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor").trim());
		  
			listView = (ListView) findViewById(R.id.listView1);
			SQLiteQuery sql = new SQLiteQuery(this);
			List<String> items = new ArrayList<String>();
			List<Ruta> rutas = sql.getRutasSinVendedor(idvendedor);
			if(rutas.size() == 0){
				 Toast.makeText(this, "No hay rutas disponibles...", Toast.LENGTH_LONG).show();		
			}else{
				hayDatos = true;
				for(Ruta r : rutas){
					items.add(r.getRuta());
					this.ids.add(r.getIdruta());		
				}		
				/*
				  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
						   android.R.layout.simple_list_item_1, items);
			                listView.setAdapter(adapter);
			                listView.setAdapter(adapter);
			                */
			                //
				
				//			adapter = new RutaArrayAdapter(this, rutas);
			        //        listView.setAdapter( adapter );
				 ArrayAdapter<String> adapter = new ListArrayAdapter(this,items);
				 listView.setAdapter(adapter);
			}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.agregar__ruta__vendedor, menu);
		MenuItem menu1 = menu.add(Menu.NONE, MENU1, 3, "Agregar");
        menu1.setAlphabeticShortcut('a');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	if(!hayDatos){
	        		Toast.makeText(this, "No existen rutas.", Toast.LENGTH_LONG).show();  
	        		return true;
	        	}
	        	boolean haySeleccionados = false;
	        	SQLiteQuery sql = new SQLiteQuery(this); 
	        	 for (int i = 0; i < listView.getAdapter().getCount(); i++)
	             {
	               ViewGroup row = (ViewGroup) listView.getChildAt(i);
	               CheckBox tvTest = (CheckBox) row.findViewById(R.id.CheckBox01);
	                 if (tvTest.isChecked())
	                 {
	                	 sql.insertVendedorRuta(idvendedor, ids.get(i));
	                     haySeleccionados = true;
	                 }
	             
	             }
	        	if(haySeleccionados){
	        		Toast.makeText(this, "Se agregar las rutas al vendedor.", Toast.LENGTH_LONG).show();   
		        	Intent intent = new Intent(Agregar_Ruta_Vendedor.this,Seleccionar_Ruta.class);
		        	intent.putExtra("idvendedor",""+idvendedor);
		    		startActivity(intent);   
	        	}else{
	        		Toast.makeText(this, "No hay rutas seleccionadas.", Toast.LENGTH_LONG).show();   
	        	}
	        		
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}
