package com.jhuntington.android.esfitbeta.utils;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabHelperEclair extends TabHelper implements TabHost.OnTabChangeListener {
	
	private final HashMap<String, CompatTab> mTabs = new HashMap<String, CompatTab>();
	private TabHost mTabHost;
	CompatTabListener mCallback;
	CompatTab mLastTab;

	protected TabHelperEclair(FragmentActivity activity) {
		super(activity);
		mActivity = activity;
	}
	
	@Override
	protected void setUp() {
		if (mTabHost == null) {
			mTabHost = (TabHost) mActivity.findViewById(android.R.id.tabhost);
			mTabHost.setup();
			mTabHost.setOnTabChangedListener(this);
		}
		
	}

	@Override
	public void addTab(CompatTab tab) {
		String tag = tab.getTag();
		TabSpec spec;

		if (tab.getIcon() != null) {
			spec = mTabHost.newTabSpec(tag).setIndicator(tab.getText(), tab.getIcon());
		} else {
			spec = mTabHost.newTabSpec(tag).setIndicator(tab.getText());
		}

		spec.setContent(new DummyTabFactory(mActivity));

		// Check to see if we already have a fragment for this tab, probably
		// from a previously saved state.  If so, deactivate it, because our
		// initial state is that a tab isn't shown.

		Fragment fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
		tab.setFragment(fragment);

		if (fragment != null && !fragment.isDetached()) {
			FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
			ft.detach(fragment);
			ft.commit();
		}

		mTabs.put(tag, tab);
		mTabHost.addTab(spec);
		
	}

	@Override
	public void onTabChanged(String tabId) {
		CompatTab newTab = mTabs.get(tabId);
		FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();

		if (mLastTab != newTab) {
			if (mLastTab != null) {
				if (mLastTab.getFragment() != null) {
					// Pass the unselected event back to the tab's CompatTabListener
					mLastTab.getCallback().onTabUnselected(mLastTab, ft);
				}
			}
			if (newTab != null) {
				// Pass the selected event back to the tab's CompatTabListener
				newTab.getCallback().onTabSelected(newTab, ft);
			}

			mLastTab = newTab;
		} else {
			// Pass the re-selected event back to the tab's CompatTabListener
			newTab.getCallback().onTabReselected(newTab, ft);
		}

		ft.commit();
		mActivity.getSupportFragmentManager().executePendingTransactions();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Save and restore the selected tab for rotations/restarts.
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}
	
	/**
	 * Backwards-compatibility mumbo jumbo
	 */
	static class DummyTabFactory implements TabHost.TabContentFactory {

		private final Context mContext;

		public DummyTabFactory(Context context) {
			mContext = context;
		}

		@Override
		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}
	}

	
	
	

}
