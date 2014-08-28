package com.example.salespoint;

import com.example.entidades.Vendedor;
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

public class Update_Vendedores extends Activity {
	private EditText txtNombre;
	private EditText txtApepat;
	private EditText txtApemat;
	private EditText txtDomicilio;
	private EditText txtCelular;
	private EditText txtEmail;
	private EditText txtRfc;
	private int idvendedor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update__vendedores);
		
		Button button = (Button) this.findViewById(R.id.button2);
		button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		txtNombre = (EditText) findViewById(R.id.editText1);
		txtApepat = (EditText) findViewById(R.id.editText2);
		txtApemat = (EditText) findViewById(R.id.editText3);
		txtDomicilio = (EditText) findViewById(R.id.editText4);
		txtCelular = (EditText) findViewById(R.id.editText5);
		txtEmail = (EditText) findViewById(R.id.editText6);
		txtRfc = (EditText) findViewById(R.id.editText7);
		Bundle reicieveParams = getIntent().getExtras();
		idvendedor = Integer.parseInt(reicieveParams.getString("idvendedor").trim());
		 if(idvendedor != 0){
			 SQLiteQuery sql = new SQLiteQuery(this);
			 Vendedor v = sql.getVendedorPorId(idvendedor);
			 txtNombre.setText(v.getNombre()); 
				txtApepat.setText(v.getApepat()); 
				txtApemat.setText(v.getApemat());
				txtDomicilio.setText(v.getDomicilio());
				txtCelular.setText(v.getCelular());
				txtEmail.setText(v.getEmail());
				txtRfc.setText(v.getRfc());
		 }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.update__vendedores, menu);
		return true;
	}
	
	public void onClickButton(View view){
		Intent intent = new Intent(Update_Vendedores.this,Vendedores.class);
		startActivity(intent);   		
	}
	
	public void click_button(View view){
		SQLiteQuery sql = new SQLiteQuery(this);
		Vendedor v = new Vendedor();
		v.setIdvendedor(idvendedor);
    	v.setNombre(txtNombre.getText().toString());
    	v.setApepat(txtApepat.getText().toString());
    	v.setApemat(txtApemat.getText().toString());
    	v.setDomicilio(txtDomicilio.getText().toString());
    	v.setCelular(txtCelular.getText().toString());
    	v.setEmail(txtEmail.getText().toString());
    	v.setRfc(txtRfc.getText().toString());
		sql.updateVendedor(v);
		Toast.makeText(this, "Se guardaron los datos del vendedor.", Toast.LENGTH_LONG).show();   
        Intent intent = new Intent(Update_Vendedores.this,Vendedores.class);
		startActivity(intent);
	}

}
