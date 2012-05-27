package com.jhuntington.android.esfit;

import com.jhuntington.android.esfit.ui.PrefsActivity;
import com.jhuntington.android.esfit.util.Utils;

import android.app.Application;
import android.preference.PreferenceManager;

public class ESFitApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		String theme = PreferenceManager.getDefaultSharedPreferences(this).getString(PrefsActivity.KEY_THEME, "0");
		Utils.updateTheme(theme);
		
	}
	


}
