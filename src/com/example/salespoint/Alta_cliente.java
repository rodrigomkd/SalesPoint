package com.example.salespoint;


import com.example.entidades.cliente;
import com.example.sql.SQLiteQuery;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alta_cliente extends Activity {
	private EditText txtNombre;
	private EditText txtApepat;
	private EditText txtApemat;
	private EditText txtFecha;
	private EditText txtDomicilio;
	private EditText txtCelular;
	private EditText txtEmail;
	private int idcliente;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alta_cliente);
		
		Button button = (Button) this.findViewById(R.id.button2);
		button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		txtNombre = (EditText) findViewById(R.id.editText2);
		txtApepat = (EditText) findViewById(R.id.editText3);
		txtApemat = (EditText) findViewById(R.id.editText4);
		txtFecha = (EditText) findViewById(R.id.editText5);
		txtDomicilio = (EditText) findViewById(R.id.editText6);
		txtCelular = (EditText) findViewById(R.id.editText7);
		txtEmail = (EditText) findViewById(R.id.editText1);
		Bundle reicieveParams = getIntent().getExtras();
		 idcliente = Integer.parseInt(reicieveParams.getString("idcliente").trim());
		 if(idcliente != 0){
			 SQLiteQuery sql = new SQLiteQuery(this);
			 cliente c = sql.consultaClientesPorId(idcliente);
			 txtNombre.setText(c.getNombre());
				txtApepat.setText(c.getApepat());
				txtApemat.setText(c.getApemat());
				txtFecha.setText(c.getFecnac());
				txtDomicilio.setText(c.getDomicilio());
				txtCelular.setText(c.getCelular());
				txtEmail.setText(c.getEmail());			 
		 }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	public void onClickButton(View view){
		Intent intent = new Intent(Alta_cliente.this,MainActivity_Clientes.class);
		startActivity(intent);   		
	}
	
	public void click_button(View view){
		SQLiteQuery sql = new SQLiteQuery(this);
		cliente c = new cliente();
		c.setIdcliente(idcliente);
		c.setNombre(txtNombre.getText().toString());
		c.setApepat(txtApepat.getText().toString());
		c.setApemat(txtApemat.getText().toString());
		c.setFecnac(txtFecha.getText().toString());
		c.setDomicilio(txtDomicilio.getText().toString());
		c.setCelular(txtCelular.getText().toString());
		c.setEmail(txtEmail.getText().toString());
		sql.updateCliente(c);
		Toast.makeText(this, "Se guardaron los datos del cliente.", Toast.LENGTH_LONG).show();   
        Intent intent = new Intent(Alta_cliente.this,MainActivity_Clientes.class);
		startActivity(intent);
	}
	
}
