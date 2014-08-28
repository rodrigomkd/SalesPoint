package com.example.salespoint;

import java.util.ArrayList;
import java.util.List;
import com.example.entidades.Venta;
import com.example.metodos.Metodos;
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

public class Mis_Ventas extends Activity {
	private int idvendedor;
	private ListView listView;
	private static final int MENU1 = 1;
	private static final int MENU2 = 2;
	private List<Integer> ids = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mis__ventas);
		
		listView = (ListView) findViewById(R.id.listView1);
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
		  }	
		  
		  SQLiteQuery sql = new SQLiteQuery(this);
		  List<Venta> ventas = sql.getVentasPorIdVendedor(idvendedor);
		  if(!ventas.isEmpty()){
			  ArrayList<String> items = new ArrayList<String>();
			  for(Venta v: ventas){
				  String fecha = Metodos.convertirFecha(v.getFecha());
				  items.add(fecha.split(" ")[0] +"		"+ fecha.split(" ")[1]);
				  ids.add(v.getIdventa());
			  }
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					   android.R.layout.simple_list_item_1, items);
		                listView.setAdapter(adapter);
		                listView.setAdapter(adapter);
		                
		                listView.setOnItemClickListener(new OnItemClickListener(){
		        	        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {   	
		        	        		Intent intent = new Intent(Mis_Ventas.this,Detalle_Venta.class);
		        	        		intent.putExtra("idventa", ""+ids.get(position));
		        	        		intent.putExtra("idvendedor",""+idvendedor);
		        	        		startActivity(intent);   		
		        	        }
		        	    });
		  }else{
			  Toast.makeText(this, "No hay ventas existentes.", Toast.LENGTH_LONG).show();	
		  }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Nueva Venta");
        menu3.setAlphabeticShortcut('n');
        MenuItem menuPrincipal = menu.add(Menu.NONE, MENU2, 3, "Menú");
        menuPrincipal.setAlphabeticShortcut('m');
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	Intent intent = new Intent(Mis_Ventas.this,Select_Ruta_Venta.class);
	        	intent.putExtra("idvendedor",""+idvendedor);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	Intent intent2 = new Intent(Mis_Ventas.this,MainActivity.class);
	    		startActivity(intent2);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}
}
