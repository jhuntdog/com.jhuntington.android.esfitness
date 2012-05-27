package com.jhuntington.android.esfit.ui;

import com.jhuntington.android.esfit.R;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class AboutActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	        
        setContentView(R.layout.dialog_custom);
        
        TextView aboutTextView = (TextView)findViewById(R.id.custom_dialog_text_view);
        aboutTextView.setText(Html.fromHtml(getString(R.string.dialog_about_text_html)));
	}

}
