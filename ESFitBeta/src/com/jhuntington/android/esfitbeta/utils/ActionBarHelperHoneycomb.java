package com.jhuntington.android.esfitbeta.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jhuntington.android.esfitbeta.R;

public class ActionBarHelperHoneycomb extends ActionBarHelper {
	private Menu mOptionsMenu;
    private View mRefreshIndeterminateProgressView = null;

	protected ActionBarHelperHoneycomb(Activity activity) {
		super(activity);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mOptionsMenu = menu;
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void setRefreshActionItemState(boolean refreshing) {
		// On Honeycomb, we can set the state of the refresh button by giving it a custom actionview
		if (mOptionsMenu == null) {
			return;
		}
		
		final MenuItem refreshItem = mOptionsMenu.findItem(R.id.menu_refresh);
		if (refreshItem != null) {
			if (refreshing) {
				if (mRefreshIndeterminateProgressView == null) {
					LayoutInflater inflater = (LayoutInflater)
							getActionBarThemedContext().getSystemService(
									Context.LAYOUT_INFLATER_SERVICE);
					mRefreshIndeterminateProgressView = inflater.inflate(
							R.layout.actionbar_indeterminate_progress, null);
				}

				refreshItem.setActionView(mRefreshIndeterminateProgressView);
			} else {
				refreshItem.setActionView(null);
			}
		}
	}

	protected Context getActionBarThemedContext() {
		return mActivity;
	}

}
