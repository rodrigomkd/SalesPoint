package com.example.metodos;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salespoint.R;

public class ListArrayAdapterMenu extends ArrayAdapter<String>{
	    private LayoutInflater inflater;  
	      
	    public ListArrayAdapterMenu( Context context, List<String> list ) {  
	    	super(context, R.layout.listview_menu, R.id.textView, list);  
	         inflater = LayoutInflater.from(context) ;  
	    }  
	  
	   	@Override  
	    public View getView(final int position, View convertView, ViewGroup parent) {  
	    // Planet to display  
	      //Planet planet = (Planet) this.getItem( position );   
	      String item = this.getItem(position);
	      // The child views in each row.  
	      ImageView imageView ;   
	      TextView textView ;   
	        
	      // Create a new row view  
	      if ( convertView == null ) {  
	        convertView = inflater.inflate(R.layout.listview_menu, null);  
	          
	        // Find the child views.  
	        textView = (TextView) convertView.findViewById( R.id.textView );  
	        imageView = (ImageView) convertView.findViewById( R.id.imageView );  
	          
	        // Optimization: Tag the row with it's child views, so we don't have to   
	        // call findViewById() later when we reuse the row.  
	        convertView.setTag( new ListViewHolderMenu(textView,imageView) );  
	  
	        // If CheckBox is toggled, update the planet it is tagged with.  
	        imageView.setOnClickListener( new View.OnClickListener() {  
	        
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
	    	ListViewHolderMenu viewHolderMenu = (ListViewHolderMenu) convertView.getTag();  
	        imageView = viewHolderMenu.getImageView() ;  
	        textView = viewHolderMenu.getTextView() ;  
	      }  
	  
	      // Tag the CheckBox with the Planet it is displaying, so that we can  
	      // access the planet in onClick() when the CheckBox is toggled.  
	    //  checkBox.setTag( planet );   
	      switch(position){
	  			case 0:
      		imageView.setImageResource(R.drawable.client_32x32);
      		break;
	      	case 1:
	      		imageView.setImageResource(R.drawable.product_32x32);
	      		break;
	      	case 2: 
	      		imageView.setImageResource(R.drawable.rute_32x32);
	      		break;
	      	case 3:
	      		imageView.setImageResource(R.drawable.vendedor_32x32);
	      		break;
	    	case 4:
	      		imageView.setImageResource(R.drawable.ventas_32x32);
	      		break;
	    	case 5:
	      		imageView.setImageResource(R.drawable.veta_32x32);
	      		break;
	      }
	      
	       textView.setText(item);        
	      return convertView;  
	    }  
	   	
	   	@SuppressWarnings("deprecation")
		public static Drawable resizeImage(Context ctx, int resId, int w, int h) {
	   	 
	          // cargamos la imagen de origen
	          Bitmap BitmapOrg = BitmapFactory.decodeResource(ctx.getResources(),
	                                                          resId);
	 
	          int width = BitmapOrg.getWidth();
	          int height = BitmapOrg.getHeight();
	          int newWidth = w;
	          int newHeight = h;
	 
	          // calculamos el escalado de la imagen destino
	          float scaleWidth = ((float) newWidth) / width;
	          float scaleHeight = ((float) newHeight) / height;
	 
	          // para poder manipular la imagen
	          // debemos crear una matriz
	 
	          Matrix matrix = new Matrix();
	          // resize the Bitmap
	          matrix.postScale(scaleWidth, scaleHeight);
	 
	          // volvemos a crear la imagen con los nuevos valores
	          Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
	                                                     width, height, matrix, true);
	 
	          // si queremos poder mostrar nuestra imagen tenemos que crear un
	          // objeto drawable y así asignarlo a un botón, imageview...
	          return new BitmapDrawable(resizedBitmap);
	 
	        }
}
