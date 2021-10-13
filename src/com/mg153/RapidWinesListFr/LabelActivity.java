package com.mg153.RapidWinesListFr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.graphics.Matrix;
import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;
import com.mg153.RapidWinesListFr.Vin;
import com.mg153.RapidWinesListFr.R;

import android.net.Uri;
import android.provider.MediaStore; 
import java.io.*;

public class LabelActivity extends Activity {
 
/*	   ImageView mImaView; */
	   ImageView imageView;
	   Bitmap myImg;
	   String imgPath;
	   long selcursor;
	   private VinOperations VinDBoperation;
	   int r;
	   int nopic;
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
		    selcursor = extras.getLong("KEY"); 
		    
		 VinDBoperation = new VinOperations(this);
	     VinDBoperation.open();

		Vin stud = null;
		stud = VinDBoperation.goid(selcursor);
		imgPath = stud.getimagepath();  
		nopic = 1;

			File folderimg = Environment.getExternalStorageDirectory();
			String fileName = imgPath;
			File myFileimg = new File(fileName);

        	if (myFileimg.exists()) {
        		
        		
        		BitmapFactory.Options options = new BitmapFactory.Options();
        		options.inJustDecodeBounds = true;
   		        myImg = BitmapFactory.decodeFile(imgPath, options);
        		if (options.outWidth != -1 && options.outHeight != -1) {
        		    // This is an image file.
		            setContentView(R.layout.label);
		            nopic = 0;

		  	      /**
		  	      * Rotation
		  	      */ 
		  		 imageView = (ImageView) findViewById(R.id.ImgView1);
		  	       /* Bitmap myImg = BitmapFactory.decodeResource(getResources(), R.drawable.label); */
	   		        myImg = BitmapFactory.decodeFile(imgPath);
		  		     imageView.setImageBitmap(myImg); 
		  		     r=0;
		  	
		  	  /*     Matrix matrix = new Matrix();
		  	       matrix.postRotate(270);

		  	       Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
		  	               matrix, true);

		  	       imageView.setImageBitmap(rotated);  */
		  		           
		  	       
		  	       usingSimpleImage(imageView);
		  	        
		  	        Toast.makeText(LabelActivity.this, "Taper deux fois pour réduire / agrandir / bouton menu pour retourner", Toast.LENGTH_SHORT).show();

        		}
        		else {
        		    // This is not an image file.
      			    setContentView(R.layout.nolabel);
        		}

        	}
            	else {
			    setContentView(R.layout.nolabel);
            	}      

		 }
	    }

	    public void usingSimpleImage(ImageView imageView) {
	        ImageAttacher mAttacher = new ImageAttacher(imageView);
	        ImageAttacher.MAX_ZOOM = 4.0f; // Double the current Size
	        ImageAttacher.MIN_ZOOM = 1.0f; // Half the current Size
	        MatrixChangeListener mMaListener = new MatrixChangeListener();
	        mAttacher.setOnMatrixChangeListener(mMaListener);
	        PhotoTapListener mPhotoTap = new PhotoTapListener();
	        mAttacher.setOnPhotoTapListener(mPhotoTap);

	    }
	    

	    private class PhotoTapListener implements OnPhotoTapListener {

	        @Override
	        public void onPhotoTap(View view, float x, float y) {
	        }
	    }

	    private class MatrixChangeListener implements OnMatrixChangedListener {

	        @Override
	        public void onMatrixChanged(RectF rect) {

	        }
	    }
	    
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if ( keyCode == KeyEvent.KEYCODE_MENU ) {

	        if (nopic==0) {
	            /**
	   	      * Rotation
	   	      */ 
	         r=r+1;
	         int rr = 0;
	         switch(r)
	            { case 1: rr=270; break;
	              case 2: rr=540; break;
	              case 3: rr=-270; break;
	              case 4: rr=0; r=0; break;
                } 
	   		 Matrix matrix = new Matrix();
	   	     matrix.postRotate(rr);
	   	       Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
	   	               matrix, true);
	   	     imageView.setImageBitmap(rotated); 
	
	        	}
	        }
	        return super.onKeyDown(keyCode, event);
	    }
	    
	}
