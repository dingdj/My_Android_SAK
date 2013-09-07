package com.dj.sak.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//数据arud类
public class FeedReaderService {

	private FeedOpenHelper mDbHelper;

	public FeedReaderService(Context context) {
		mDbHelper = new FeedOpenHelper(context);
	}

	public long insert(String id, String title, String subTitle) {
		// Gets the data repository in write mode
		SQLiteDatabase db = mDbHelper.getWritableDatabase();

		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
		values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, subTitle);

		// Insert the new row, returning the primary key value of the new row
		long newRowId;
		newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null,
				values);
		return newRowId;
	}
	
	public Cursor query(){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
		    FeedReaderContract.FeedEntry._ID,
		    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
		    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,
		    };

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
		    FeedReaderContract.FeedEntry._ID + " DESC";

		Cursor c = db.query(
		    FeedReaderContract.FeedEntry.TABLE_NAME,  // The table to query
		    projection,                               // The columns to return
		    null,                                // The columns for the WHERE clause
		    null,                            // The values for the WHERE clause
		    null,                                     // don't group the rows
		    null,                                     // don't filter by row groups
		    sortOrder                                 // The sort order
		    );
		return c;
	}
	
	public long queryForId(){
		Cursor cursor = query();
		cursor.moveToFirst();
		long itemId = cursor.getLong(
		    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID)
		);
		return itemId;
	}
	
	public void delete(int rowId){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		// Define 'where' part of query.
		String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		// Specify arguments in placeholder order.
		String[] selelectionArgs = { String.valueOf(rowId) };
		// Issue SQL statement.
		db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selelectionArgs);
	}
	
	public void update(String title, long rowId){
		SQLiteDatabase db = mDbHelper.getReadableDatabase();

		// New value for one column
		ContentValues values = new ContentValues();
		values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);

		// Which row to update, based on the ID
		String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		String[] selelectionArgs = { String.valueOf(rowId) };

		int count = db.update(
			FeedReaderContract.FeedEntry.TABLE_NAME,
		    values,
		    selection,
		    selelectionArgs);
	}
	
}
