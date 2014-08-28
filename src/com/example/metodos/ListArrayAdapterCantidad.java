package com.example.metodos;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.entidades.Detalleventa;
import com.example.entidades.Producto;
import com.example.salespoint.R;

public class ListArrayAdapterCantidad extends ArrayAdapter<Detalleventa>{
	  private LayoutInflater inflater; 
	  private List<Producto> productos = new ArrayList<Producto>();
 	      
	    public ListArrayAdapterCantidad(Context context, List<Detalleventa> list, List<Producto>  productos) {  
	      super( context, R.layout.two_columns, R.id.rowTextView, list );  
	      inflater = LayoutInflater.from(context) ;  
	      this.productos = productos;
	    }  
	  
	   	@Override  
	    public View getView(final int position, View convertView, ViewGroup parent) {  
	    // Planet to display  
	      //Planet planet = (Planet) this.getItem( position );   
	   	Detalleventa item = this.getItem(position);
	      // The child views in each row.  
	      TextView cantidad;   
	      TextView textView ;   
	        
	      // Create a new row view  
	      if ( convertView == null ) {  
	        convertView = inflater.inflate(R.layout.two_columns, null);  
	          
	        // Find the child views.  
	        textView = (TextView) convertView.findViewById( R.id.rowTextView );  
	        cantidad = (TextView) convertView.findViewById( R.id.rowTextView2 );  
	          
	        // Optimization: Tag the row with it's child views, so we don't have to   
	        // call findViewById() later when we reuse the row.  
	        convertView.setTag( new ListViewHolderColumn(textView,cantidad) );  
	  
	        // If CheckBox is toggled, update the planet it is tagged with.  
        
	      }  
	      // Reuse existing row view  
	      else {  
	        // Because we use a ViewHolder, we avoid having to call findViewById().  
	    	  ListViewHolderColumn viewHolder = (ListViewHolderColumn) convertView.getTag();  
	        cantidad = viewHolder.getTextViewCantidad();  
	        textView = viewHolder.getTextView() ;  
	      }  
	
	    	Producto p = this.productos.get(position);
	       cantidad.setText(""+item.getCantidad()); 
	       textView.setText(p.getProducto());        
	      return convertView;  
	    }  
	   	    	    
}
