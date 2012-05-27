package com.jhuntington.android.esfitbeta.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public abstract class ActionBarHelper {
	protected Activity mActivity;
	
	public static ActionBarHelper createInstance(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return new ActionBarHelperICS(activity);
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			return new ActionBarHelperHoneycomb(activity);
		} else {
			return new ActionBarHelperBase(activity);
		}
	}
	
	
	protected ActionBarHelper(Activity activity) {
        mActivity = activity;
    }
	
	/**
	 * Action bar helper code to be run in {@link Activity#onCreate(android.os.Bundle)}.
	 */
	public void onCreate(Bundle savedInstanceState) {
	}

	/**
	 * Action bar helper code to be run in {@link Activity#onPostCreate(android.os.Bundle)}.
	 */
	public void onPostCreate(Bundle savedInstanceState) {
	}

	/**
	 * Action bar helper code to be run in {@link Activity#onCreateOptionsMenu(android.view.Menu)}.
	 *
	 * NOTE: Setting the visibility of menu items in <em>menu</em> is not currently supported.
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	/**
	 * Action bar helper code to be run in {@link Activity#onTitleChanged(CharSequence, int)}.
	 */
	protected void onTitleChanged(CharSequence title, int color) {
	}

	/**
	 * Sets the indeterminate loading state of the item with ID {@link R.id.menu_refresh}.
	 * (where the item ID was menu_refresh).
	 */
	public abstract void setRefreshActionItemState(boolean refreshing);

	/**
	 * Returns a {@link MenuInflater} for use when inflating menus. The implementation of this
	 * method in {@link ActionBarHelperBase} returns a wrapped menu inflater that can read
	 * action bar metadata from a menu resource pre-Honeycomb.
	 */
	public MenuInflater getMenuInflater(MenuInflater superMenuInflater) {
		return superMenuInflater;
	}
	

}
