package com.jhuntington.android.esfit.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jhuntington.android.esfit.ESFitApplication;
import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.R.layout;

public class ClientActivity extends SherlockFragmentActivity {
	
	SharedPreferences preferences;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_clients);
		
	}
	

}
