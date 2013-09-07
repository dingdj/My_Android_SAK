package com.dj.sak.util;

import java.util.Calendar;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

/**
 * implict intent
 * @author dingdj
 */
public class IntentUtil {
	
	//action and uri
	public void telIntent(Context context){
		Uri number = Uri.parse("tel:5551234");
		Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
		context.startActivity(callIntent);
	}
	
	public void mapIntent(Context context){
		// Map point based on address
		Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
		// Or map point based on latitude/longitude
		// Uri location = Uri.parse("geo:37.422219,-122.08364?z=14"); // z param is zoom level
		Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
		context.startActivity(mapIntent);
	}
	
	public void webPageIntent(Context context){
		Uri webpage = Uri.parse("http://www.android.com");
		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
		context.startActivity(webIntent);
	}
	
	//  no uri
	public Intent getEmailIntent(){
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		// The intent does not have a URI, so declare the "text/plain" MIME type
		emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // recipients
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
		// You can also attach multiple items by passing an ArrayList of Uris
		return emailIntent;
	}
	
	// only with API level 14 and higher
	/*public Intent getCalendarIntent(){
		Intent calendarIntent = new Intent(Intent.ACTION_INSERT, Events.CONTENT_URI);
		Calendar beginTime = Calendar.getInstance().set(2012, 0, 19, 7, 30);
		Calendar endTime = Calendar.getInstance().set(2012, 0, 19, 10, 30);
		calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
		calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
		calendarIntent.putExtra(Events.TITLE, "Ninja class");
		calendarIntent.putExtra(Events.EVENT_LOCATION, "Secret dojo");
	}*/

	//判断intent是否有对应的activity来处理
	public static boolean isIntentSafe(Context context, Intent intent){
		PackageManager packageManager = context.getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
		return activities.size() > 0;
	}
	
	//Show an App Chooser
	public static void showAppChooser(Context context){
		Intent intent = new Intent(Intent.ACTION_SEND);
		// Always use string resources for UI text. This says something like "Share this photo with"
		String title = "Share this photo with";
		// Create and start the chooser
		Intent chooser = Intent.createChooser(intent, title);
		context.startActivity(chooser);
	}
}
