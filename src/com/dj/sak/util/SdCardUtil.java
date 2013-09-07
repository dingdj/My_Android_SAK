package com.dj.sak.util;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

/**
 * store data
 * @author dingdj
 *
 */
public class SdCardUtil {
	
	private static final String LOG_TAG = "SdCardUtil";
	
	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state) ||
	        Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	        return true;
	    }
	    return false;
	}
	
	//获取SD卡上公共的文件 当程序被卸载时 此目录不会被删除
	public static File getAlbumStorageDir(String albumName) {
	    // Get the directory for the user's public pictures directory. 
	    File file = new File(Environment.getExternalStoragePublicDirectory(
	            Environment.DIRECTORY_PICTURES), albumName);
	    if (!file.mkdirs()) {
	        Log.e(LOG_TAG, "Directory not created");
	    }
	    return file;
	}
	
	//当程序被卸载时 此目录会被删除
	public static File getAlbumStorageDir(Context context, String albumName) {
	    // Get the directory for the app's private pictures directory. 
	    File file = new File(context.getExternalFilesDir(
	            Environment.DIRECTORY_PICTURES), albumName);
	    if (!file.mkdirs()) {
	        Log.e(LOG_TAG, "Directory not created");
	    }
	    return file;
	}
	
	//删除内部文件
	public static void deleteInternalFile(Context context, String fileName){
		context.deleteFile(fileName);
	}

}
