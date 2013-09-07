package com.dj.sak.db;

import android.provider.BaseColumns;


/**
 * Contract class
 */
public class FeedReaderContract{
	
	// Prevents the FeedReaderContract class from being instantiated.
	private FeedReaderContract() {}

	public abstract class FeedEntry implements BaseColumns{
		public static final String TABLE_NAME = "entry";
		public static final String COLUMN_NAME_ENTRY_ID = "entryid";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_SUBTITLE = "subtitle";
	
	}
	
	
}
