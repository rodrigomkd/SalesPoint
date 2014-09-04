package com.example.salespoint;

import com.example.entidades.Ruta;
import com.example.sql.SQLiteQuery;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Ruta extends Activity {
	private EditText txtRuta;
	private EditText txtEstado;
	private EditText txtMunicipio;
	private EditText txtCiudad;
	private int idruta;
	private String activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update__ruta);
		
		Button button = (Button) this.findViewById(R.id.button2);
		button.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
		txtRuta = (EditText) findViewById(R.id.editText1);
		txtEstado = (EditText) findViewById(R.id.editText2);
		txtMunicipio = (EditText) findViewById(R.id.editText3);
		txtCiudad = (EditText) findViewById(R.id.editText4);
		Bundle reicieveParams = getIntent().getExtras();
		idruta = Integer.parseInt(reicieveParams.getString("idruta").trim());
		activity = reicieveParams.getString("activity");
		 if(idruta != 0){
			 SQLiteQuery sql = new SQLiteQuery(this);
			 Ruta r = sql.getRutaPorId(idruta);
			 txtRuta.setText(r.getRuta());
			 txtEstado.setText(r.getEstado().toString());
			 txtMunicipio.setText(r.getMunicipio().toString());
			 txtCiudad.setText(r.getCiudad().toString()); 
		 }
		 
		 ActionBar actionBar = getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        actionBar.setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.update__ruta, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
			 case android.R.id.home:
			Intent intentHome;
			try {
				intentHome = new Intent(Update_Ruta.this, Class.forName("com.example.salespoint."+activity));
				intentHome.putExtra("idruta", ""+idruta);
		 		startActivity(intentHome);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			 		
			 		return true;
			 default:
				 return super.onOptionsItemSelected(item);
		 }
	}
	public void onClickButton(View view){
		Intent intent = new Intent(Update_Ruta.this,Rutas.class);
		startActivity(intent);   		
	}
	
	public void click_button(View view){
		SQLiteQuery sql = new SQLiteQuery(this);
		Ruta r = new Ruta();
		r.setIdruta(idruta);
    	r.setRuta(txtRuta.getText().toString());
    	r.setEstado(txtEstado.getText().toString());
    	r.setMunicipio(txtMunicipio.getText().toString());
    	r.setCiudad(txtCiudad.getText().toString());
		sql.updateRuta(r);
		Toast.makeText(this, "Se guardaron los datos de la ruta.", Toast.LENGTH_LONG).show();   
        Intent intent = new Intent(Update_Ruta.this,Rutas.class);
		startActivity(intent);
	}

}
