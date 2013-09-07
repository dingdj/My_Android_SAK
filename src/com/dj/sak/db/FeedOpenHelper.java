package com.dj.sak.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FeedOpenHelper extends SQLiteOpenHelper {
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
		    "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
		    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
		    FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
		    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
		    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE +
		    " )";
	private static final String SQL_DELETE_ENTRIES =
		    "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;
	
	public FeedOpenHelper(Context context) {
	     super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
	}
	
	

}
