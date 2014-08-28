package com.example.salespoint;

import com.example.entidades.Producto;
import com.example.sql.SQLiteQuery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.PorterDuff;

public class Update_Producto extends Activity {
	private EditText txtProducto;
	private EditText txtDescripcion;
	private EditText txtPventa;
	private EditText txtPcompra;
	private EditText txtCantidad;
	private int idproducto;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update__producto);
		
		Button button = (Button) this.findViewById(R.id.button2);
		button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		txtProducto = (EditText) findViewById(R.id.editText1);
		txtDescripcion = (EditText) findViewById(R.id.editText2);
		txtPcompra = (EditText) findViewById(R.id.editText3);
		txtPventa = (EditText) findViewById(R.id.editText4);
		txtCantidad = (EditText) findViewById(R.id.editText5);
		Bundle reicieveParams = getIntent().getExtras();
		idproducto = Integer.parseInt(reicieveParams.getString("idproducto").trim());
		 if(idproducto != 0){
			 SQLiteQuery sql = new SQLiteQuery(this);
			 Producto p = sql.getProductoPorId(idproducto);
			    txtProducto.setText(p.getProducto());
				txtDescripcion.setText(p.getDescripcion());
				txtPcompra.setText(""+p.getPcompra());
				txtPventa.setText(""+p.getPventa());
				txtCantidad.setText(""+p.getCantidad());	 
		 }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update__producto, menu);
		return true;
	}
	
	public void onClickButton(View view){
		Intent intent = new Intent(Update_Producto.this,MainActivity_Productos.class);
		startActivity(intent);   		
	}
	
	public void click_button(View view){
		SQLiteQuery sql = new SQLiteQuery(this);
		Producto p = new Producto();
		p.setIdproducto(idproducto);
		p.setProducto(txtProducto.getText().toString());
		p.setDescripcion(txtDescripcion.getText().toString());
		double pcompra = 0;
		if(!txtPcompra.getText().toString().isEmpty())
			pcompra = Double.parseDouble(txtPcompra.getText().toString());
		p.setPcompra(pcompra);
		double pventa = 0;
		if(!txtPventa.getText().toString().isEmpty())
			pventa = Double.parseDouble(txtPventa.getText().toString());
		p.setPventa(pventa);
		int cantidad = 0;
		if(!txtCantidad.getText().toString().isEmpty())
			cantidad = Integer.parseInt(txtCantidad.getText().toString());
		p.setCantidad(cantidad);
		sql.updateProducto(p);
		Toast.makeText(this, "Se guardaron los datos del producto.", Toast.LENGTH_LONG).show();   
        Intent intent = new Intent(Update_Producto.this,MainActivity_Productos.class);
		startActivity(intent);
	}

}
