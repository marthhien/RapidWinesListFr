package com.mg153.RapidWinesListFr;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import com.mg153.RapidWinesListFr.Vin;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;


public class VinOperations {

	// Database fields
	private DataBaseWrapper dbHelper;
	private String[] VIN_TABLE_COLUMNS = { DataBaseWrapper.RapidWinesListFr_ID, 
			DataBaseWrapper.RapidWinesListFr_NOM, 
			DataBaseWrapper.RapidWinesListFr_MAISON,
			DataBaseWrapper.RapidWinesListFr_PAYS,
			DataBaseWrapper.RapidWinesListFr_REGION,
			DataBaseWrapper.RapidWinesListFr_COULEUR,
			DataBaseWrapper.RapidWinesListFr_MILLE,
			DataBaseWrapper.RapidWinesListFr_ALCOOL,
			DataBaseWrapper.RapidWinesListFr_PRIX,
			DataBaseWrapper.RapidWinesListFr_CODE,
			DataBaseWrapper.RapidWinesListFr_NOTE,
			DataBaseWrapper.RapidWinesListFr_APPREC,
			DataBaseWrapper.RapidWinesListFr_IMAGEID,
			DataBaseWrapper.RapidWinesListFr_IMAGEPATH  };
	
	
	private SQLiteDatabase database;

	public VinOperations(Context context) {
		dbHelper = new DataBaseWrapper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	
	public Vin addVin(String nom, 
					String maison,
					String pays,
					String region,
					String couleur,
					String mille,
					String prix,
					String alcool,
					String code,
					String note,
					String apprec,
					int imageid,
					String imagepath) {

		ContentValues values = new ContentValues();

		values.put(DataBaseWrapper.RapidWinesListFr_NOM, nom);
		values.put(DataBaseWrapper.RapidWinesListFr_MAISON, maison);
		values.put(DataBaseWrapper.RapidWinesListFr_PAYS, pays);
		values.put(DataBaseWrapper.RapidWinesListFr_REGION, region);
		values.put(DataBaseWrapper.RapidWinesListFr_COULEUR, couleur);
		values.put(DataBaseWrapper.RapidWinesListFr_MILLE, mille);
		values.put(DataBaseWrapper.RapidWinesListFr_ALCOOL, alcool);
		values.put(DataBaseWrapper.RapidWinesListFr_PRIX, prix);
		values.put(DataBaseWrapper.RapidWinesListFr_CODE, code);
		values.put(DataBaseWrapper.RapidWinesListFr_NOTE, note);
		values.put(DataBaseWrapper.RapidWinesListFr_APPREC, apprec);
		values.put(DataBaseWrapper.RapidWinesListFr_IMAGEID, imageid);
		values.put(DataBaseWrapper.RapidWinesListFr_IMAGEPATH, imagepath);
	
		long studId = database.insert(DataBaseWrapper.RapidWinesListFr, null, values);  
		
			// now that the VIN is created return it ...
		Cursor cursor = database.query(DataBaseWrapper.RapidWinesListFr,
				VIN_TABLE_COLUMNS, DataBaseWrapper.RapidWinesListFr_ID + " = "
						+ studId, null, null, null, null);

		cursor.moveToFirst();
		Vin newComment = parseVin(cursor); 
		cursor.close(); 

		return newComment;
	}
	
	
	public Vin updateVin(long id, String nom, 
			String maison,
			String pays,
			String region,
			String couleur,
			String mille,
			String prix,
			String alcool,
			String code,
			String note,
			String apprec,
			int imageid,
			String imagepath)  {

		ContentValues values = new ContentValues();

		values.put(DataBaseWrapper.RapidWinesListFr_NOM, nom);
		values.put(DataBaseWrapper.RapidWinesListFr_MAISON, maison);
		values.put(DataBaseWrapper.RapidWinesListFr_PAYS, pays);
		values.put(DataBaseWrapper.RapidWinesListFr_REGION, region);
		values.put(DataBaseWrapper.RapidWinesListFr_COULEUR, couleur);
		values.put(DataBaseWrapper.RapidWinesListFr_MILLE, mille);
		values.put(DataBaseWrapper.RapidWinesListFr_ALCOOL, alcool);
		values.put(DataBaseWrapper.RapidWinesListFr_PRIX, prix);
		values.put(DataBaseWrapper.RapidWinesListFr_CODE, code);
		values.put(DataBaseWrapper.RapidWinesListFr_NOTE, note);
		values.put(DataBaseWrapper.RapidWinesListFr_APPREC, apprec);
		values.put(DataBaseWrapper.RapidWinesListFr_IMAGEID, imageid);
		values.put(DataBaseWrapper.RapidWinesListFr_IMAGEPATH, imagepath);
	
		String whereArgs[] = new String[1];
		whereArgs[0] = "" + id;
		database.update(DataBaseWrapper.RapidWinesListFr, values, "_id= ?", whereArgs);
		
			// now that the VIN is created return it ...
		Cursor cursor = database.query(DataBaseWrapper.RapidWinesListFr,
				VIN_TABLE_COLUMNS, DataBaseWrapper.RapidWinesListFr_ID + " = "
						+ id, null, null, null, null);

		cursor.moveToFirst();
		Vin newComment = parseVin(cursor); 
		cursor.close();   
		
		return newComment;  

	} 
	
		
	public void deleteVin(Vin comment) {
		long id = comment.getId();
			long studId = database.delete(DataBaseWrapper.RapidWinesListFr, DataBaseWrapper.RapidWinesListFr_ID
				+ " = " + id, null);
		}

	
	
	public Vin goid(long id) {

			Cursor cursor = database.query(DataBaseWrapper.RapidWinesListFr,
				VIN_TABLE_COLUMNS, DataBaseWrapper.RapidWinesListFr_ID + " = "
						+ id, null, null, null, null);

		cursor.moveToFirst();
		Vin newComment = parseVin(cursor); 
		cursor.close();   
		return newComment;  

	} 
	
	
	public List getAllVins() {
		List vins = new ArrayList<Vin>(); 

		Cursor cursor = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _nom ASC", null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Vin vin = parseVin(cursor); 
  			vins.add(vin);
			cursor.moveToNext();
		}

		cursor.close();
		
		return vins;
	}


	public List getAllVinsTri(int t) {
		List vins = new ArrayList<Vin>(); 

 	    	   switch (t) 
		           { 
		       
		           case 0: 
				Cursor cursor0 = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _nom ASC", null);
				cursor0.moveToFirst();
				while (!cursor0.isAfterLast()) {
					Vin vin = parseVin(cursor0); 
		  			vins.add(vin);
					cursor0.moveToNext();
				}
				cursor0.close();
				break;

		           case 1: 
				Cursor cursor1 = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _nom DESC", null);
				cursor1.moveToFirst();
				while (!cursor1.isAfterLast()) {
					Vin vin = parseVin(cursor1); 
		  			vins.add(vin);
					cursor1.moveToNext();
				}
				cursor1.close();
				break;

		           case 2: 
				Cursor cursor2 = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _prix ASC", null);
				cursor2.moveToFirst();
				while (!cursor2.isAfterLast()) {
					Vin vin = parseVin(cursor2); 
		  			vins.add(vin);
					cursor2.moveToNext();
				}
				cursor2.close();
				break;

		           case 3: 
				Cursor cursor3 = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _prix DESC", null);
				cursor3.moveToFirst();
				while (!cursor3.isAfterLast()) {
					Vin vin = parseVin(cursor3); 
		  			vins.add(vin);
					cursor3.moveToNext();
				}
				cursor3.close();
				break;

		           case 4: 
				Cursor cursor4 = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _pays ASC", null);
				cursor4.moveToFirst();
				while (!cursor4.isAfterLast()) {
					Vin vin = parseVin(cursor4); 
		  			vins.add(vin);
					cursor4.moveToNext();
				}
				cursor4.close();
				break;

		           case 5: 
				Cursor cursor5 = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _pays DESC", null);
				cursor5.moveToFirst();
				while (!cursor5.isAfterLast()) {
					Vin vin = parseVin(cursor5); 
		  			vins.add(vin);
					cursor5.moveToNext();
				}
				cursor5.close();
				break;
}
				
		return vins;
	}


	public List getAllVinsCherche(String c) {
		int counter;
		counter=0;
		List vins = new ArrayList<Vin>(); 

		Cursor cursor = database.rawQuery("SELECT * FROM RapidWinesListFr WHERE _nom LIKE '%" + c + "%' ORDER BY _nom ASC", null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			counter++;
			Vin vin = parseVin(cursor); 
  			vins.add(vin);
			cursor.moveToNext();
		}

		cursor.close();
		
		if(counter == 0 ) {

			cursor = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _nom ASC", null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				counter++;
				Vin vin = parseVin(cursor); 
	  			vins.add(vin);
				cursor.moveToNext();
			}

			cursor.close();
			}

		
		return vins;

	}

	public List getFavorisVins() {
		List vins = new ArrayList<Vin>(); 

		Cursor cursor = database.rawQuery("SELECT * FROM RapidWinesListFr WHERE _imageid=1 ORDER BY _nom ASC", null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Vin vin = parseVin(cursor); 
  			vins.add(vin);
			cursor.moveToNext();
		}

		cursor.close();
		
		return vins;
	}
	
	public List getUpVins() {
		List vins = new ArrayList<Vin>(); 

		Cursor cursor = database.rawQuery("SELECT * FROM RapidWinesListFr WHERE _imageid=0 AND (_note='1 étoile' OR  _note='2 étoiles' OR  _note='3 étoiles' OR  _note='4 étoiles' OR _note='5 étoiles') ORDER BY _nom ASC", null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Vin vin = parseVin(cursor); 
  			vins.add(vin);
			cursor.moveToNext();
		}

		cursor.close();
		
		return vins;
	}
	
	public List getDownVins() {
		List vins = new ArrayList<Vin>(); 

		Cursor cursor = database.rawQuery("SELECT * FROM RapidWinesListFr WHERE _imageid=0 AND _note='' ORDER BY _nom ASC", null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Vin vin = parseVin(cursor); 
  			vins.add(vin);
			cursor.moveToNext();
		}

		cursor.close();
		
		return vins;
	}
	
	
	public List getExportVins()  {
		
	   List<String> vins2 = new ArrayList<String>();
	   String f;
	   String str1;
           String str2;

		Cursor cursor = database.rawQuery("SELECT * FROM RapidWinesListFr ORDER BY _nom ASC", null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
	
			 f="nofavorite";
			 if (cursor.getInt(12) == 1) {
			     f="favorite";
			 }

			 str1 = cursor.getString(11);
			 str2=str1.replace('\n',' ');
			 
			 vins2.add(cursor.getString(1) + ";" +  
  					cursor.getString(2)  + ";" +
  					cursor.getString(3) + ";" +
  					cursor.getString(4) + ";" +
  					cursor.getString(5) + ";" +
  					cursor.getString(6) + ";" +
  					cursor.getString(7) + ";" +
  					cursor.getString(8) + ";" +
  					cursor.getString(9) + ";" +
  					cursor.getString(10) + ";" +
  					str2 + ";" +
  					f + ";" +
  					cursor.getString(13));

			cursor.moveToNext();
		}

		cursor.close();
		
		return vins2;
	}

	private Vin parseVin(Cursor cursor) {
		Vin vin = new Vin(cursor.getInt(12), cursor.getString(1), cursor.getString(4), cursor.getString(3), cursor.getString(8));
		vin.setId((cursor.getInt(0)));
		vin.setnom(cursor.getString(1));
		vin.setmaison(cursor.getString(2));
		vin.setpays(cursor.getString(3));
		vin.setregion(cursor.getString(4));
		vin.setcouleur(cursor.getString(5));
		vin.setmille(cursor.getString(6));
		vin.setalcool(cursor.getString(7));
		vin.setprix(cursor.getString(8));
		vin.setcode(cursor.getString(9));
		vin.setnote(cursor.getString(10));
		vin.setapprec(cursor.getString(11));
		vin.setimageid(cursor.getInt(12));
		vin.setimagepath(cursor.getString(13));
				
		return vin; 
	}
		
}



