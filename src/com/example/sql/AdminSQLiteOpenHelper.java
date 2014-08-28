package com.example.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
	
	  public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
	        super(context, nombre, factory, version);
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL("create table cliente(idcliente integer primary key, nombre text, apepat text, apemat text," +
	        		"fecnac text, domicilio text, celular text, email text)");
	        //tabla productos
	     //  db.execSQL("drop table if exists producto");
	       db.execSQL("create table producto(idproducto integer primary key, producto text, pcompra real, pventa real," +
	        		"cantidad integer, descripcion text, foto text)");
	       db.execSQL("create table ruta(idruta integer primary key, ruta text, estado text, municipio text," +
	        		"ciudad text)");
	       db.execSQL("create table vendedor(idvendedor integer primary key, nombre text, apepat text, apemat text," +
	        		" domicilio text, celular text, email text, rfc text)");
	       db.execSQL("create table vendedor_ruta(idvendedor integer, idruta integer)");
	       db.execSQL("create table cliente_ruta(idcliente integer, idruta integer)");
	       db.execSQL("create table venta(idventa integer primary key  AUTOINCREMENT, fecha DATETIME DEFAULT " +
	       		"CURRENT_TIMESTAMP, subtotal real, iva real, total real, idcliente integer, idvendedor integer, idruta integer)");
	       db.execSQL("create table detalleventa(iddetalleventa integer primary key  AUTOINCREMENT, idventa integer, " +
		       		"idproducto integer,cantidad integer)");
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
	        db.execSQL("drop table if exists cliente");
	        db.execSQL("create table cliente(idcliente integer primary key, nombre text, apepat text, apemat text," +
	        		"fecnac text, domicilio text, celular text, email text)");        
	    }  
}
