package com.example.entidades;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Detalleventa implements Serializable {
	private int iddetalleventa;
	private int idventa;
	private int idproducto;
	private int cantidad;
	
	public int getIddetalleventa() {
		return iddetalleventa;
	}
	public void setIddetalleventa(int iddetalleventa) {
		this.iddetalleventa = iddetalleventa;
	}
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
