package com.dj.sak.ui;

import com.dj.sak.R;
import com.dj.sak.ui.fragment.ArticleFragment;
import com.dj.sak.ui.fragment.HeadlinesFragment;
import com.dj.sak.ui.fragment.HeadlinesFragment.OnHeadlineSelectedListener;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/**
 * fragmentActivity
 * @author dingdj
 * Date:2013-9-6下午4:24:29
 *
 */
public class ArticleActivity extends FragmentActivity implements OnHeadlineSelectedListener {
	
	private HeadlinesFragment headlineFragment;
	private static final String TAG = "ArticleActivity";
	private static final boolean DEBUG = true;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(DEBUG){
			Log.d(TAG, "onCreate");
		}
		setContentView(R.layout.article_container);
		if(findViewById(R.id.fragment_container) != null){//小屏幕手机适配 动态加载第一个fragment
			// However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
			headlineFragment = new HeadlinesFragment();
			// In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
			headlineFragment.setArguments(getIntent().getExtras());
			// Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, headlineFragment).commit();
		}
	}



	@Override
	public void onArticleSelected(int position) {
		  // The user selected the headline of an article from the HeadlinesFragment

        // Capture the article fragment from the activity layout
        ArticleFragment articleFrag = (ArticleFragment)
                getSupportFragmentManager().findFragmentById(R.id.article_fragment);
        if(articleFrag != null){ //大屏幕
        	articleFrag.updateArticleView(position);
        }else{ //小屏幕
        	ArticleFragment newFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
	}
	

}
