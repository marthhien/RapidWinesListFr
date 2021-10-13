package com.mg153.RapidWinesListFr;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

	public static final String RapidWinesListFr = "RapidWinesListFr";
	public static final String RapidWinesListFr_ID = "_id";
	public static final String RapidWinesListFr_NOM = "_nom";
	public static final String RapidWinesListFr_MAISON ="_maison";
	public static final String RapidWinesListFr_PAYS = "_pays";
	public static final String RapidWinesListFr_REGION = "_region";
	public static final String RapidWinesListFr_COULEUR = "_couleur";
	public static final String RapidWinesListFr_MILLE = "_mille";
	public static final String RapidWinesListFr_ALCOOL = "_alcool";
	public static final String RapidWinesListFr_PRIX = "_prix";
	public static final String RapidWinesListFr_CODE = "_code";
	public static final String RapidWinesListFr_NOTE = "_note";
	public static final String RapidWinesListFr_APPREC = "_apprec";
	public static final String RapidWinesListFr_IMAGEID = "_imageid";
        public static final String RapidWinesListFr_IMAGEPATH = "_imagepath";


	private static final String DATABASE_NAME = "RapidWinesListFr.db";
	private static final int DATABASE_VERSION = 1;

	// creation SQLite statement
	private static final String DATABASE_CREATE = "create table " + RapidWinesListFr
			+ "(" + RapidWinesListFr_ID + " integer primary key autoincrement, "
			+ RapidWinesListFr_NOM  + " text not null, "
			+ RapidWinesListFr_MAISON  + " text not null, "
			+ RapidWinesListFr_PAYS  + " text not null, "
			+ RapidWinesListFr_REGION  + " text not null, "
			+ RapidWinesListFr_COULEUR  + " text not null, "
			+ RapidWinesListFr_MILLE  + " text not null, "
			+ RapidWinesListFr_ALCOOL  + " text not null, "
			+ RapidWinesListFr_PRIX  + " text not null, "
			+ RapidWinesListFr_CODE  + " text not null, "
			+ RapidWinesListFr_NOTE  + " text not null, "
			+ RapidWinesListFr_APPREC  + " text not null, "
			+ RapidWinesListFr_IMAGEID  + " integer, "
			+ RapidWinesListFr_IMAGEPATH  + " text not null);";
	
	
	public DataBaseWrapper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// you should do some logging in here
		// ..

		db.execSQL("DROP TABLE IF EXISTS " + RapidWinesListFr);
		onCreate(db);
	}

}
