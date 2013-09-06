package com.dj.sak.ui;

import com.dj.sak.R;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity implements OnClickListener{
	
	static final String STATE_SCORE = "playerScore";
	static final String STATE_LEVEL = "playerLevel";
	
	private int mCurrentScore;
	private int mCurrentLevel;
	private Button fragementBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // Always call the superclass first
		// Check whether we're recreating a previously destroyed instance
	    if (savedInstanceState != null) {
	        // Restore value of members from saved state
	        mCurrentScore = savedInstanceState.getInt(STATE_SCORE);
	        mCurrentLevel = savedInstanceState.getInt(STATE_LEVEL);
	    } else {
	        // Probably initialize members with default values for a new instance
	    }
		setContentView(R.layout.main);
		fragementBtn = (Button)findViewById(R.id.fragementBtn);
		fragementBtn.setOnClickListener(this);
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
	
	//版本判断
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
