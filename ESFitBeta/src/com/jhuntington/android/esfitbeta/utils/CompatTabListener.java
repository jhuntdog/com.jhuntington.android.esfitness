package com.jhuntington.android.esfitbeta.utils;

import android.support.v4.app.FragmentTransaction;


// implement ActionBar.TabListener functions
public interface CompatTabListener {
	
	 public void onTabSelected(CompatTab tab, FragmentTransaction ft);
	 
	 public void onTabUnselected(CompatTab tab, FragmentTransaction ft);
	 
	 public void onTabReselected(CompatTab tab, FragmentTransaction ft);
	

}
