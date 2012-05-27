package com.jhuntington.android.esfitbeta.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;

public class ContentActivityHelper extends FragmentActivity {
	
	final ActionBarHelper mActionBarHelper = ActionBarHelper.createInstance(this);

	TabHelper mTabHelper;
	
	protected ActionBarHelper getActionBarHelper() {
		return mActionBarHelper;
	}
	
	protected TabHelper getTabHelper() {
		mTabHelper.setUp();
		return mTabHelper;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTabHelper = TabHelper.createInstance(this);
		mActionBarHelper.onCreate(savedInstanceState);
		
	}
	
	// Tab Helper Stuff
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
	
	
	// ActionbarHelper Stuff
	@Override
	public MenuInflater getMenuInflater() {
		return mActionBarHelper.getMenuInflater(super.getMenuInflater());
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mActionBarHelper.onPostCreate(savedInstanceState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean retValue = false;
		retValue |= mActionBarHelper.onCreateOptionsMenu(menu);
		retValue |= super.onCreateOptionsMenu(menu);
		return retValue;
	}
	
	@Override
	protected void onTitleChanged(CharSequence title, int color) {
		mActionBarHelper.onTitleChanged(title, color);
		super.onTitleChanged(title, color);
	}
	
	
	
}
