package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Ruta;
import com.example.metodos.ListArrayAdapter;
import com.example.sql.SQLiteQuery;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class Rutas_Cliente extends Activity {
	private int idcliente;
	private ListView listView;
	private ArrayList<Integer> ids = new ArrayList<Integer>();
	private final int  MENU1 = 1;
	private final int MENU2 = 2;
	private final int MENU3 = 3;
	private boolean hayRutas = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rutas__cliente);
		
		 Bundle reicieveParams = getIntent().getExtras();
		  idcliente = Integer.parseInt(reicieveParams.getString("idcliente").trim());
		  
			listView = (ListView) findViewById(R.id.listView1);
			SQLiteQuery sql = new SQLiteQuery(this);
			List<String> items = new ArrayList<String>();
			List<Ruta> rutas = sql.getRutasPorIdCliente(idcliente);
			if(rutas.size() == 0){
				 Toast.makeText(this, "No existen Rutas asignadas...", Toast.LENGTH_LONG).show();		
			}else{
				for(Ruta r : rutas){
					items.add(r.getRuta());
					this.ids.add(r.getIdruta());		
				}		

				ArrayAdapter<String> adapter = new ListArrayAdapter(this,items);
				listView.setAdapter(adapter);
				hayRutas = true;
			}
			ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu1 = menu.add(Menu.NONE, MENU1, 3, "Agregar Rutas");
        menu1.setAlphabeticShortcut('a');
        if(hayRutas){
        	 MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Eliminar Rutas");
             menu2.setAlphabeticShortcut('e');
        }
     
        MenuItem menu3 = menu.add(Menu.NONE, MENU3, 3, "Clientes");
        menu3.setAlphabeticShortcut('e');
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 case android.R.id.home:
		 		Intent intentHome = new Intent(Rutas_Cliente.this,Detalle_cliente.class);
		 		intentHome.putExtra("idcliente",""+idcliente);
		 		startActivity(intentHome);
		 		return true;
	        case MENU1:
	        	Intent intent = new Intent(Rutas_Cliente.this,Agregar_Ruta_Cliente.class);
	        	intent.putExtra("idcliente",""+idcliente);
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
	                	 sql.eliminarRutaCliente(idcliente, ids.get(i));
	                     haySeleccionados = true;
	                 }
	             
	             }
	        	if(haySeleccionados){
	        		Toast.makeText(this, "Se eliminaron las rutas seleccionadas.", Toast.LENGTH_LONG).show();   
		        	Intent intent2 = new Intent(Rutas_Cliente.this,Rutas_Cliente.class);
		        	intent2.putExtra("idcliente",""+idcliente);
		    		startActivity(intent2);   
	        	}else{
	        		Toast.makeText(this, "No hay rutas seleccionadas.", Toast.LENGTH_LONG).show();   
	        	}
	            return true;
	        case MENU3:
	        	Intent intent3 = new Intent(Rutas_Cliente.this,MainActivity_Clientes.class);
	    		startActivity(intent3);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}
}
