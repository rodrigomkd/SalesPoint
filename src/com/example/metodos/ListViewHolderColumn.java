package com.example.metodos;


import android.widget.TextView;

public  class ListViewHolderColumn{
    private TextView cantidad ;
    private TextView textView ;
    public ListViewHolderColumn() {}
    public ListViewHolderColumn( TextView textView, TextView cantidad ) {
      this.cantidad = cantidad ;
      this.textView = textView ;
    }
    public TextView getTextViewCantidad() {
      return cantidad;
    }
    public void setTextViewCantidad(TextView textView) {
      this.cantidad = textView;
    }
    public TextView getTextView() {
      return textView;
    }
    public void setTextView(TextView textView) {
      this.textView = textView;
    }    
}

