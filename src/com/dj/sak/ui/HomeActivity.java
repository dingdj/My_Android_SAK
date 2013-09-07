package com.dj.sak.ui;

import java.io.File;
import java.io.IOException;

import com.dj.sak.R;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class HomeActivity extends Activity implements OnClickListener {

	static final String STATE_SCORE = "playerScore";
	static final String STATE_LEVEL = "playerLevel";

	private int mCurrentScore = 100;
	private int mCurrentLevel;
	private Button fragementBtn;
	private Button saveFileInternalBtn;
	private Button saveFileExternalBtn;
	
	static final String TAG = "HomeActivity";
	static final boolean DEBUG = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Always call the superclass first
		// Check whether we're recreating a previously destroyed instance
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
			mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
		} else {
			// Probably initialize members with default values for a new
			// instance
		}
		setContentView(R.layout.main);
		fragementBtn = (Button) findViewById(R.id.fragementBtn);
		fragementBtn.setOnClickListener(this);
		SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.homeactivitysrname), 
	    		Context.MODE_PRIVATE);
		int highScore = sharedPref.getInt(getString(R.string.saved_high_score), 101);
		if(DEBUG){
			Toast.makeText(this, highScore+"", Toast.LENGTH_LONG).show();
			
		}
		saveFileInternalBtn = (Button)findViewById(R.id.saveFileInternalBtn);
		saveFileInternalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String fileName = "sak.log";
				File file = new File(getFilesDir(), "sak.log");
				try {
					File tempFile = File.createTempFile(fileName, null, getCacheDir());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		saveFileExternalBtn = (Button)findViewById(R.id.saveFileExternalBtn);
		saveFileExternalBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		android.os.Debug.stopMethodTracing();
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		// Save the user's current game state
	    savedInstanceState.putInt(STATE_SCORE, mCurrentScore);
	    savedInstanceState.putInt(STATE_LEVEL, mCurrentLevel);
	    
	    //save sharePreferences
	    SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.homeactivitysrname), 
	    		Context.MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPref.edit();
	    editor.putInt(getString(R.string.saved_high_score), mCurrentScore);
	    editor.commit();
	    
	    // Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		// Restore state members from saved instance
		mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
		mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
	}

	// 版本判断
	private void setUpActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(getBaseContext(), ArticleActivity.class);
		startActivity(intent);
	}
	
	

}
