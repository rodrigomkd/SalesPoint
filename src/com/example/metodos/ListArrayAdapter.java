package com.example.metodos;

import java.util.ArrayList;
import java.util.List;
import com.example.salespoint.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListArrayAdapter extends ArrayAdapter<String>{
	  private LayoutInflater inflater;  
	    public List<Integer> ids = new ArrayList<Integer>();
	      
	    public ListArrayAdapter( Context context, List<String> list ) {  
	      super( context, R.layout.simplerow, R.id.rowTextView, list );  
	      // Cache the LayoutInflate to avoid asking for a new one each time.  
	      inflater = LayoutInflater.from(context) ;  
	    }  
	  
	   	@Override  
	    public View getView(final int position, View convertView, ViewGroup parent) {  
	    // Planet to display  
	      //Planet planet = (Planet) this.getItem( position );   
	      String item = this.getItem(position);
	      // The child views in each row.  
	      CheckBox checkBox ;   
	      TextView textView ;   
	        
	      // Create a new row view  
	      if ( convertView == null ) {  
	        convertView = inflater.inflate(R.layout.simplerow, null);  
	          
	        // Find the child views.  
	        textView = (TextView) convertView.findViewById( R.id.rowTextView );  
	        checkBox = (CheckBox) convertView.findViewById( R.id.CheckBox01 );  
	          
	        // Optimization: Tag the row with it's child views, so we don't have to   
	        // call findViewById() later when we reuse the row.  
	        convertView.setTag( new ListViewHolder(textView,checkBox) );  
	  
	        // If CheckBox is toggled, update the planet it is tagged with.  
	        checkBox.setOnClickListener( new View.OnClickListener() {  
	        
			public void onClick(View v) {  
		            CheckBox cb = (CheckBox) v ;  
		            if(cb.isChecked()){
		            }
	           // Planet planet = (Planet) cb.getTag();  
	           // planet.setChecked( cb.isChecked() );  
	          }  
	        });          
	      }  
	      // Reuse existing row view  
	      else {  
	        // Because we use a ViewHolder, we avoid having to call findViewById().  
	    	ListViewHolder viewHolder = (ListViewHolder) convertView.getTag();  
	        checkBox = viewHolder.getCheckBox() ;  
	        textView = viewHolder.getTextView() ;  
	      }  
	  
	      // Tag the CheckBox with the Planet it is displaying, so that we can  
	      // access the planet in onClick() when the CheckBox is toggled.  
	    //  checkBox.setTag( planet );   
	        
	      // Display planet data  
	       checkBox.setChecked( false);  
	       textView.setText(item);        
	      return convertView;  
	    }  
	   	    	    
}
