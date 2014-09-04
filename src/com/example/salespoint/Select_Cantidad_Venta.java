package com.example.salespoint;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.sql.SQLiteQuery;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Select_Cantidad_Venta extends Activity {
	private TextView txtProducto;
	private TextView txtDescripcion;
	private TextView txtPrecio;
	private TextView txtCantidadDisponible;
	private EditText edtCantidad;
	private int idvendedor;
	private int idruta;
	private int idcliente;
	private int idproducto;
	private int cantidad;
	private List<Detalleventa> detalleventas = new ArrayList<Detalleventa>();
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select__cantidad__venta);
		
		Button button = (Button) this.findViewById(R.id.button2);
		button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		txtProducto = (TextView) findViewById(R.id.textView2);	
		txtDescripcion = (TextView) findViewById(R.id.textView4);	
		txtPrecio = (TextView) findViewById(R.id.textView6);	
		txtCantidadDisponible = (TextView) findViewById(R.id.textView8);	
		edtCantidad = (EditText) findViewById(R.id.editText1);	
		
		txtProducto.setTextColor(Color.DKGRAY);
		txtDescripcion.setTextColor(Color.DKGRAY);
		txtPrecio.setTextColor(Color.DKGRAY);
		txtCantidadDisponible.setTextColor(Color.DKGRAY);
		
		Intent startingIntent = getIntent();
		  if (startingIntent != null) {
		    //Bundle b = startingIntent.getBundleExtra("android.intent.extra.INTENT");
			  Bundle reicieveParams = getIntent().getExtras();
			   idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor"));
			   idruta = Integer.parseInt(reicieveParams.getString("idruta"));
			   idcliente = Integer.parseInt(reicieveParams.getString("idcliente"));
			   idproducto = Integer.parseInt(reicieveParams.getString("idproducto"));
			   cantidad = Integer.parseInt(reicieveParams.getString("cantidad"));
		       this.detalleventas = (List<Detalleventa>)getIntent().getSerializableExtra("detalleventas");
		  }
		  
		  if(cantidad > 0)
			  edtCantidad.setText(""+cantidad);
		  
		  SQLiteQuery sql = new SQLiteQuery(this);
		  Producto p = sql.getProductoPorId(idproducto);
		  txtProducto.setText(p.getProducto());
		  txtDescripcion.setText(p.getDescripcion());
		  txtPrecio.setText(""+p.getPventa());
		  txtCantidadDisponible.setText(""+p.getCantidad());
		  
		  ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);
	}

	public void aceptarButton(View view){
		if(edtCantidad.getText().toString().isEmpty()){
			 Toast.makeText(this, "Falta ingresar la cantidad.", Toast.LENGTH_SHORT).show();	
			 return;
		}
		int cantidadDisponible = Integer.parseInt(txtCantidadDisponible.getText().toString());
		int cantidad = Integer.parseInt(edtCantidad.getText().toString());
		if(cantidad > cantidadDisponible){
			 Toast.makeText(this, "Cantidad no disponible.", Toast.LENGTH_SHORT).show();	
			 return;
		}
		for(Detalleventa dv : detalleventas){
			if(dv.getIdproducto() == this.idproducto){
				dv.setCantidad(cantidad);
				break;
			}
		}
		Intent intent = new Intent(Select_Cantidad_Venta.this,Select_Producto_Venta.class);
     	//intent.putExtra("idproducto", ""+idproducto);
     	//intent.putExtra("cantidad", ""+cantidad);	
     	intent.putExtra("idvendedor", ""+idvendedor);	
     	intent.putExtra("idruta", ""+idruta);	
     	intent.putExtra("idcliente", ""+idcliente);	
     	intent.putExtra("detalleventas",(Serializable) detalleventas);
		startActivity(intent);   
		
	}
	
	public void cancelarButton(View view){
		Intent intent = new Intent(Select_Cantidad_Venta.this,Select_Producto_Venta.class);
     	intent.putExtra("idvendedor", ""+idvendedor);	
     	intent.putExtra("idruta", ""+idruta);	
     	intent.putExtra("idcliente", ""+idcliente);	
     	intent.putExtra("detalleventas",(Serializable) detalleventas);
		startActivity(intent);   
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
		 	case android.R.id.home:
		 		Intent intent = new Intent(Select_Cantidad_Venta.this,Select_Producto_Venta.class);
             	intent.putExtra("idvendedor", ""+idvendedor);	
             	intent.putExtra("idruta", ""+idruta);	
             	intent.putExtra("idcliente", ""+idcliente);	
             	intent.putExtra("detalleventas",(Serializable) detalleventas);
		 		startActivity(intent);
		 	return true;
		 	default:
		 			return super.onOptionsItemSelected(item);
		 }
	}
	
}
