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

public class Seleccionar_Ruta extends Activity {
	private int idvendedor;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private final int  MENU1 = 1;
	private final int MENU2 = 2;
	private final int MENU3 = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seleccionar__ruta);
		
		 Bundle reicieveParams = getIntent().getExtras();
		  idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor").trim());
		  
			listView = (ListView) findViewById(R.id.listView1);
			SQLiteQuery sql = new SQLiteQuery(this);
			List<String> items = new ArrayList<String>();
			List<Ruta> rutas = sql.getRutasPorIdVendedor(idvendedor);
			if(rutas.size() == 0){
				 Toast.makeText(this, "No existen Rutas asignadas...", Toast.LENGTH_LONG).show();		
			}else{
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
				ArrayAdapter<String> adapter = new ListArrayAdapter(this,items);
				listView.setAdapter(adapter);
			}

			/*
			listView.setOnItemClickListener(new OnItemClickListener(){
		        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
		        		Intent intent = new Intent(Seleccionar_Ruta.this,Detalle_Ruta.class);
		        		intent.putExtra("idruta", ""+ids.get(position));
		        		startActivity(intent);   		
		        }
		    });
		  */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.seleccionar__ruta, menu);
		MenuItem menu1 = menu.add(Menu.NONE, MENU1, 3, "Agregar Rutas");
        menu1.setAlphabeticShortcut('a');
        MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Eliminar Rutas");
        menu2.setAlphabeticShortcut('e');
        MenuItem menu3 = menu.add(Menu.NONE, MENU3, 3, "Vendedores");
        menu3.setAlphabeticShortcut('v');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	Intent intent = new Intent(Seleccionar_Ruta.this,Agregar_Ruta_Vendedor.class);
	        	intent.putExtra("idvendedor",""+idvendedor);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	//eliminar rutas seleccionadas    	
	        	boolean haySeleccionados = false;
	        	SQLiteQuery sql = new SQLiteQuery(this); 
	        	 for (int i = 0; i < listView.getAdapter().getCount(); i++)
	             {
	               ViewGroup row = (ViewGroup) listView.getChildAt(i);
	               CheckBox tvTest = (CheckBox) row.findViewById(R.id.CheckBox01);
	                 if (tvTest.isChecked())
	                 {
	                	 sql.eliminarRutaVendedor(idvendedor, ids.get(i));
	                     haySeleccionados = true;
	                 }
	             
	             }
	        	if(haySeleccionados){
	        		Toast.makeText(this, "Se eliminaron las rutas seleccionadas.", Toast.LENGTH_LONG).show();   
		        	Intent intent2 = new Intent(Seleccionar_Ruta.this,Seleccionar_Ruta.class);
		        	intent2.putExtra("idvendedor",""+idvendedor);
		    		startActivity(intent2);   
	        	}else{
	        		Toast.makeText(this, "No hay rutas seleccionadas.", Toast.LENGTH_LONG).show();   
	        	}
	            return true;
	        case MENU3:
	        	Intent intent3 = new Intent(Seleccionar_Ruta.this,Vendedores.class);
	    		startActivity(intent3);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}
