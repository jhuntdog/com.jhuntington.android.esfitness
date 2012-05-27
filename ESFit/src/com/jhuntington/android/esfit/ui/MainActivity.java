package com.jhuntington.android.esfit.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewGroupCompat;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.nineoldandroids.animation.Animator;
import com.actionbarsherlock.internal.nineoldandroids.animation.ObjectAnimator;
import com.actionbarsherlock.internal.nineoldandroids.animation.ValueAnimator;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.jhuntington.android.esfit.R;

public class MainActivity extends BaseActivity implements DashboardFragment.OnDbItemSelectedListener {
	
	//private String[] mToggleLabels = {"Show Titles", "Hide Titles"};
	private Animator mCurrentJillianAnimator;
	private String[] mToggleJillian = {"Show Jillian", "Hide Jillian"};
	
	//private int mThemeId = -1;

	private boolean mDualFragments = false;
	private boolean mDualPane = false;
	private boolean mJillianHidden = false;
	//private int mThemeId = -1;
	
	SharedPreferences preferences;
	public String ListPreference;
	//private boolean scheduledRestart;
	private int mThemeChoice;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		ActionBar bar = getSupportActionBar();
		bar.setDisplayShowTitleEnabled(true);
		
		// find our fragments
		DashboardFragment mDashboardFragment = (DashboardFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_dashboard);
		/*ContentFragment mContentFragment = (ContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_content);
		if (mContentFragment != null) mDualFragments = true;*/
		
		View contentFrameCalcs = this.findViewById(R.id.fragment_content);
		if (contentFrameCalcs != null) mDualPane = true;
		
		/*JillianFragment frag = (JillianFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_content);
		if (frag != null) mDualFragments = true;*/
		
		// find Jillian
		if (mJillianHidden) {
			getSupportFragmentManager().beginTransaction()
				.hide(getSupportFragmentManager().findFragmentById(R.id.fragment_jillian)).commit();
		}
		
		
	}
	
	// Options Menu
	// ------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		if (!mDualPane) {
			menu.findItem(R.id.menu_toggle_jillian).setTitle((mToggleJillian[mJillianHidden ? 0 : 1]));
		} else {
			menu.removeItem(R.id.menu_toggle_jillian);
		}
		//menu.removeItem(R.id.menu_toggleList);
		if (!mDualFragments){
			//menu.removeItem(R.id.menu_toggle_jillian);
		} else {
			//menu.findItem(R.id.menu_toggle_jillian).setTitle(mToggleJillian[mJillianHidden ? 0 : 1]);
		}
		
		//menu.findItem(R.id.menu_toggle_jillian).setTitle(mToggleJillian[mJillianHidden ? 0 : 1]);
		
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
			menu.removeItem(R.id.menu_share);
			//menu.removeItem(R.id.menu_toggleTheme);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			menu.removeItem(R.id.menu_share_gb);
		}
		
		// prefs menu item
		Intent prefsIntent = new Intent(getApplicationContext(), PrefsActivity.class);
		MenuItem preferences = menu.findItem(R.id.menu_prefs);
		preferences.setIntent(prefsIntent);
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case R.id.menu_testinghelper_button:
			SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(this);
			String mTheme = sPrefs.getString("theme_select_listpref_key", "");
			
			// unknown broken stuff
			//int myTheme = ESFitApplication.getThemeId();
			//int myTheme = ESFitApplication.getThemeId();
			//String mTheme = Integer.toString(myTheme);
			
			Toast.makeText(this, "Theme set to ? " + mTheme, Toast.LENGTH_SHORT).show();
			
			return true;
			
		case R.id.menu_toggle_jillian:
			FragmentManager fm = getSupportFragmentManager();
			JillianFragment f = (JillianFragment) fm.findFragmentById(R.id.fragment_jillian);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
			if (mJillianHidden) {
				ft.show(f);
				mJillianHidden = false;
			} else {
				ft.hide(f);
				mJillianHidden = true;
				
			}
			ft.commit();
			
			return true;
			
		case R.id.menu_prefs:
			//Toast.makeText(this, "You hit prefs", Toast.LENGTH_SHORT).show();
			startActivity(item.getIntent());
			return true;
		
		default:
			return super.onOptionsItemSelected(item);	
		
		}
	}
	
	/*private void toggleJillianTweets() {
		final FragmentManager fm = getSupportFragmentManager();
		final JillianFragment f = (JillianFragment) fm.findFragmentById(R.id.fragment_jillian);
		final View jillianView = f.getView();
		
		//Determine if in portrait, and whether showing or hiding jillian with toggle
		//final boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
		final boolean shouldShow = f.isHidden() || mCurrentJillianAnimator != null;
		
		// Cancel current jillian animation if there is one
		if (mCurrentJillianAnimator != null) 
			mCurrentJillianAnimator.cancel();
		
		if (shouldShow) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
			ft.show(f);
			ft.commit();
			
		} else {
			FragmentTransaction ft = fm.beginTransaction();
			ft.setCustomAnimations(android.R.anim.fade_out, android.R.anim.fade_in);
			ft.hide(f);
			ft.commit();
		}
		
		
		
		
	}*/

	@Override
	public void onSaveInstanceState (Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("jillianHidden", mJillianHidden);
		//outState.putInt("theme", mThemeId);
	}
	
	/**Implement the DashboardFragment.OnItemSelectedListener
	 * when dashboard frag receives onclick event
	 * it's passed back to this activity through this method so that we
	 * deliver it in the appropriate way to Content Activity  */
	public void onDbItemSelected() {
		if (!mDualPane) {
		Intent intent = new Intent(this, CalcActivity.class);
		startActivity(intent);
		} else {
			
		}
		
	}


	    
	
	
	
	
}