package com.jhuntington.android.esfitbeta.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class TabHelperHoneycomb extends TabHelper {
	
	ActionBar mActionBar;
	
	protected TabHelperHoneycomb(FragmentActivity activity) {
		super(activity);
	}
	
	protected void setUp() {
        if (mActionBar == null) {
            mActionBar = mActivity.getActionBar();
            mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
    }

	@Override
	public void addTab(CompatTab tab) {
		String tag = tab.getTag();

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

		if (tab.getCallback() == null) {
			throw new IllegalStateException("CompatTab must have a CompatTabListener");
		}

		// We know tab is a CompatTabHoneycomb instance, so its
		// native tab object is an ActionBar.Tab.
		mActionBar.addTab((ActionBar.Tab) tab.getTab());
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		int position = mActionBar.getSelectedTab().getPosition();
		outState.putInt("tab_position", position);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		int position = savedInstanceState.getInt("tab_position");
		mActionBar.setSelectedNavigationItem(position);
	}
	
	

}
