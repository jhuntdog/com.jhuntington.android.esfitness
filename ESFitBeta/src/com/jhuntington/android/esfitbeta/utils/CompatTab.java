package com.jhuntington.android.esfitbeta.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


// represents a tab, mirrors the ActionBar.Tab interface
public abstract class CompatTab {
	
	final FragmentActivity mActivity;
	final String mTag;

	protected CompatTab(FragmentActivity activity, String tag) {
		mActivity = activity;
		mTag = tag;
	}
	
	public abstract CompatTab setText(int resId);
    public abstract CompatTab setIcon(int resId);
    public abstract CompatTab setTabListener(CompatTabListener callback);
    public abstract CompatTab setFragment(Fragment fragment);

    public abstract CharSequence getText();
    public abstract Drawable getIcon();
    public abstract CompatTabListener getCallback();
    public abstract Fragment getFragment();
    
    public abstract Object getTab();
    
    public String getTag() {
    	return mTag;
    }
	

}
	
