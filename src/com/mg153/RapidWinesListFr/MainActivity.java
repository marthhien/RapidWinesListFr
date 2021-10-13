package com.mg153.RapidWinesListFr;

import java.util.List;

import android.app.ListActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity; 
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.ImageButton;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import java.util.ArrayList; 
import java.util.List; 

import com.mg153.RapidWinesListFr.R; 
import com.mg153.RapidWinesListFr.CustomListViewAdapter;
import com.mg153.RapidWinesListFr.FicheActivity;
import com.mg153.RapidWinesListFr.LabelActivity;
import com.mg153.RapidWinesListFr.NoLabelActivity;
import com.mg153.RapidWinesListFr.NouvFicheActivity;
import com.mg153.RapidWinesListFr.Vin;

import android.os.Handler; 
import android.util.AttributeSet; 
import android.util.Log; 
import android.view.InflateException; 
import android.view.LayoutInflater; 
import android.view.LayoutInflater.Factory;

import java.io.*;
import android.net.Uri;


public class MainActivity extends ListActivity {

	private VinOperations VinDBoperation; 
	int tous;
	boolean chx;
	long selcursor;
	String Nomfich;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.RedTheme);
		setContentView(R.layout.main);
		
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
	        public boolean onItemLongClick(AdapterView<?> parent, View v, int position, long id) {
 	        	
	  	     Vin stud = null;	  		
	         if (getListAdapter().getCount() > 0) {	
	            	stud = (Vin) getListAdapter().getItem(position);
	            	selcursor = stud.getId();

	        		/* afficher la photo	*/
	            	String imgPath = stud.getimagepath();
	            	
	            	if (imgPath.trim().equals("nopicture")) {
	            		NoPhoto(); 
	        		 }
	            	else {
	            		Affphoto(); 
	            	}      
	         }
	         return true;
	        }
		});
		
		    getListView().setOnItemClickListener(new OnItemClickListener() {
		        @Override
	        public void onItemClick(AdapterView<?> parent, View v, int position ,long id) {
		 
		        	for(int a = 0; a < parent.getChildCount(); a++)
		            {
		         	parent.getChildAt(a).setBackgroundColor(getResources().getColor(R.color.LBLACK));
		            }
		        v.setBackgroundColor(getResources().getColor(R.color.LBLUE));
		        
			Vin stud = null;
		  		if (getListAdapter().getCount() > 0) {
		  			stud = (Vin) getListAdapter().getItem(position);
		  			selcursor = stud.getId();
		  			
		  			Toast.makeText(MainActivity.this, "Appuyer plus longtemps pour afficher la photo", Toast.LENGTH_SHORT).show(); 
		  		}
	
		        };
		    });  
	     
		
		VinDBoperation = new VinOperations(this);
		VinDBoperation.open();

		tous = 0;
		selcursor = -1;
		List values = VinDBoperation.getAllVins(); 
	    ListView lv = getListView();
		lv.setAdapter(null);
	    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
	    setListAdapter(adapter);

	    /*   View view = this.getWindow().getDecorView();  */
	}

    
	
  	   // Initiating Menu XML file (menu.xml) 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    { 
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main, menu);
	   	   return true;//return true so to menu pop up is opens

    } 

         
    /** 
     * Event Handling for Individual menu item selected 
     * Identify single menu item by it's id 
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    { 
          
        switch (item.getItemId()) 
        { 
    
        case R.id.menu_quit: 
       
        	/*/ System.exit(0);  */ 
        	AlertDialog.Builder alertDialogQuit = new AlertDialog.Builder(this);
        	alertDialogQuit.setMessage("Quitter l'application?");
        	alertDialogQuit.setCancelable(false);
	        	alertDialogQuit.setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
            	   MainActivity.this.finish();
               }
             });
        	alertDialogQuit.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
               }
            });
        	alertDialogQuit.show();
        	return true;
      	        	        	      	
        case R.id.menu_par:
        
        	StringBuilder sb = new StringBuilder();
        	sb.append("par Martin Gauthier");
        	sb.append("\n");
        	sb.append("mg.rapidwineslist@gmail.com");
        	sb.append("\n\n");
        	sb.append("Version 1.2");
        	AlertDialog.Builder alertDialogAbout = new AlertDialog.Builder(this);
        	alertDialogAbout.setMessage(sb);   
     	   	alertDialogAbout.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
          		    dialog.cancel();
        		}
        	});
     	   	
        	alertDialogAbout.show();
        	                	    
        	return true;
        	    	
        case R.id.menu_imp:
            
       		FileDialog FileOpenDialog =  new FileDialog(this, "FileOpen",
    				new FileDialog.FileDialogListener()
    		{
    			@Override
    			public void onChosenDir(String chosenDir) 
    			{
    				Nomfich = chosenDir;
    	            if (Nomfich != "") {
            	  	    importDB(Nomfich);
    	            }
    			}
    		});
    		
    		FileOpenDialog.Default_File_Name = "MesVins.txt";
    		FileOpenDialog.chooseFile_or_Dir();
        	           
            	return true;

    	case R.id.menu_exp:
            
     		FileDialog FileOpenDialog2 =  new FileDialog(this, "FileSave",
    				new FileDialog.FileDialogListener()
    		{
    			@Override
    			public void onChosenDir(String chosenDir) 
    			{
    				Nomfich = chosenDir;
    	            if (Nomfich != "") {
    	            	exportDB(Nomfich);
    	            }
    			}
    		});
    		
    		FileOpenDialog2.Default_File_Name = "MesVins.txt";
    		FileOpenDialog2.chooseFile_or_Dir();
     
            	return true;
        	
    case R.id.menu_cherche: 
    	chercherliste();
    	return true;
    	
    case R.id.menu_trier: 
    	trierliste();
    	return true;
    	
        default: 
            return super.onOptionsItemSelected(item); 
        } 
    } 
    
   
    public void Affphoto() {
    	  	
           if (selcursor > -1) {
    		
    		final Context context = this;
        	Intent intent = new Intent(context, LabelActivity.class);
        	intent.putExtra("KEY",selcursor);
                startActivity(intent);   
        	 }
        		
          }
    
    public void NoPhoto() {
	  	
    	final Context context = this;
    	Intent intent = new Intent(context, NoLabelActivity.class);
        startActivity(intent);  
   
      }
    
    
	public void nouveau(View view) {

	 	final Context context = this;
	    	Intent intent = new Intent(context, NouvFicheActivity.class);
	        startActivity(intent);   
		}
	
    public void fiche(View view) {
    	
    	if (selcursor > -1) {
    		
		final Context context = this;
    	Intent intent = new Intent(context, FicheActivity.class);
    	intent.putExtra("KEY",selcursor);
            startActivity(intent);   
    	 }
    		
      }
    
    
    public void favorisliste(View view) {

	if (tous==0)  {
	
	tous=1;
    	List values = VinDBoperation.getFavorisVins(); 
	    ListView lv = getListView();
		lv.setAdapter(null);
	    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
	    setListAdapter(adapter);
	 	}
	else	{

	tous=0;
    	List values = VinDBoperation.getAllVins(); 
	    ListView lv = getListView();
		lv.setAdapter(null);
	    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
	    setListAdapter(adapter);

 	}

 	}
    
    public void upliste(View view) {
    	
    	if (tous==0)  {
    		
    		tous=1;
    	    	List values = VinDBoperation.getUpVins(); 
    		    ListView lv = getListView();
    			lv.setAdapter(null);
    		    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
    		    setListAdapter(adapter);
    		 	}
    		else	{

    		tous=0;
    	    	List values = VinDBoperation.getAllVins(); 
    		    ListView lv = getListView();
    			lv.setAdapter(null);
    		    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
    		    setListAdapter(adapter);

    	 	}
	}
    
    public void downliste(View view) {
    
    	if (tous==0)  {
    		
    		tous=1;
    	    	List values = VinDBoperation.getDownVins(); 
    		    ListView lv = getListView();
    			lv.setAdapter(null);
    		    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
    		    setListAdapter(adapter);
    		 	}
    		else	{

    		tous=0;
    	    	List values = VinDBoperation.getAllVins(); 
    		    ListView lv = getListView();
    			lv.setAdapter(null);
    		    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
    		    setListAdapter(adapter);

    	 	}
   	}
    

    public void chercherliste() {


         	AlertDialog.Builder alertDialogimport = new AlertDialog.Builder(this);
        	alertDialogimport.setMessage("Rechercher :");
        	
        	final EditText input1 = new EditText(this);
        	input1.setText("");
        	alertDialogimport.setView(input1);

        	alertDialogimport.setCancelable(false);
	        	alertDialogimport.setPositiveButton("Afficher", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
            	  	String txt1 = input1.getText().toString();
            	  	chercher(txt1);
               }
             });
        	alertDialogimport.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
               }
            });
        	alertDialogimport.show();

 	}


  public void chercher(String f) {
   	    List values = VinDBoperation.getAllVinsCherche(f); 
	    ListView lv = getListView();
	    lv.setAdapter(null);
	    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
	    setListAdapter(adapter);

 	}


    public void trierliste() {


  	final CharSequence[] items = {
			"Nom ascendant",
			"Nom descendant",
			"Prix ascendant",
			"Prix descendant",
			"Pays ascendant",
			"Pays descendant"};
   	       	       	
	AlertDialog levelDialog=null;
	AlertDialog.Builder builder = new AlertDialog.Builder(this); 
     builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialog, int item) {
        	
	switch(item)
	            {

               case 0:
		    trier(0);
                    break;

               case 1:
		    trier(1);
                    break;

               case 2:
		    trier(2);
                    break;

               case 3:
		    trier(3);
                    break;

               case 4:
		    trier(4);
                    break;

               case 5:
		    trier(5);
                    break;

            } 
        dialog.dismiss();            
        }
    });
    
    levelDialog = builder.create();
    levelDialog.show();

 	}


  public void trier(int i) {
 	    List values = VinDBoperation.getAllVinsTri(i); 
	    ListView lv = getListView();
	    lv.setAdapter(null);
	    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
	    setListAdapter(adapter);

 	}

/** Rate this application  **/

     public void RateApplication() {
    	 Uri uri = Uri.parse("market://details?id=" + this.getPackageName());  	 
Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
try {
  startActivity(goToMarket);
} catch (android.content.ActivityNotFoundException e) {
	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
}
}


/** Send a email **/

  
  public void SendEmail() {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("message/rfc822");
    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mg153@hotmail.com"});
    intent.putExtra(Intent.EXTRA_SUBJECT, "Liste rapide des vins commentaires");
    intent.putExtra(Intent.EXTRA_TEXT,"Placer ici vos commentaires.  Merci!" );
    try {
     startActivity(intent);
    } catch (android.content.ActivityNotFoundException ex) {
    	 Toast.makeText(MainActivity.this, "Aucun programme de courrier n'est installé",Toast.LENGTH_LONG).show();
    }
}

private void importDB(String path){
	
    try {
   
    	File myFile = new File(path);
		FileInputStream fIn = new FileInputStream(myFile);
		BufferedReader myReader = new BufferedReader(
				new InputStreamReader(fIn));
		String aDataRow = "";
	/*	String aBuffer = ""; */
		String[] values;
		int f = 0;
		int counter = 0;
		int i = 0;
		
		String txt1 = "";
	  	String txt2 = "";
	  	String txt3 = "";
	  	String txt4 = "";
	  	String txt5  = "";
	  	String txt6 = "";
	  	String txt7 = "";
	  	String txt8 = "";
	  	String txt9 = "";
	  	String txt10 = "";
	  	String txt11 = "";
		String txt12 = "";
		String txtf = "";
		
		while ((aDataRow = myReader.readLine()) != null) {
	/*		aBuffer += aDataRow + "\n";   */			
		    counter = 0;
		    values = aDataRow.split(";"); 
		    for (String item : values)
		    {
		    	   switch (counter) 
		           { 
		       
		           case 0: 
		        	   txt1 = item;
		        	   
		           case 1: 
		        	   txt2 = item;
		        	   
		           case 2: 
		        	   txt3 = item;
		        	   
		           case 3: 
		        	   txt4 = item;
		        	   
		           case 4: 
		        	   txt5 = item;
		        	   
		           case 5: 
		        	   txt6 = item;
		        	   
		           case 6: 
		        	   txt7 = item;
		        	   
		           case 7: 
		        	   txt8 = item;
		        	   
		           case 8: 
		        	   txt9 = item;
		        	   
		           case 9: 
		        	   txt10 = item;
		        	   
		           case 10: 
		        	   txt11 = item;
		        	   
		           case 11: 
	        	       if (item.trim().equals("favorite"))  {
	        	    	   f=1;
	        	    	   txtf = "favorite";
	        	       }
	        	       if (item.trim().equals("nofavorite")) {
	        	    	   f=0;
	        	    	   txtf = "nofavorite";
	        	       }
	        	       
		           case 12: 
		        	   txt12 = item;
		           } 

		    	   counter++;
		    }       
			
			Vin stud = VinDBoperation.addVin(txt1,txt2,txt3,txt4,txt5,txt6,txt8,txt7,txt9,txt10,txt11,f,txt12); 
			 		    
		/*    alertbox("",txt1+"/"+txt2+"/"+txt3+"/"+txt4+"/"+txt5+"/"+txt6+"/"+txt7+"/"+txt8+"/"+txt9+"/"+txt10+"/"+txt11+"/"+txtf+"/"+txt12); */
		}
		
		myReader.close();
		
		tous=0;
		selcursor =-1;
	    	List values2 = VinDBoperation.getAllVins(); 
		    ListView lv = getListView();
			lv.setAdapter(null);
		    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values2);
		    setListAdapter(adapter);

 		   	Toast.makeText(MainActivity.this, "Importé",Toast.LENGTH_LONG).show();   
 		   
	} catch (Exception e) {
 		/*   Toast.makeText(MainActivity.this, e.getMessage() ,Toast.LENGTH_LONG).show();   */
 		   Toast.makeText(MainActivity.this, "Fichier introuvable",Toast.LENGTH_LONG).show();   
	}
	
}

private void exportDB(String path){

			try {
				
				File myFile = new File(path);
				myFile.createNewFile();
				FileOutputStream fOut = new FileOutputStream(myFile);
				OutputStreamWriter myOutWriter = 
										new OutputStreamWriter(fOut);
				String txt = "";
		    	List<String> values = VinDBoperation.getExportVins(); 
		    	for (int i = 0; i < values.size(); i++) {
					txt=values.get(i);
					myOutWriter.append(txt);
					myOutWriter.append("\r\n");
				}
				
				myOutWriter.close();
				fOut.close();
				Toast.makeText(getBaseContext(), "Enregistré", Toast.LENGTH_SHORT).show(); 
				
			} catch (Exception e) {
	 	/*	   Toast.makeText(MainActivity.this, "Impossible d'enregistrer" + e.getMessage() ,Toast.LENGTH_LONG).show();  */ 
	 		   Toast.makeText(MainActivity.this, "Impossible d'enregistrer",Toast.LENGTH_LONG).show();   
		}

}



protected void alertbox(String title, String mymessage)
{
	
	StringBuilder bOK = new StringBuilder();
bOK.append("OK");
  	
new AlertDialog.Builder(this)
    /* .setTitle(title) */
   .setMessage(mymessage)
   .setCancelable(true)
   .setNeutralButton(bOK,
      new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int whichButton){}
      })
   .show();           

}


	 
	@Override
	protected void onResume() {
		VinDBoperation.open();
		tous=0;
		selcursor =-1;
	    	List values = VinDBoperation.getAllVins(); 
		    ListView lv = getListView();
			lv.setAdapter(null);
		    CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.list_item, values);
		    setListAdapter(adapter);
		super.onResume();
		}

	@Override
	protected void onPause() {
		VinDBoperation.close();
		super.onPause();
		}


}

