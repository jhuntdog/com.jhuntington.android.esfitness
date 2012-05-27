package com.jhuntington.android.esfit.util;

import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.ui.PrefsActivity;

public class Utils {
	
	private static final String TAG = "Utils";
	
	public static synchronized void updateTheme(String themeIndex) {
		int theme = Integer.valueOf((String) themeIndex);
		switch (theme) {
		case 1:
			PrefsActivity.THEME = R.style.AppThemeDark;
			break;
		case 2:
			PrefsActivity.THEME = R.style.AppThemeStyled;
			break;
		default:
			PrefsActivity.THEME = R.style.AppThemeLight;
			break;
		}
	}
	

}
