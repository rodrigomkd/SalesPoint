package com.example.metodos;

import android.widget.ImageView;
import android.widget.TextView;

public class ListViewHolderMenu {
	   private ImageView imageView ;
	    private TextView textView ;
	    
	    public ListViewHolderMenu() {}
	    public ListViewHolderMenu( TextView textView, ImageView imageView ) {
	      this.imageView = imageView;
	      this.textView = textView ;
	    }
	    public ImageView getImageView() {
	      return imageView;
	    }
	    public void setImageView(ImageView imageView) {
	      this.imageView = imageView;
	    }
	    public TextView getTextView() {
	      return textView;
	    }
	    public void setTextView(TextView textView) {
	      this.textView = textView;
	    }    
}
