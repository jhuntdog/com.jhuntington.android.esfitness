package com.jhuntington.android.esfit.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.jhuntington.android.esfit.R;


public class ContentActivity extends BaseActivity {
	
	private String[] mToggleLabels = {"Show Titles", "Hide Titles"};
	private int mThemeId = 0;
	private boolean mDualFragments = false;
	private boolean mTitlesHidden = false;
	
	public ShareActionProvider mShareActionProvider;
	SharedPreferences preferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_content);
		
		ActionBar bar = getSupportActionBar();
		bar.setDisplayShowTitleEnabled(true);
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		bar.addTab(bar.newTab()
				.setText(R.string.title_male3site)
				.setTabListener(new TabListener<CalcMaleThreeFragment>(this, "title_male3site", CalcMaleThreeFragment.class)));
		
		bar.addTab(bar.newTab()
				.setText(R.string.title_female3site)
				.setTabListener(new TabListener<CalcFemaleThreeFragment>(this, "title_female3site", CalcFemaleThreeFragment.class)));
		
		bar.addTab(bar.newTab()
				.setText(R.string.title_male7site)
				.setTabListener(new TabListener<CalcMaleSevenFragment>(this, "title_male7site", CalcMaleSevenFragment.class)));
		
		bar.addTab(bar.newTab()
				.setText(R.string.title_female7site)
				.setTabListener(new TabListener<CalcFemaleSevenFragment>(this, "title_female7site", CalcFemaleSevenFragment.class)));
		
		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}
		
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
		getSupportMenuInflater().inflate(R.menu.content_menu, menu);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			MenuItem item = menu.findItem(R.id.menu_share);
			
			mShareActionProvider = (ShareActionProvider) item.getActionProvider();
			mShareActionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
		}
		
		
		
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		/*if (!mDualFragments) {
			//menu.removeItem(R.id.menu_toggleList);
		} else {
			menu.findItem(R.id.menu_toggleList).setTitle(mToggleLabels[mTitlesHidden ? 0 : 1]);
		}*/
		
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
			
			menu.removeItem(R.id.menu_share);
			//menu.removeItem(R.id.menu_toggleTheme);
		}
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			menu.removeItem(R.id.menu_share_gb);
		}
		

		return super.onPrepareOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		/*case R.id.menu_share:
			Toast.makeText(this, "You hit share", Toast.LENGTH_SHORT).show();
			return true;*/
		
		case R.id.menu_share_gb:
			//Toast.makeText(this, "You hit share", Toast.LENGTH_SHORT).show();
			Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			startActivity(Intent.createChooser(shareIntent, getText(R.string.title_share)));
		return true;

		default:
			return super.onOptionsItemSelected(item);	
		
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("tab", getSupportActionBar().getSelectedNavigationIndex());
	}
	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private final SherlockFragmentActivity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		private final Bundle mArgs;
		private Fragment mFragment;
		
		
		public TabListener(SherlockFragmentActivity activity, String tag, Class<T> clz) {
			this(activity, tag, clz, null);
		}

		public TabListener(SherlockFragmentActivity activity, String tag, Class<T> clz, Bundle args) {
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
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			 if (mFragment == null) {
					mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
					ft.add(android.R.id.content, mFragment, mTag);
				} else {
					ft.attach(mFragment);
				}
			
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			//Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
			
		}

		

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
			
		}
		
	}

}
