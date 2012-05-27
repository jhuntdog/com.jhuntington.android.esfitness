package com.jhuntington.android.esfitbeta;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jhuntington.android.esfitbeta.utils.ActivityHelper;


public class MainActivity extends ActivityHelper {
	
	//DashboardFragment mDashboardFragment;
	//ContentFragment mContentFragment;
	
	private String[] mToggleLabels = {"Show Titles", "Hide Titles"};
	private int mThemeId = -1;
	private boolean mDualFragments = false;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if(savedInstanceState !=null) {
			if (savedInstanceState.getInt("theme", -1) != -1) {
				mThemeId = savedInstanceState.getInt("theme");
				this.setTheme(mThemeId);
			}
		}
        
        setContentView(R.layout.main);
        
        /*ActionBar bar = getActionBar();
        bar.setDisplayShowTitleEnabled(true);*/
        
        // find our fragments
        DashboardFragment mDashboardFragment = (DashboardFragment) getSupportFragmentManager()
        		.findFragmentById(R.id.fragment_dashboard);
        
        
        
        
    }
    
	// Options Menu
	// ------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        
		return true;
	}
    
	@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	
		menu.removeItem(R.id.menu_toggleList);

    	return super.onPrepareOptionsMenu(menu);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		/*case R.id.menu_share:
			Toast.makeText(this, "You hit share", Toast.LENGTH_SHORT).show();
			return true;*/
		
		case R.id.menu_toggleTheme:
			//Toast.makeText(this, "You hit toggle theme", Toast.LENGTH_SHORT).show();
			if (mThemeId == R.style.AppTheme_Dark) {
				mThemeId = R.style.AppTheme_Light;
			} else {
				mThemeId = R.style.AppTheme_Dark;
			}
			this.recreate();
			return true;
		
		case R.id.menu_toggleList:
			//toggleVisibleTitles();
			Toast.makeText(this, "You hit toggle list", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.menu_prefs:
			Toast.makeText(this, "You hit prefs", Toast.LENGTH_SHORT).show();
			return true;
		
		default:
            return super.onOptionsItemSelected(item);	
		
		}
	}
	
	@Override
	public void onSaveInstanceState (Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("theme", mThemeId);
	}
    

	
	
	
	
    
}