package com.jhuntington.android.esfit.ui;

import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.jhuntington.android.esfit.R;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class CalcActivity extends BaseActivity {
	
	ViewPager mViewPager;

	TabsAdapter mTabsAdapter;

	private ShareActionProvider mShareActionProvider;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calc_multipane);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab mthreeTab = actionBar.newTab().setText(R.string.title_male3site);
		ActionBar.Tab fthreeTab = actionBar.newTab().setText(R.string.title_female3site);
		ActionBar.Tab msevenTab = actionBar.newTab().setText(R.string.title_male7site);
		ActionBar.Tab fsevenTab = actionBar.newTab().setText(R.string.title_female7site);
		
		mViewPager = (ViewPager) findViewById(R.id.pager);

		mTabsAdapter = new TabsAdapter(this, actionBar, mViewPager);
		
		/* if need to add arguments 
		 * you bundle here and add in place of "null" below, like
		 * final Bundle argsCM3 = new Bundle();
		 * argsCM3.putString(InitBundle.ANALYTICS_Tag, "/CalcMaleThree";
		 * argsCM3.putInt(InitBundle.LOADER_ID, 10);*/
		mTabsAdapter.addTab(mthreeTab, CalcMaleThreeFragment.class, null);
		mTabsAdapter.addTab(fthreeTab, CalcFemaleThreeFragment.class, null);
		mTabsAdapter.addTab(msevenTab, CalcMaleSevenFragment.class, null);
		mTabsAdapter.addTab(fsevenTab, CalcFemaleSevenFragment.class, null);
		
		if (savedInstanceState != null) {
			actionBar.setSelectedNavigationItem(savedInstanceState.getInt("index"));
		}
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("index", getSupportActionBar().getSelectedNavigationIndex());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.menu_shareprovider, menu);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			MenuItem item = menu.findItem(R.id.menu_share);
			mShareActionProvider = (ShareActionProvider) item.getActionProvider();
			mShareActionProvider.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
		}
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
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

		default:
			return super.onOptionsItemSelected(item);	
		
		}
	}
	
	
	public static class TabsAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, ActionBar.TabListener {
		
		private final Context mContext;
		private final ActionBar mActionBar;
		private final ViewPager mViewPager;
		private final ArrayList<String> mTabs = new ArrayList<String>();
		private final ArrayList<Bundle> mArgs = new ArrayList<Bundle>();

		public TabsAdapter(FragmentActivity activity, ActionBar actionBar, ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = actionBar;
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}
		
		public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
			mTabs.add(clss.getName());
			mArgs.add(args);
			mActionBar.addTab(tab.setTabListener(this));
			notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			return mTabs.size();
		}
		
		@Override
		public Fragment getItem(int position) {
			return Fragment.instantiate(mContext, mTabs.get(position), mArgs.get(position));
		}
		
		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {			
		}
		
		@Override
		public void onPageSelected(int position) {
			mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			mViewPager.setCurrentItem(tab.getPosition());
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
		
	}
	

}
