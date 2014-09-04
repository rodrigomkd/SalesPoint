package com.example.salespoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.entidades.Venta;
import com.example.metodos.ListArrayAdapterCantidad;
import com.example.sql.SQLiteQuery;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class Select_Producto_Venta extends Activity {
	private final int MENU1 = 1;
	private List<Detalleventa> detalleventas = new ArrayList<Detalleventa>();
	private ListView listView;
	private int idvendedor;
	private int idruta;
	private int idcliente;
	//private List<Producto> productos = new ArrayList<Producto>();
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select__producto__venta);
		
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
		    //Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
			   idruta = Integer.parseInt(reicieveParams.getString("idruta"));
			   idcliente = Integer.parseInt(reicieveParams.getString("idcliente"));
		       this.detalleventas = (List<Detalleventa>)getIntent().getSerializableExtra("detalleventas");
		  }
		  
		listView = (ListView) findViewById(R.id.listView1);
		SQLiteQuery sql = new SQLiteQuery(this);
		List<Producto> productos = sql.getProductos();
		 ArrayAdapter<Detalleventa> adapter = new ListArrayAdapterCantidad(this,detalleventas,productos);
		 listView.setAdapter(adapter);
		 
		 ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);
	        
		 listView.setOnItemClickListener(new OnItemClickListener(){
		        public void onItemClick(AdapterView<?> parent, View view, int position,long id) { 
		        		ViewGroup row = (ViewGroup) listView.getChildAt(position);
		                TextView textView = (TextView) row.findViewById(R.id.rowTextView2);
		                int cantidad = Integer.parseInt(textView.getText().toString());
		        		Intent intent = new Intent(Select_Producto_Venta.this,Select_Cantidad_Venta.class);
		             	intent.putExtra("idproducto", ""+detalleventas.get(position).getIdproducto());
		             	intent.putExtra("cantidad", ""+cantidad);	
		             	intent.putExtra("idvendedor", ""+idvendedor);	
		             	intent.putExtra("idruta", ""+idruta);	
		             	intent.putExtra("idcliente", ""+idcliente);	
		             	intent.putExtra("detalleventas",(Serializable) detalleventas);
		        		startActivity(intent);   		
		        }
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu1 = menu.add(Menu.NONE, MENU1, 3, "Finalizar venta");
        menu1.setAlphabeticShortcut('f');
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 	case android.R.id.home:
		 		Intent intentHome = new Intent(Select_Producto_Venta.this,Select_Cliente_Venta.class);
		 		intentHome.putExtra("idvendedor", ""+idvendedor);
		 		intentHome.putExtra("idruta", ""+idruta);
		 		startActivity(intentHome);
		 	return true;
	        case MENU1:
	        	boolean hayCompra = false;
	        	double subtotal = 0;
	        	SQLiteQuery sql = new SQLiteQuery(Select_Producto_Venta.this);
	        	 List<Detalleventa> detalles = new ArrayList<Detalleventa>();
	        	 for (int i = 0; i < listView.getAdapter().getCount(); i++)
	             {
	               ViewGroup row = (ViewGroup) listView.getChildAt(i);
	               TextView tvCantidad = (TextView) row.findViewById(R.id.rowTextView2);             
	                 if (!tvCantidad.getText().toString().equals("0"))
	                 {
	                	 int cantidad = Integer.parseInt(tvCantidad.getText().toString());
	                	 Detalleventa dv = detalleventas.get(i);
	                	 Producto p = sql.getProductoPorId(dv.getIdproducto());
	                	 detalles.add(dv);
	                	 subtotal += (p.getPventa() * cantidad);
	                	 p.setCantidad(p.getCantidad() - cantidad);
	                	 sql.updateProducto(p);
	                     hayCompra = true;
	                 }
	             
	             }
	        	if(hayCompra){
	        		Venta v = new Venta();
	        		v.setFecha(new Date());
	        		v.setIdcliente(idcliente);
	        		v.setIdruta(idruta);
	        		v.setIdvendedor(idvendedor);
	        		v.setIva(subtotal*0.16);
	        		v.setSubtotal(subtotal);
	        		v.setTotal(subtotal*1.16);
	        		sql.insertVenta(v);
	        		int idventa = sql.getId("venta", "idventa")-1;
	        		for(Detalleventa dv : detalles){
	        			dv.setIdventa(idventa);
	        			sql.insertDetalleVenta(dv);
	        		}
	        		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
	        		alertDialog.setTitle("Detalles venta...");
	        		alertDialog.setMessage("Subtotal:     "+subtotal+"\n"+"I.V.A:     "+v.getIva()+"\n"+"TOTAL:     "+v.getTotal());
	        		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		        		public void onClick(DialogInterface dialog, int which) {
		        			Toast.makeText(Select_Producto_Venta.this, "Se ha guardado la venta.", Toast.LENGTH_LONG).show();   
				        	Intent intent = new Intent(Select_Producto_Venta.this,Mis_Ventas.class);
				        	intent.putExtra("idvendedor",""+idvendedor);
				    		startActivity(intent);   	        		
		        		}
		        		});
	        		alertDialog.setIcon(R.drawable.ic_launcher);
	        		alertDialog.show();        	       		
	        	}else{
	        		Toast.makeText(this, "No hay cantidades válidas.", Toast.LENGTH_LONG).show();   
	        	}
	        		
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }	
	}
}
