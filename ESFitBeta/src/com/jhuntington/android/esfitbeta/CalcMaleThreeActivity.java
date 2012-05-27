package  com.jhuntington.android.esfitbeta;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalcMaleThreeActivity extends Activity {
	
	public static final String DEBUG_TAG = "CalcMaleThreeActivity";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Bundle extras = getIntent().getExtras();
		
		setContentView(R.layout.fragment_content_mthree);
		
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
