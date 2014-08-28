package com.example.metodos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class Metodos {
	 //manejar fecha
	 @SuppressLint("SimpleDateFormat") 
	 public static String convertirFecha(Date date){
			DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String convertido = fechaHora.format(date);
			return convertido;
	 }
	 
	 @SuppressLint("SimpleDateFormat") 
	 public static Date convertirFecha(String date){
			DateFormat fechaHora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date convertido = null;
			try {
				convertido = fechaHora.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return convertido;
	 }
}
