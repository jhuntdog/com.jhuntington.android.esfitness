package  com.jhuntington.android.esfitbeta;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class CalcFemaleSevenActivity extends Activity {
	
	public static final String DEBUG_TAG = "CalcFemaleSevenActivity";
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Bundle extras = getIntent().getExtras();
		
		setContentView(R.layout.fragment_content_fseven);
		
		ActionBar bar = getActionBar();
	    bar.setDisplayShowTitleEnabled(true);
		

	}
	
	// Options Menu
	// ------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
		
		
		return true;
	}
	
	
	  @Override
	  protected void onSaveInstanceState(Bundle outState) {
	      super.onSaveInstanceState(outState);

	  }
	

}
