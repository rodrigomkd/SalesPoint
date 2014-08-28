package com.example.sql;


import java.util.ArrayList;

import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.entidades.Ruta;
import com.example.entidades.Vendedor;
import com.example.entidades.Venta;
import com.example.entidades.cliente;
import com.example.metodos.Metodos;
import com.example.sql.AdminSQLiteOpenHelper;

public class SQLiteQuery {
	private Context context;
	
	public SQLiteQuery(Context context){
		this.context = context;
	}
	
	public List<cliente> getClientes() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select idcliente, nombre,apepat,apemat,fecnac,domicilio,celular,email from cliente " +
                "ORDER BY nombre", null);
        List<cliente> items = new ArrayList<cliente>();
        while(fila.moveToNext()){  
        	cliente c = new cliente();
        	 c.setIdcliente(Integer.parseInt(fila.getString(0)));
             c.setNombre(fila.getString(1));
             c.setApepat(fila.getString(2));
             c.setApemat(fila.getString(3));
            c.setFecnac(fila.getString(4));
            c.setDomicilio(fila.getString(5));
             c.setCelular(fila.getString(6));
             c.setEmail(fila.getString(7));
        	items.add(c);  	
        }
       
        bd.close();
        return items;
    }
	
	public cliente consultaClientesPorId(int idcliente) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select idcliente, nombre,apepat,apemat,fecnac,domicilio,celular,email from cliente " +
                "WHERE idcliente = "+idcliente, null);
        cliente c = new cliente();
        if (fila.moveToFirst()) {
            c.setIdcliente(Integer.parseInt(fila.getString(0)));
            c.setNombre(fila.getString(1));
            c.setApepat(fila.getString(2));
            c.setApemat(fila.getString(3));
           c.setFecnac(fila.getString(4));
           c.setDomicilio(fila.getString(5));
            c.setCelular(fila.getString(6));
            c.setEmail(fila.getString(7));
        }

        bd.close();
        return c;
	}
	
	public void updateCliente(cliente c){
		AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro=new ContentValues();
        registro.put("nombre",c.getNombre() );
        registro.put("apepat",c.getApepat());
        registro.put("apemat",c.getApemat()); 
        registro.put("fecnac",c.getFecnac()); 
        registro.put("domicilio",c.getDomicilio()); 
        registro.put("celular",c.getCelular()); 
        registro.put("email",c.getEmail()); 
		if(c.getIdcliente() == 0){
			 registro.put("idcliente", getIdcliente());
			 bd.insert("cliente", null, registro);	
		}	           
		else{
		        bd.update("cliente", registro, "idcliente = "+c.getIdcliente(), null);		             		 
		}
		 bd.close();			
	}
	
	public int getIdcliente() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,
                "ventas", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor fila = bd.rawQuery(
                "select max(idcliente) from cliente ", null);
        int idcliente = 0;
        if(fila.moveToNext())
        	idcliente = fila.getInt(0);
        bd.close();
        idcliente++;
        return idcliente;
    }

	 public void eliminarCliente(int idcliente) {
	        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        bd.delete("cliente", "idcliente = "+idcliente+"",null);
	        bd.close();	          
	    }
	 
	 ///tabla producto
	 public List<Producto> getProductos() {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idproducto, producto, pcompra, pventa, cantidad, descripcion, foto FROM producto " +
	                "ORDER BY producto", null);
	        List<Producto> items = new ArrayList<Producto>();
	        while(fila.moveToNext()){   
	        	Producto p = new Producto();
	        	p.setIdproducto(fila.getInt(0));
	        	p.setProducto(fila.getString(1));
	        	p.setPcompra(Double.parseDouble(fila.getString(2)));
	        	p.setPventa(Double.parseDouble(fila.getString(3)));
	        	p.setCantidad(fila.getInt(4));
	        	p.setDescripcion(fila.getString(5));
	        	p.setFoto(fila.getString(6));
	        	items.add(p);  	
	        }
	       
	        bd.close();
	        return items;
	    }
	 
		public Producto getProductoPorId(int idproducto) {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idproducto, producto, pcompra, pventa, cantidad, descripcion, foto FROM producto " +
	                "WHERE idproducto = "+idproducto, null);
	        Producto p = new Producto();
	        if (fila.moveToFirst()) {
	        	p.setIdproducto(fila.getInt(0));
	        	p.setProducto(fila.getString(1));
	        	p.setPcompra(Double.parseDouble(fila.getString(2)));
	        	p.setPventa(Double.parseDouble(fila.getString(3)));
	        	p.setCantidad(fila.getInt(4));
	        	p.setDescripcion(fila.getString(5));
	        	p.setFoto(fila.getString(6));
	        }

	        bd.close();
	        return p;
		}
		
	 public void updateProducto(Producto p){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("producto",p.getProducto());
	        registro.put("descripcion",p.getDescripcion());
	        registro.put("pventa",p.getPventa()); 
	        registro.put("pcompra",p.getPcompra()); 
	        registro.put("cantidad",p.getCantidad()); 
	        registro.put("foto", "foto");
			if(p.getIdproducto() == 0){
				 registro.put("idproducto", getId("producto","idproducto")); 
				 bd.insert("producto", null, registro);	
			}	           
			else{
			        bd.update("producto", registro, "idproducto = "+p.getIdproducto(), null);		             		 
			}
			 bd.close();			
		}
	 
	 public int getId(String tabla,String idtabla) {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context,
	                "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select max("+idtabla+") from "+tabla, null);
	        int id = 0;
	        if(fila.moveToNext())
	        	id = fila.getInt(0);
	        bd.close();
	        id++;
	        return id;
	    }
	 
	 public void eliminar(String tabla, String idtabla, int ideliminar) {
	        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        bd.delete(tabla, idtabla + " = " + ideliminar+"",null);
	        bd.close();	          
	    }
	 
	 
	 //tabla ruta
	 public List<Ruta> getRutas() {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idruta, ruta, estado, municipio, ciudad FROM ruta " +
	                "ORDER BY ruta", null);
	        List<Ruta> items = new ArrayList<Ruta>();
	        while(fila.moveToNext()){   
	        	Ruta r = new Ruta();
	        	r.setIdruta(fila.getInt(0));
	        	r.setRuta(fila.getString(1));
	        	r.setEstado(fila.getString(2));
	        	r.setMunicipio(fila.getString(3));
	        	r.setCiudad(fila.getString(4));
	        	items.add(r);  	
	        }
	       
	        bd.close();
	        return items;
	    }
	 
	 public void updateRuta(Ruta r){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("ruta",r.getRuta());
	        registro.put("estado",r.getEstado());
	        registro.put("municipio",r.getMunicipio()); 
	        registro.put("ciudad",r.getCiudad()); 
			if(r.getIdruta() == 0){
				 registro.put("idruta", getId("ruta","idruta"));
				 bd.insert("ruta", null, registro);	   			
			}	           
			else{
			        bd.update("ruta", registro, "idruta = "+r.getIdruta(), null);		             		 
			}
			 bd.close();			
		}
	 
	 public Ruta getRutaPorId(int idruta) {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idruta, ruta, estado, municipio, ciudad FROM ruta " +
	                "WHERE idruta = "+idruta, null);
	        Ruta r = new Ruta();
	        if (fila.moveToFirst()) {
	        	r.setIdruta(fila.getInt(0));
	        	r.setRuta(fila.getString(1));
	        	r.setEstado(fila.getString(2));
	        	r.setMunicipio(fila.getString(3));
	        	r.setCiudad(fila.getString(4));
	        }

	        bd.close();
	        return r;
		}
	 
	 //tabla vendedor
	 public List<Vendedor> getVendedores() {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idvendedor, nombre, apepat, apemat, domicilio, celular, email, rfc FROM vendedor " +
	                "ORDER BY nombre", null);
	        List<Vendedor> items = new ArrayList<Vendedor>();
	        while(fila.moveToNext()){   
	        	Vendedor v = new Vendedor();
	        	v.setIdvendedor(fila.getInt(0));
	        	v.setNombre(fila.getString(1));
	        	v.setApepat(fila.getString(2));
	        	v.setApemat(fila.getString(3));
	        	v.setDomicilio(fila.getString(4));
	        	v.setCelular(fila.getString(5));
	        	v.setEmail(fila.getString(6));
	        	v.setRfc(fila.getString(7));
	        	items.add(v);  	
	        }
	       
	        bd.close();
	        return items;
	    }
	 
	 public void updateVendedor(Vendedor v){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("nombre",v.getNombre());
	        registro.put("apepat",v.getApepat());
	        registro.put("apemat",v.getApemat()); 
	        registro.put("domicilio",v.getDomicilio()); 
	        registro.put("celular",v.getCelular()); 
	        registro.put("email",v.getEmail()); 
	        registro.put("rfc",v.getRfc()); 
			if(v.getIdvendedor() == 0){
				 registro.put("idvendedor", getId("vendedor","idvendedor"));
				 bd.insert("vendedor", null, registro);	 
			}	           
			else{
			        bd.update("vendedor", registro, "idvendedor = "+v.getIdvendedor(), null);		             		 
			}
			 bd.close();			
		}
	 
	 public Vendedor getVendedorPorId(int idvendedor) {
	        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idvendedor, nombre, apepat, apemat, domicilio, celular, email, rfc FROM vendedor " +
	                "WHERE idvendedor = "+idvendedor, null);
	        Vendedor v = new Vendedor();
	        if (fila.moveToFirst()) {
	        	v.setIdvendedor(fila.getInt(0));
	        	v.setNombre(fila.getString(1));
	        	v.setApepat(fila.getString(2));
	        	v.setApemat(fila.getString(3));
	        	v.setDomicilio(fila.getString(4));
	        	v.setCelular(fila.getString(5));
	        	v.setEmail(fila.getString(6));
	        	v.setRfc(fila.getString(7));
	        }

	        bd.close();
	        return v;
		}
	
	 //vendedor - ruta
	 public List<Ruta> getRutasPorIdVendedor(int idvendedor){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select r.idruta, r.ruta, r.estado, r.municipio, r.ciudad FROM vendedor_ruta vr " +
	                "INNER JOIN ruta r ON vr.idruta = r.idruta WHERE vr.idvendedor = "+idvendedor+ "" +
	                " ORDER BY r.ruta", null);
	        List<Ruta> items = new ArrayList<Ruta>();
	        while(fila.moveToNext()){   
	        	Ruta r = new Ruta();
	        	r.setIdruta(fila.getInt(0));
	        	r.setRuta(fila.getString(1));
	        	r.setEstado(fila.getString(2));
	        	r.setMunicipio(fila.getString(3));
	        	r.setCiudad(fila.getString(4));
	        	items.add(r);  	
	        }
	       
	        bd.close();
	        return items;	 
	 }
	 
	 public List<Ruta> getRutasSinVendedor(int idvendedor){
		 List<Ruta> allRutas = getRutas();
		 List<Ruta> rutasVendedor = getRutasPorIdVendedor(idvendedor);
		 List<Ruta> temporal = new ArrayList<Ruta>();
		 for(Ruta allRuta : allRutas){
			 if(rutasVendedor.isEmpty()){
				 temporal.add(allRuta);
			 }else{
				 if(!contieneRuta(rutasVendedor,allRuta.getIdruta()))
					 temporal.add(allRuta);				 
			 }
		 }
		 return temporal;
	 }
	 
	 public boolean contieneRuta(List<Ruta> rutas, int idruta){
		 for(Ruta r : rutas){
			 if(r.getIdruta() == idruta)
				 return true;
		 }
		 return false;
	 }
	 
	 public void insertVendedorRuta(int idvendedor, int idruta){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("idvendedor",idvendedor);
	        registro.put("idruta",idruta);
			bd.insert("vendedor_ruta", null, registro);	 
			bd.close();			
	 }
	 
	 public void eliminarRutaVendedor(int idvendedor, int idruta){
		 	AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        bd.delete("vendedor_ruta", "idvendedor = "+idvendedor+" AND idruta = "+idruta,null);
	        bd.close();	
	 }
	 
	 //Rutas - cliente
	 public List<Ruta> getRutasPorIdCliente(int idcliente){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select r.idruta, r.ruta, r.estado, r.municipio, r.ciudad FROM cliente_ruta vr " +
	                "INNER JOIN ruta r ON vr.idruta = r.idruta WHERE vr.idcliente = "+idcliente+ "" +
	                " ORDER BY r.ruta", null);
	        List<Ruta> items = new ArrayList<Ruta>();
	        while(fila.moveToNext()){   
	        	Ruta r = new Ruta();
	        	r.setIdruta(fila.getInt(0));
	        	r.setRuta(fila.getString(1));
	        	r.setEstado(fila.getString(2));
	        	r.setMunicipio(fila.getString(3));
	        	r.setCiudad(fila.getString(4));
	        	items.add(r);  	
	        }
	       
	        bd.close();
	        return items;	 
	 }
	 
	 public List<Ruta> getRutasSinCliente(int idcliente){
		 List<Ruta> allRutas = getRutas();
		 List<Ruta> rutasCliente = getRutasPorIdCliente(idcliente);
		 List<Ruta> temporal = new ArrayList<Ruta>();
		 for(Ruta allRuta : allRutas){
			 if(rutasCliente.isEmpty()){
				 temporal.add(allRuta);
			 }else{
				 if(!contieneRuta(rutasCliente,allRuta.getIdruta()))
					 temporal.add(allRuta);				 
			 }
		 }
		 return temporal;
	 }
	 
	 public void insertClienteRuta(int idcliente, int idruta){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("idcliente",idcliente);
	        registro.put("idruta",idruta);
			bd.insert("cliente_ruta", null, registro);	 
			bd.close();			
	 }
	 
	 public void eliminarRutaCliente(int idcliente, int idruta){
		 	AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd=admin.getWritableDatabase();
	        bd.delete("cliente_ruta", "idcliente = "+idcliente+" AND idruta = "+idruta,null);
	        bd.close();	
	 }
	 
	 public List<cliente> getClientesPorIdRuta(int idruta){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select c.idcliente, c.nombre,c.apepat,c.apemat,c.fecnac,c.domicilio,c.celular,c.email FROM cliente c " +
	                "INNER JOIN cliente_ruta cr ON cr.idcliente = c.idcliente WHERE cr.idruta = "+idruta+ "" +
	                " ORDER BY c.nombre", null);
	        List<cliente> items = new ArrayList<cliente>();
	        while(fila.moveToNext()){   
	        	cliente c = new cliente();
	        	c.setIdcliente(fila.getInt(0));
	        	c.setNombre(fila.getString(1));
	        	c.setApepat(fila.getString(2));
	        	c.setApemat(fila.getString(3));
	        	c.setFecnac(fila.getString(4));
	        	c.setDomicilio(fila.getString(5));
	        	c.setCelular(fila.getString(6));
	        	c.setEmail(fila.getString(7));
	        	items.add(c);  	
	        }
	       
	        bd.close();
	        return items;	 
	 }
	 
	 //venta
	 public void insertVenta(Venta v){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("fecha",Metodos.convertirFecha(v.getFecha()));
	        registro.put("subtotal",v.getSubtotal());
	        registro.put("iva",v.getIva());
	        registro.put("total",v.getTotal());
	        registro.put("idcliente",v.getIdcliente());
	        Log.i("insert idcliente en venta",""+v.getIdcliente());
	        registro.put("idruta",v.getIdruta());
	        registro.put("idvendedor",v.getIdvendedor());
	        
			bd.insert("venta", null, registro);	 
			bd.close();			
	 }
	 
	 public void insertDetalleVenta(Detalleventa dv){
			AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1);
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        ContentValues registro=new ContentValues();
	        registro.put("idventa",dv.getIdventa());
	        registro.put("idproducto",dv.getIdproducto());
	        registro.put("cantidad", dv.getCantidad());
			bd.insert("detalleventa", null, registro);	 
			bd.close();			
	 }
	 
	 public List<Venta> getVentasPorIdVendedor(int idvendedor){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idventa, fecha, subtotal, iva, total, idcliente, idruta, idvendedor " +
	                "FROM venta WHERE idvendedor = "+idvendedor, null);
	       List<Venta> ventas = new ArrayList<Venta>();
	        while(fila.moveToNext()){   
	        	Venta v = new Venta();
	        	v.setIdventa(fila.getInt(0));
	        	v.setFecha(Metodos.convertirFecha(""+fila.getString(1)));
	        	v.setSubtotal(Double.parseDouble(""+fila.getString(2)));
	        	v.setIva(Double.parseDouble(""+fila.getString(3)));
	        	v.setTotal(Double.parseDouble(""+fila.getString(4)));
	        	v.setIdcliente(fila.getInt(5));
	        	v.setIdruta(fila.getInt(6));
	        	v.setIdvendedor(fila.getInt(7));
	        	ventas.add(v);
	        }
	       
	        bd.close();
	        return ventas;	 
		 
	 }
	 
	 public Venta getVentaPorIdVenta(int idventa){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select idventa, fecha, subtotal, iva, total, idcliente, idruta, idvendedor " +
	                "FROM venta WHERE idventa = "+idventa, null);
	       Venta v = new Venta();
	        while(fila.moveToNext()){   
	        	v.setIdventa(fila.getInt(0));
	        	v.setFecha(Metodos.convertirFecha(""+fila.getString(1)));
	        	v.setSubtotal(Double.parseDouble(""+fila.getString(2)));
	        	v.setIva(Double.parseDouble(""+fila.getString(3)));
	        	v.setTotal(Double.parseDouble(""+fila.getString(4)));
	        	v.setIdcliente(fila.getInt(5));
	        	v.setIdruta(fila.getInt(6));
	        	v.setIdvendedor(fila.getInt(7));
	        }
	       
	        bd.close();
	        return v;	 	 
	 }
	 
	 //detalleventa
	 public List<Detalleventa> getDetalleventaPorIdVenta(int idventa){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select iddetalleventa, idventa, idproducto, cantidad " +
	                "FROM detalleventa WHERE idventa = "+idventa, null);
	       List<Detalleventa> dventas = new ArrayList<Detalleventa>();
	        while(fila.moveToNext()){   
	        	Detalleventa dv = new Detalleventa();
	        	dv.setIddetalleventa(fila.getInt(0));
	        	dv.setIdventa(fila.getInt(1));
	        	dv.setIdproducto(fila.getInt(2));
	        	dv.setCantidad(fila.getInt(3));
	        	dventas.add(dv);
	        }
	       
	        bd.close();
	        return dventas;	 	 
	 }
	 
	 public Detalleventa getDetalleventaPorId(int iddetalleventa){
		   AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "ventas", null, 1); 
	        SQLiteDatabase bd = admin.getWritableDatabase();
	        Cursor fila = bd.rawQuery(
	                "select iddetalleventa, idventa, idproducto, cantidad " +
	                "FROM detalleventa WHERE iddetalleventa = "+iddetalleventa, null);
	       Detalleventa dv = new Detalleventa();
	        while(fila.moveToNext()){   
	        	dv.setIddetalleventa(fila.getInt(0));
	        	dv.setIdventa(fila.getInt(1));
	        	dv.setIdproducto(fila.getInt(2));
	        	dv.setCantidad(fila.getInt(3));
	        }
	       
	        bd.close();
	        return dv;	 	 
	 }
	 
}
