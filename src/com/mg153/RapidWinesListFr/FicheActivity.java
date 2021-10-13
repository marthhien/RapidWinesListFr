package com.mg153.RapidWinesListFr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.mg153.RapidWinesListFr.R;
import com.mg153.RapidWinesListFr.Vin;

import java.io.*;
import java.util.UUID;

import android.net.Uri;
import android.provider.MediaStore; 

public class FicheActivity extends Activity {
 	
	Uri imageUri;
	final int TAKE_PICTURE = 115;
	
	ToggleButton togglefavorite;
	ToggleButton star1button;
	ToggleButton star2button;
	ToggleButton star3button;
	ToggleButton star4button;
	ToggleButton star5button;
	int nbstars;
	private VinOperations VinDBoperation;
	boolean chx;
	long selcursor;
	String imgPath;
	String nomunique;
	String nomunique2;
	String Nomfich;
	String NomID;
	int fav;
	
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		
		togglefavorite = (ToggleButton) findViewById(R.id.toggle);
		togglefavorite.setText("");
		fav = 0;
		imgPath = "nopicture";
		nbstars = 0;
		
	/*	TextView txt1=(TextView) findViewById(R.id.textpays);
		txt1.setOnClickListener(new OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	selectPays(v);
		        }
		    }); */

		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
		    selcursor = extras.getLong("KEY"); 
		    
		 VinDBoperation = new VinOperations(this);
	     VinDBoperation.open();

/* lire l'enr */
		
		Vin stud = null;
		stud = VinDBoperation.goid(selcursor);
	  	EditText text1 = (EditText) findViewById(R.id.editnom);
	  	EditText text2 = (EditText) findViewById(R.id.editmaison);
	  	EditText text3 = (EditText) findViewById(R.id.editpays);
	  	EditText text4 = (EditText) findViewById(R.id.editregion);
	  	EditText text5 = (EditText) findViewById(R.id.editcouleur);
	  	EditText text6 = (EditText) findViewById(R.id.editmille);
	  	EditText text7 = (EditText) findViewById(R.id.editprix);
	  	EditText text8 = (EditText) findViewById(R.id.edital);
	  	EditText text9 = (EditText) findViewById(R.id.editcode);
	  	EditText text10 = (EditText) findViewById(R.id.editnote);
	  	EditText text11 = (EditText) findViewById(R.id.editapprec);
		text1.setText(stud.getnom());
		text2.setText(stud.getmaison());
		text3.setText(stud.getpays()); 	  	
		text4.setText(stud.getregion()); 	  	
		text5.setText(stud.getcouleur()); 	  	
		text6.setText(stud.getmille()); 	  	
		text7.setText(stud.getprix()); 	  	
		text8.setText(stud.getalcool()); 	  	
		text9.setText(stud.getcode()); 	  	
		text10.setText(stud.getnote()); 
		String txt10;
		txt10 = stud.getnote();

		if (txt10.trim().equals("1 étoile")) {
   		    nbstars = 1;
 		    star1button = (ToggleButton) findViewById(R.id.star1);
 		    star1button.setChecked(true);
 		    star2button = (ToggleButton) findViewById(R.id.star2);
		    star2button.setChecked(false);
 		    star3button = (ToggleButton) findViewById(R.id.star3);
		    star3button.setChecked(false);
 		    star4button = (ToggleButton) findViewById(R.id.star4);
		    star4button.setChecked(false);
 		    star5button = (ToggleButton) findViewById(R.id.star5);
		    star5button.setChecked(false);
		    }

		if (txt10.trim().equals("2 étoiles")) {
   		    nbstars = 2;
  		    star1button = (ToggleButton) findViewById(R.id.star1);
  		    star1button.setChecked(true);
  		    star2button = (ToggleButton) findViewById(R.id.star2);
 		    star2button.setChecked(true);
  		    star3button = (ToggleButton) findViewById(R.id.star3);
 		    star3button.setChecked(false);
  		    star4button = (ToggleButton) findViewById(R.id.star4);
 		    star4button.setChecked(false);
  		    star5button = (ToggleButton) findViewById(R.id.star5);
 		    star5button.setChecked(false);
		    }

		if (txt10.trim().equals("3 étoiles")) {
   		    nbstars = 3;
   		    star1button = (ToggleButton) findViewById(R.id.star1);
   		    star1button.setChecked(true);
   		    star2button = (ToggleButton) findViewById(R.id.star2);
  		    star2button.setChecked(true);
   		    star3button = (ToggleButton) findViewById(R.id.star3);
  		    star3button.setChecked(true);
   		    star4button = (ToggleButton) findViewById(R.id.star4);
  		    star4button.setChecked(false);
   		    star5button = (ToggleButton) findViewById(R.id.star5);
  		    star5button.setChecked(false);
		    }
  		  
		if (txt10.trim().equals("4 étoiles")) {
   		    nbstars = 4;
   		    star1button = (ToggleButton) findViewById(R.id.star1);
    		star1button.setChecked(true);
      		star2button = (ToggleButton) findViewById(R.id.star2);
      		star2button.setChecked(true);
       		star3button = (ToggleButton) findViewById(R.id.star3);
      		star3button.setChecked(true);
       		star4button = (ToggleButton) findViewById(R.id.star4);
      		star4button.setChecked(true);
       		star5button = (ToggleButton) findViewById(R.id.star5);
      		star5button.setChecked(false);
		    }
      		
		if (txt10.trim().equals("5 étoiles")) {
   		    nbstars = 5;
    		star1button = (ToggleButton) findViewById(R.id.star1);
    		star1button.setChecked(true);
          	star2button = (ToggleButton) findViewById(R.id.star2);
          	star2button.setChecked(true);
           	star3button = (ToggleButton) findViewById(R.id.star3);
          	star3button.setChecked(true);
           	star4button = (ToggleButton) findViewById(R.id.star4);
          	star4button.setChecked(true);
           	star5button = (ToggleButton) findViewById(R.id.star5);
          	star5button.setChecked(true);
		    }
          

	  	
		text11.setText(stud.getapprec()); 	
		fav = stud.getimageid();
		if (fav == 1) {
			togglefavorite = (ToggleButton) findViewById(R.id.toggle);
			togglefavorite.setChecked(true);
			togglefavorite.setText("");
			  TextView tx1 = (TextView) findViewById(R.id.TextView01);
			  tx1.setText(" Favoris");
			  fav=1;
		}
		imgPath = stud.getimagepath();  	

		}
	    }
	
	
	public void enregistrer(View view) {

		EditText text1 = (EditText) findViewById(R.id.editnom);
	  	EditText text2 = (EditText) findViewById(R.id.editmaison);
	  	EditText text3 = (EditText) findViewById(R.id.editpays);
	  	EditText text4 = (EditText) findViewById(R.id.editregion);
	  	EditText text5 = (EditText) findViewById(R.id.editcouleur);
	  	EditText text6 = (EditText) findViewById(R.id.editmille);
	  	EditText text7 = (EditText) findViewById(R.id.editprix);
	  	EditText text8 = (EditText) findViewById(R.id.edital);
	  	EditText text9 = (EditText) findViewById(R.id.editcode);
	  	EditText text10 = (EditText) findViewById(R.id.editnote);
	  	EditText text11 = (EditText) findViewById(R.id.editapprec);
	  	String txt1 = text1.getText().toString();
	  	String txt2 = text2.getText().toString();
	  	String txt3 = text3.getText().toString();
	  	String txt4 = text4.getText().toString();
	  	String txt5 = text5.getText().toString();
	  	String txt6 = text6.getText().toString();
	  	String txt7 = text7.getText().toString();
	  	String txt8 = text8.getText().toString();
	  	String txt9 = text9.getText().toString();
	  	String txt10 = text10.getText().toString();
	  	String txt11 = text11.getText().toString();

	  	Vin stud = VinDBoperation.updateVin(selcursor,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11,fav,imgPath); 

     	Toast.makeText(FicheActivity.this, "Enregistré", Toast.LENGTH_SHORT).show(); 

     	finish();
	}
	
	
	public void supprimer(View view) {

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //TODO Yes button clicked

            		Vin stud = VinDBoperation.goid(selcursor);
            		VinDBoperation.deleteVin(stud);

			File folderimg = Environment.getExternalStorageDirectory();
			String fileName = folderimg.getPath() + imgPath;
			File myFileimg = new File(fileName);
			if(myFileimg.exists())
			    myFileimg.delete();

            		Toast.makeText(FicheActivity.this, "Supprimé", Toast.LENGTH_SHORT).show(); 
            		finish();

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    //TODO No button clicked
                    dialog.dismiss();
                    break;
                }
            }
        };
        
        AlertDialog.Builder builder = new AlertDialog.Builder(FicheActivity.this);
        builder.setMessage("Supprimer cette fiche?")
            .setPositiveButton("Supprimer", dialogClickListener)
            .setNegativeButton("Annuler", dialogClickListener)
            .show();
     
	}
	
	public void nouvelphoto(View view) {

		NomID = GenerateUUID() + ".png";
		nomunique = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RapidWinesListFr/" + NomID;
    	nomunique2 = imgPath;     
		
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
	    File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +"RapidWinesListFr");
	    directory.mkdirs();
	    File photoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +"RapidWinesListFr", NomID);
	    intent.putExtra(MediaStore.EXTRA_OUTPUT,
	            Uri.fromFile(photoFile));
	    imageUri = Uri.fromFile(photoFile);
	    startActivityForResult(intent, TAKE_PICTURE);
	    
	}
	
	public void nouvelimg(View view) {

		FileDialog FileOpenDialog =  new FileDialog(this, "FileOpen",
				new FileDialog.FileDialogListener()
		{
			@Override
			public void onChosenDir(String chosenDir) 
			{
				Nomfich = chosenDir;
	            if (Nomfich != "") {
	            	imgPath = chosenDir;
	            }
				
			}
		});
		
		FileOpenDialog.Default_File_Name = "image.png";
		FileOpenDialog.chooseFile_or_Dir();
	}
	
	public String GenerateUUID() {
		  
	    UUID idOne = UUID.randomUUID();
	    String s = "";
	    s = String.valueOf(idOne);
	  
	    return s;
	} 

	/*****************
	Sélections
	**/
	
	public void selectPays(View view) {
		
	  	final CharSequence[] items = {
    			"Afrique du Sud",
    			"Argentine",
    			"Australie",
    			"Chili",
    			"Espagne",
    			"États-Unis",
    			"France",
    			"Italie",
    			"Portugal"};
       	       	       	
    	AlertDialog levelDialog=null;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int item) {
        	
        	EditText text1 = (EditText) findViewById(R.id.editpays);
		       switch(item)
	            {
	                case 0:
	                	text1.setText("Afrique du Sud");
	                    break;
	    
	 	               case 1:
	            	   text1.setText("Argentine");
	                    break;
	                    
	 	               case 2:
	            	   text1.setText("Australie");
	                    break;	                    

	 	               case 3:
	            	   text1.setText("Chili");
	                    break;	                    

	 	               case 4:
	            	   text1.setText("Espagne");
	                    break;	                    

	 	               case 5:
	            	   text1.setText("États-Unis");
	                    break;                    

	 	               case 6:
	            	   text1.setText("France");
	                    break;                    

	 	               case 7:
	            	   text1.setText("Italie");
	                    break;                    

	 	               case 8:
	            	   text1.setText("Portugal");
	                    break;	                    
	                    
	            } 
            dialog.dismiss();            
            }
        });
        
	    levelDialog = builder.create();
        levelDialog.show();     
	
	}


	public void selectAlcool(View view) {
		
	  	final CharSequence[] items = {
    			"11 %",
       			"11,5 %",
   			    "12 %",
   			    "12,5 %",
       			"13 %",
       			"13,5 %",
      			"14 %"};
       	       	       	
    	AlertDialog levelDialog=null;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int item) {
            	
        	EditText text1 = (EditText) findViewById(R.id.edital);
		       switch(item)
	            {
	                case 0:
	                	text1.setText("11 %");
	                    break;
	               case 1:
	                	text1.setText("11,5 %");
	                    break;
	               case 2:
	                	text1.setText("12 %");
	                    break;
	               case 3:
	                	text1.setText("12,5 %");
	                    break;

	               case 4:
	                	text1.setText("13 %");
	                    break;

	               case 5:
	                	text1.setText("13,5 %");
	                    break;

	               case 6:
	                	text1.setText("14 %");
	                    break;

	            } 
            dialog.dismiss();            
            }
        });
        
	    levelDialog = builder.create();
        levelDialog.show();
		
	}
	
	public void selectRegion(View view) {
		
	  	final CharSequence[] items = {
	  			"Beaujolais",
	  			"Bordeaux",
	  			"Bourgogne",
	  			"Californie",
	  			"Castille Léon",
	  			"Catalogne",
	  			"La Rioja",
	  			"Languedoc-Roussillon",
	  			"Mendoza",
	  			"Provence",
	  			"Toscane",
	  			"Vallée de la Loire",
	  			"Vallée du Rhône"};
       	       	       	
    	AlertDialog levelDialog=null;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int item) {
            	
        	EditText text1 = (EditText) findViewById(R.id.editregion);
		       switch(item)
	            {
	                case 0:
	                	text1.setText("Beaujolais");
	                    break;

		             case 1:
		            	 text1.setText("Bordeaux");
		                 break;

			         case 2:
			              text1.setText("Bourgogne");
			              break;

				     case 3:
				          text1.setText("Californie");
				          break;

					  case 4:
					       text1.setText("Castille Léon");
					       break;

					  case 5:
						   text1.setText("Catalogne");
						   break;

					  case 6:
						   text1.setText("La Rioja");
						   break;

					  case 7:
						   text1.setText("Languedoc-Roussillon");
						   break;

					  case 8:
						   text1.setText("Mendoza");
	    	                    break;

					  case 9:
						   text1.setText("Provence");
	    	                    break;

					  case 10:
						   text1.setText("Toscane");
	    	                    break;

					  case 11:
						   text1.setText("Vallée de la Loire");
	    	                    break;

					  case 12:
						   text1.setText("Vallée du Rhône");
	    	                    break;
	            } 
            dialog.dismiss();            
            }
        });
        
	    levelDialog = builder.create();
        levelDialog.show();
		
	}
		
	
public void selectCouleur(View view) {
		
	  	final CharSequence[] items = {
    			"Cabernet franc",
    			"Cabernet sauvignon",
    			"Carignan",
    			"Chardonnay",
    			"Cinsault",
    			"Grenache",
    			"Malbec",
    			"Merlot",
    			"Mourvèdre",
    			"Muscat",
    			"Pinot noir",
    			"Riesling",
    			"Sauvignon",
    			"Sémillon",
    			"Syrah",
    			"Tannat"};
       	       	       	
    	AlertDialog levelDialog=null;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int item) {
            	
        	EditText text1 = (EditText) findViewById(R.id.editcouleur);
		       switch(item)
	            {
	                case 0:
	                	text1.setText("Cabernet franc");
	                	break;
	                	
	                case 1:
	                	text1.setText("Cabernet sauvignon");
	                	break;
	                case 2:
	                	text1.setText("Carignan");
	                	break;
	                case 3:
	                	text1.setText("Chardonnay");
	                	break;
	                case 4:
	                	text1.setText("Cinsault");
	                	break;
	                case 5:
	                	text1.setText("Grenache");
	                	break;
	                case 6:
	                	text1.setText("Malbec");
	                	break;
	                case 7:
	                	text1.setText("Merlot");
	                	break;
	                case 8:
	                	text1.setText("Mourvèdre");
	                	break;
	                case 9:
	                	text1.setText("Muscat");
	                	break;
	                case 10:
	                	text1.setText("Pinot noir");
	                	break;
	                case 11:
	                	text1.setText("Riesling");
	                	break;
	                case 12:
	                	text1.setText("Sauvignon");
	                	break;
	                case 13:
	                	text1.setText("Sémillon");
	                	break;
	                case 14:
	                	text1.setText("Syrah");
	                	break;
	                case 15:
	                	text1.setText("Tannat");
	                	break;
	                   

	            } 
            dialog.dismiss();            
            }
        });
        
	    levelDialog = builder.create();
        levelDialog.show();
		
	}

public void selectEval(View view) {
	
  	final CharSequence[] items = {
			"10/10 Excellent",
			"9/10",
			"8/10", 
			"7/10", 
			"6/10",
			"5/10 Moyen",
			"4/10",
			"3/10", 
			"2/10", 
			"1/10 Mauvais"};
   	       	       	
	AlertDialog levelDialog=null;
	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
     builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int item) {
        	
    	EditText text1 = (EditText) findViewById(R.id.editnote);
	       switch(item)
            {
                case 0:
                	text1.setText("10/10");
                    break;
               case 1:
                	text1.setText("9/10");
                    break;
               case 2:
                	text1.setText("8/10");
                    break;
               case 3:
                	text1.setText("7/10");
                    break;

               case 4:
                	text1.setText("6/10");
                    break;

               case 5:
                	text1.setText("5/10");
                    break;
               case 6:
               	text1.setText("4/10");
                   break;
              case 7:
               	text1.setText("3/10");
                   break;
              case 8:
               	text1.setText("2/10");
                   break;
              case 9:
               	text1.setText("1/10");
                   break;

            } 
        dialog.dismiss();            
        }
    });
    
    levelDialog = builder.create();
    levelDialog.show();
	
}

public void selectEvalStars(View view) {
	
	 nbstars++;
 	 EditText text1 = (EditText) findViewById(R.id.editnote);
	  
	 switch(nbstars)  {
   
	 case 0:
		 
		 break;
		 
	    case 1:
 		    star1button = (ToggleButton) findViewById(R.id.star1);
 		    star1button.setChecked(true);
 		    star2button = (ToggleButton) findViewById(R.id.star2);
		    star2button.setChecked(false);
 		    star3button = (ToggleButton) findViewById(R.id.star3);
		    star3button.setChecked(false);
 		    star4button = (ToggleButton) findViewById(R.id.star4);
		    star4button.setChecked(false);
 		    star5button = (ToggleButton) findViewById(R.id.star5);
		    star5button.setChecked(false);
		    text1.setText("1 étoile");
		    text1.requestFocus();
		    break;

         case 2:
  		    star1button = (ToggleButton) findViewById(R.id.star1);
  		    star1button.setChecked(true);
  		    star2button = (ToggleButton) findViewById(R.id.star2);
 		    star2button.setChecked(true);
  		    star3button = (ToggleButton) findViewById(R.id.star3);
 		    star3button.setChecked(false);
  		    star4button = (ToggleButton) findViewById(R.id.star4);
 		    star4button.setChecked(false);
  		    star5button = (ToggleButton) findViewById(R.id.star5);
 		    star5button.setChecked(false);
		    text1.setText("2 étoiles");
		    text1.requestFocus();
 		   break;

         case 3:
   		    star1button = (ToggleButton) findViewById(R.id.star1);
   		    star1button.setChecked(true);
   		    star2button = (ToggleButton) findViewById(R.id.star2);
  		    star2button.setChecked(true);
   		    star3button = (ToggleButton) findViewById(R.id.star3);
  		    star3button.setChecked(true);
   		    star4button = (ToggleButton) findViewById(R.id.star4);
  		    star4button.setChecked(false);
   		    star5button = (ToggleButton) findViewById(R.id.star5);
  		    star5button.setChecked(false);
		    text1.setText("3 étoiles");
		    text1.requestFocus();
  		  break;
  		  
         case 4:
   		    star1button = (ToggleButton) findViewById(R.id.star1);
    		star1button.setChecked(true);
      		star2button = (ToggleButton) findViewById(R.id.star2);
      		star2button.setChecked(true);
       		star3button = (ToggleButton) findViewById(R.id.star3);
      		star3button.setChecked(true);
       		star4button = (ToggleButton) findViewById(R.id.star4);
      		star4button.setChecked(true);
       		star5button = (ToggleButton) findViewById(R.id.star5);
      		star5button.setChecked(false);
		    text1.setText("4 étoiles");
		    text1.requestFocus();
      		break;
      		
         case 5:
    		star1button = (ToggleButton) findViewById(R.id.star1);
    		star1button.setChecked(true);
          	star2button = (ToggleButton) findViewById(R.id.star2);
          	star2button.setChecked(true);
           	star3button = (ToggleButton) findViewById(R.id.star3);
          	star3button.setChecked(true);
           	star4button = (ToggleButton) findViewById(R.id.star4);
          	star4button.setChecked(true);
           	star5button = (ToggleButton) findViewById(R.id.star5);
          	star5button.setChecked(true);
  	        text1.setText("5 étoiles");
		    text1.requestFocus();
          	break;
          	
         case 6:
        	nbstars=0;
 	    	star1button = (ToggleButton) findViewById(R.id.star1);
 			star1button.setChecked(false);
 	 		star2button = (ToggleButton) findViewById(R.id.star2);
 			star2button.setChecked(false);
 	 		star3button = (ToggleButton) findViewById(R.id.star3);
 			star3button.setChecked(false);
 	 		star4button = (ToggleButton) findViewById(R.id.star4);
 			star4button.setChecked(false);
 	 		star5button = (ToggleButton) findViewById(R.id.star5);
 			star5button.setChecked(false);
		    text1.setText("");
		    text1.requestFocus();
 			break;
 }

}

	/******************/
	
	public void favorisfiche(View view) {

		togglefavorite = (ToggleButton) findViewById(R.id.toggle);
			
  if(togglefavorite.isChecked()) {
        // handle toggle on
	  togglefavorite.setText("");
	  TextView tx1 = (TextView) findViewById(R.id.TextView01);
	  tx1.setText(" Favoris");
	  fav=1;


    } else {
       // handle toggle off
    	  togglefavorite.setText("");
    	  TextView tx1 = (TextView) findViewById(R.id.TextView01);
    	  tx1.setText("");
	  fav=0;

    }    

		
	}
	
	protected void confirmbox(String title, String mymessage,String btn1text, String btn2text)
	   {
		
	    AlertDialog.Builder alertDialogConfirm = new AlertDialog.Builder(this);
	    /* alertDialogConfirm.setTitle(title) */
		alertDialogConfirm.setMessage(mymessage);
		alertDialogConfirm.setCancelable(false);
		alertDialogConfirm.setPositiveButton(btn1text, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
             chx = true;
    		}   
      });
		alertDialogConfirm.setNegativeButton(btn2text, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
     	    chx = false;
             dialog.cancel();
        }
     });
		alertDialogConfirm.show();
	    
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    switch (requestCode) {
	        case TAKE_PICTURE:
	            if (resultCode == Activity.RESULT_OK) {
	                Uri selectedImageUri = imageUri;
	                imgPath = nomunique;
	        		Toast.makeText(FicheActivity.this, "Enregistrement de la photo...", Toast.LENGTH_LONG).show(); }
	        		else {
	        			imgPath = nomunique2;
	        }
	    }
	}
	
	@Override
	protected void onResume() {
		VinDBoperation.open();
		super.onResume();
		}

	@Override
	protected void onPause() {
		VinDBoperation.close();
		super.onPause();
		}
	

	}