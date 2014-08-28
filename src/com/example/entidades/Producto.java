package com.example.entidades;

public class Producto {
	private int idproducto;
	private String producto;
	private double pcompra;
	private double pventa;
	private int cantidad;
	private String descripcion;
	private String foto;
	
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public double getPcompra() {
		return pcompra;
	}
	public void setPcompra(double pcompra) {
		this.pcompra = pcompra;
	}
	public double getPventa() {
		return pventa;
	}
	public void setPventa(double pventa) {
		this.pventa = pventa;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
