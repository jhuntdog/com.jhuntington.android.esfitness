package com.jhuntington.android.esfitbeta.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TabCompatActivity extends FragmentActivity {
	
	TabHelper mTabHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTabHelper = TabHelper.createInstance(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mTabHelper.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mTabHelper.onRestoreInstanceState(savedInstanceState);
	}

	/**
	 * Returns the {@link TabHelper} for this activity.
	 */
	protected TabHelper getTabHelper() {
		mTabHelper.setUp();
		return mTabHelper;
	}
	
	

}
