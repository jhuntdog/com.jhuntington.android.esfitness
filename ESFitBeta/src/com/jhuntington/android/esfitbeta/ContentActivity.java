package com.jhuntington.android.esfitbeta;


import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.jhuntington.android.esfitbeta.utils.CompatTab;
import com.jhuntington.android.esfitbeta.utils.CompatTabListener;
import com.jhuntington.android.esfitbeta.utils.ContentActivityHelper;
import com.jhuntington.android.esfitbeta.utils.TabHelper;

public class ContentActivity extends ContentActivityHelper {
	
	private String[] mToggleLabels = {"Show Titles", "Hide Titles"};
	private int mThemeId = -1;
	private boolean mDualFragments = false;
	
	public ShareActionProvider mShareActionProvider;
	
	private boolean mTitlesHidden = false;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState !=null) {
			if (savedInstanceState.getInt("theme", -1) != -1) {
				mThemeId = savedInstanceState.getInt("theme");
				this.setTheme(mThemeId);
			}
		}
		
		setContentView(R.layout.fragment_content);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar bar = getActionBar();
			bar.setDisplayShowTitleEnabled(true);
			bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		}
		
        
	    TabHelper tabHelper = getTabHelper();
	    
	    
	    CompatTab tabM3 = tabHelper.newTab("male3site")
	    		.setText(R.string.title_male3site)
        		.setIcon(R.drawable.ic_tab_m3)
	    		.setTabListener(new InstantiatingTabListener(this, CalcMaleThreeFragment.class));
	    tabHelper.addTab(tabM3);
	    
	    CompatTab tabF3 = tabHelper.newTab("female3site")
	    		.setText(R.string.title_female3site)
        		.setIcon(R.drawable.ic_tab_m3)
	    		.setTabListener(new InstantiatingTabListener(this, CalcFemaleThreeFragment.class));
	    tabHelper.addTab(tabF3);
	    
	    CompatTab tabM7 = tabHelper.newTab("male7site")
	    		.setText(R.string.title_male7site)
        		.setIcon(R.drawable.ic_tab_m3)
	    		.setTabListener(new InstantiatingTabListener(this, CalcMaleSevenFragment.class));
	    tabHelper.addTab(tabM7);
	    
	    CompatTab tabF7 = tabHelper.newTab("female7site")
	    		.setText(R.string.title_female7site)
        		.setIcon(R.drawable.ic_tab_m3)
	    		.setTabListener(new InstantiatingTabListener(this, CalcFemaleSevenFragment.class));
	    tabHelper.addTab(tabF7);
	    	  
	    
	    /*tabHelper.addTab(tabHelper.newTab("title_male3site")
        		.setText(R.string.title_male3site)
        		//.setIcon(R.drawable.ic_tab_m3)
        		.setTabListener(new TabListener<CalcMaleThreeFragment>(this, "title_male3site", CalcMaleThreeFragment.class)));
        
	    tabHelper.addTab(tabHelper.newTab("title_female3site")
        		.setText(R.string.title_female3site)
        		//.setIcon(R.drawable.ic_tab_f3)
        		.setTabListener(new TabListener<CalcFemaleThreeFragment>(this, "title_female3site", CalcFemaleThreeFragment.class)));
        
	    tabHelper.addTab(tabHelper.newTab("title_male7site")
        		.setText(R.string.title_male7site)
        		//.setIcon(R.drawable.ic_tab_m7)
        		.setTabListener(new TabListener<CalcMaleSevenFragment>(this, "title_male7site", CalcMaleSevenFragment.class)));
        
	    tabHelper.addTab(tabHelper.newTab("title_female7site")
        		.setText(R.string.title_female7site)
        		//.setIcon(R.drawable.ic_tab_f7)
        		.setTabListener(new TabListener<CalcFemaleSevenFragment>(this, "title_female7site", CalcFemaleSevenFragment.class)));*/
        
        
        /*if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }*/
        
        DashboardFragment frag = (DashboardFragment) getSupportFragmentManager()
        		.findFragmentById(R.id.fragment_dashboard);
        if (frag != null) mDualFragments = true;
        
	}
	
	// Options Menu
	// ------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.main_menu, menu);
		getMenuInflater().inflate(R.menu.main_menu, menu);
		
		MenuItem item = menu.findItem(R.id.menu_share);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			
			mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	    }
		
		
		
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		if (!mDualFragments) {
    		menu.removeItem(R.id.menu_toggleList);
    	} else {
    		menu.findItem(R.id.menu_toggleList).setTitle(mToggleLabels[mTitlesHidden ? 0 : 1]);
    	}
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		/*case R.id.menu_share:
			Toast.makeText(this, "You hit share", Toast.LENGTH_SHORT).show();
			return true;*/
		
		case R.id.menu_toggleTheme:
			// Toast.makeText(this, "You hit toggle theme", Toast.LENGTH_SHORT).show();
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
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("theme", mThemeId);
		//outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}
	
	
	public static class InstantiatingTabListener implements CompatTabListener {
		private final ContentActivityHelper mActivity;
		private final Class mClass;
		
		public InstantiatingTabListener(ContentActivityHelper activity, Class<? extends Fragment> cls) {
			mActivity = activity;
			mClass = cls;
		}
		
		@Override
		public void onTabSelected(CompatTab tab, FragmentTransaction ft) {
			// Check if the fragment is already initialized
			Fragment fragment = tab.getFragment(); 
			
			if (fragment == null) {
					fragment = Fragment.instantiate(mActivity, mClass.getName());
					tab.setFragment(fragment);
					ft.add(android.R.id.tabcontent, fragment, tab.getTag());
				} else {
					ft.attach(fragment);
				}
			
		}

		@Override
		public void onTabReselected(CompatTab tab, FragmentTransaction ft) {
			//Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onTabUnselected(CompatTab tab, FragmentTransaction ft) {
			Fragment fragment = tab.getFragment();
			if (fragment != null) {
				ft.detach(fragment);
			}
			
		}
		
	}
	
/*	public static class TabListener<T extends Fragment> implements CompatTabListener {
		private final ContentActivityHelper mActivity;
		private final String mTag;
		private final Class<T> mClass;
		private final Bundle mArgs;
		private Fragment mFragment;
		
		
		public TabListener(ContentActivityHelper activity, String tag, Class<T> clz) {
			this(activity, tag, clz, null);
		}

		
		public TabListener(ContentActivityHelper activity, String tag, Class<T> clz, Bundle args) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
			mArgs = args;
			
			// Check to see if we already have a fragment for this tab, probably
			// from a previously saved state.  If so, deactivate it, because our
			// initial state is that a tab isn't shown.
			mFragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
			if (mFragment != null && !mFragment.isDetached()) {
				FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
				ft.detach(mFragment);
				ft.commit();
			}
			
			
			
		}
		
		@Override
		public void onTabSelected(CompatTab tab, FragmentTransaction ft) {
			 if (mFragment == null) {
					mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
					ft.add(android.R.id.tabcontent, mFragment, mTag);
				} else {
					ft.attach(mFragment);
				}
			
		}

		@Override
		public void onTabReselected(CompatTab tab, FragmentTransaction ft) {
			Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
			
		}

		

		@Override
		public void onTabUnselected(CompatTab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
			
		}
		
	}*/
	
	

}
