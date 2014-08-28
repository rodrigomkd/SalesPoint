package com.example.salespoint;


import com.example.entidades.cliente;
import com.example.sql.SQLiteQuery;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Detalle_cliente extends Activity {
	private final int MENU1 = 1;
	private final int MENU2 = 2;
	private final int MENU3 = 3;
	private final int MENU4 = 4;
	private final int MENU5 = 5;
	private TextView txtIdcliente;
	private TextView txtNombre;
	private TextView txtApepat;
	private TextView txtApemat;
	private TextView txtFechanac;
	private TextView txtDomicilio;
	private TextView txtCelular;
	private TextView txtEmail;
	private int idcliente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_cliente);
	   Bundle reicieveParams = getIntent().getExtras();
	    idcliente = Integer.parseInt(reicieveParams.getString("idcliente").trim());
		txtIdcliente = (TextView) findViewById(R.id.textView2);
		txtNombre = (TextView) findViewById(R.id.textView4);
		txtApepat = (TextView) findViewById(R.id.textView6);
		txtApemat = (TextView) findViewById(R.id.textView8);
		txtFechanac = (TextView) findViewById(R.id.textView10);
		txtDomicilio = (TextView) findViewById(R.id.textView12);
		txtCelular = (TextView) findViewById(R.id.textView14);
		txtEmail = (TextView) findViewById(R.id.textView16);	
		//text color
		txtIdcliente.setTextColor(Color.DKGRAY);
		txtNombre.setTextColor(Color.DKGRAY);
		txtApepat.setTextColor(Color.DKGRAY);
		txtApemat.setTextColor(Color.DKGRAY);
		txtFechanac.setTextColor(Color.DKGRAY);
		txtDomicilio.setTextColor(Color.DKGRAY);
		txtCelular.setTextColor(Color.DKGRAY);
		txtEmail.setTextColor(Color.DKGRAY);
		
		SQLiteQuery sql = new SQLiteQuery(this);
		cliente c = sql.consultaClientesPorId(idcliente);			
		txtIdcliente.setText(""+c.getIdcliente());
		txtNombre.setText(c.getNombre());
		txtApepat.setText(c.getApepat());
		txtApemat.setText(c.getApemat());
		txtDomicilio.setText(c.getDomicilio());
		txtFechanac.setText(c.getFecnac());
		txtCelular.setText(c.getCelular());
		txtEmail.setText(c.getEmail());			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem menu3 = menu.add(Menu.NONE, MENU1, 3, "Modificar Cliente");
        menu3.setAlphabeticShortcut('m');
        MenuItem menu2 = menu.add(Menu.NONE, MENU2, 3, "Eliminar Cliente");
        menu2.setAlphabeticShortcut('e');
        MenuItem menu4 = menu.add(Menu.NONE, MENU3, 3, "Ver Rutas");
        menu4.setAlphabeticShortcut('r');
        MenuItem menu5 = menu.add(Menu.NONE, MENU4, 3, "Clientes");
        menu5.setAlphabeticShortcut('v');
        MenuItem menu6 = menu.add(Menu.NONE, MENU5, 3, "Menú");
        menu6.setAlphabeticShortcut('p');
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
	        case MENU1:
	        	//modificar cliente
	        	Intent intent = new Intent(Detalle_cliente.this,Alta_cliente.class);
	        	intent.putExtra("idcliente",""+idcliente);
	    		startActivity(intent);   
	            return true;
	        case MENU2:
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);    
	    			// set title
	    			alertDialogBuilder.setTitle("Advertencia");
	     
	    			// set dialog message
	    			alertDialogBuilder
	    				.setMessage("¿Desea eliminar el cliente?")
	    				.setCancelable(false)
	    				.setPositiveButton("Si",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, close
	    						// current activity	    
	    						SQLiteQuery sql = new SQLiteQuery(Detalle_cliente.this);
	    						sql.eliminarCliente(idcliente); 
	    						//Toast.makeText(this, "Se ha eliminado el cliente...", Toast.LENGTH_LONG).show();
	    	    				Intent intent2 = new Intent(Detalle_cliente.this,MainActivity_Clientes.class);
	    	    	    		startActivity(intent2);   
	    					}
	    				  })
	    				.setNegativeButton("No",new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						// if this button is clicked, just close
	    						// the dialog box and do nothing

	    						dialog.cancel();
	    						
	    					}
	    				});	     
	    				// create alert dialog
	    				AlertDialog alertDialog = alertDialogBuilder.create();	     
	    				// show it
	    				alertDialog.show(); 	    		    				
	            return true;
	        case MENU3:
	        	//ver rutas
	        	Intent intentVendedores = new Intent(Detalle_cliente.this,Rutas_Cliente.class);
	        	intentVendedores.putExtra("idcliente",""+idcliente);
	    		startActivity(intentVendedores);   
	            return true;
	        case MENU4:
	        	//vendedores
	        	Intent intent4 = new Intent(Detalle_cliente.this,MainActivity_Clientes.class);
	    		startActivity(intent4);   
	            return true;
	        case MENU5:
	        	//menu
	        	Intent intent5 = new Intent(Detalle_cliente.this,MainActivity.class);
	    		startActivity(intent5);   
	            return true;
	         default:
	        	 return super.onOptionsItemSelected(item);
		 }
	}

}


