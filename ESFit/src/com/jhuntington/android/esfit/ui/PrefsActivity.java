package com.jhuntington.android.esfit.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.util.Utils;

public class PrefsActivity extends SherlockPreferenceActivity implements OnSharedPreferenceChangeListener {
	
	//public static final String KEY_LIST_PREFERENCE = "theme_select_listpref_key";
	
	//public static final String KEY_THEME = "com.jhuntington.android.esfit.theme";
	public static final String KEY_THEME = "theme_select_listpref_key";
	
	public static int THEME = R.style.AppThemeLight;
	
	private SharedPreferences sharedPreferences;
	private ListPreference mListPreference;

	private String key;

	//private String key;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);

		final PrefsActivity activity = this;
		// Load XML preferences file
		addPreferencesFromResource(R.xml.preferences);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		PreferenceManager.setDefaultValues(PrefsActivity.this, R.xml.preferences, false);
		
		for(int i=0;i<getPreferenceScreen().getPreferenceCount();i++){
            initSummary(getPreferenceScreen().getPreference(i));
           }
		
		
		final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
		
		 // Theme switcher
		findPreference(KEY_THEME).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			@Override
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				if (preference.getKey().equals(KEY_THEME)) {
					Utils.updateTheme((String) newValue);
				}
				return true;
			}
		});
		
	}
	
	@Override
    protected void onResume() {
		super.onResume();

	}
	
	@Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
	
	
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) { 
        updatePrefSummary(findPreference(key));
    }
	
	
	private void initSummary(Preference p){
        if (p instanceof PreferenceCategory){
             PreferenceCategory pCat = (PreferenceCategory)p;
             for(int i=0;i<pCat.getPreferenceCount();i++){
                 initSummary(pCat.getPreference(i));
             }
         } else{
             updatePrefSummary(p);
         }

     }
	
	private void updatePrefSummary(Preference p){
        if (p instanceof ListPreference) {
            ListPreference listPref = (ListPreference) p; 
            p.setSummary(listPref.getEntry()); 
        }
       

    }

	
	// Options Menu
	// ------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}


	
	

	
		

	 
	
	

}
