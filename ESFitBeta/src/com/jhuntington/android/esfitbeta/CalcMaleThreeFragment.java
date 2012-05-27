package com.jhuntington.android.esfitbeta;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;

public class CalcMaleThreeFragment extends Fragment {
	
	public static final String DEBUG_TAG = "CalcMaleThreeFragment";
	
	private boolean mSoloFragment = false;
	private View mCalcView = null;
	
	private EditText editTextAge;
	private EditText editTextWeight;

	private EditText editTextPectoral;
	private EditText editTextAbdomen;
	private EditText editTextQuadriceps;
	
	private Button btnCalc;
	private Button btnReset;
	
	private TextView textViewResultBodyDensity;
	private TextView textViewResultPercentFat;
	
	private ShareActionProvider mShareActionProvider;
	
	public CalcMaleThreeFragment() {
		super();
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
		
		mCalcView = (View) inflater.inflate(R.layout.calc_male_three_site, container, false);
		
		editTextAge = (EditText) mCalcView.findViewById(R.id.editTextAge);
        editTextAge.requestFocus();
        editTextWeight = (EditText) mCalcView.findViewById(R.id.editTextWeight);

        editTextPectoral = (EditText) mCalcView.findViewById(R.id.editTextPectoral);
        editTextAbdomen = (EditText) mCalcView.findViewById(R.id.editTextAbdomen);
        editTextQuadriceps = (EditText) mCalcView.findViewById(R.id.editTextQuadriceps);
        
        btnCalc = (Button) mCalcView.findViewById(R.id.btnCalc);
		btnCalc.setEnabled(false);
		btnReset = (Button) mCalcView.findViewById(R.id.btnReset);
        
		textViewResultBodyDensity = (TextView) mCalcView.findViewById(R.id.textViewResultBodyDensity);
		textViewResultPercentFat = (TextView) mCalcView.findViewById(R.id.textViewResultPercentFat);
        
		/* KeyListeners */
		editTextAge.setOnKeyListener(mKeyListener);
		editTextWeight.setOnKeyListener(mKeyListener);

		editTextPectoral.setOnKeyListener(mKeyListener);
		editTextAbdomen.setOnKeyListener(mKeyListener);
		editTextQuadriceps.setOnKeyListener(mKeyListener);
		
		/* ClickListeners */
		btnCalc.setOnClickListener(mClickListener);
		btnReset.setOnClickListener(mClickListener);
		
		return mCalcView;
		
	}
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar bar = getActivity().getActionBar();
			bar.setDisplayHomeAsUpEnabled(true);
			bar.setDisplayShowTitleEnabled(true);
		}

		setHasOptionsMenu(true);
		
	}
	

	
	@Override
    public void onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.menu_share);
		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(getActivity(), MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	
	
	private OnKeyListener mKeyListener = new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {

			switch (v.getId()) {
			case R.id.editTextAge:
			case R.id.editTextWeight:
			case R.id.editTextPectoral:
			case R.id.editTextAbdomen:
			case R.id.editTextQuadriceps:
				btnCalc.setEnabled(editTextAge.getText().length() > 0
						&& editTextWeight.getText().length() > 0
						&& editTextPectoral.getText().length() > 0
						&& editTextAbdomen.getText().length() > 0
						&& editTextQuadriceps.getText().length() > 0);
				break;
			}
			return false;
		}

	};
	
	/*
	 * ClickListener for the Calculate and Reset buttons. Depending on the
	 * button clicked, the corresponding method is called.
	 */
	private OnClickListener mClickListener = new OnClickListener() {

		public void onClick(View v) {
			if (v.getId() == R.id.btnCalc) {
				calculate();
			} else {
				reset();
			}
		}
	};
	
	/* Calculate */
	private void calculate() {
		double nAge = Double.parseDouble(editTextAge.getText().toString());
		double nWeight = Double.parseDouble(editTextWeight.getText().toString());
		double nPectoral = Double.parseDouble(editTextPectoral.getText().toString());
		double nAbdomen = Double.parseDouble(editTextAbdomen.getText().toString());
		double nQuadriceps = Double.parseDouble(editTextQuadriceps.getText().toString());
		
		double sumThreeMale = (nPectoral + nAbdomen + nQuadriceps);
		Double nBodyDensity = null;
		boolean isError = false;
		
		if (nAge < 1.0) {
			showErrorAlert("Enter a valid Age.", editTextAge.getId());
			isError = true;
		}
		if (nWeight < 1.0) {
			showErrorAlert("Enter a valid value for Weight", editTextWeight.getId());
			isError = true;
		}
		if (nPectoral < 1.0) {
			showErrorAlert("Enter a valid value for Pecs", editTextPectoral.getId());
			isError = true;
		}
		if (nAbdomen < 1.0) {
			showErrorAlert("You wish you had no tummy fat", editTextAbdomen.getId());
			isError = true;
		}
		if (nQuadriceps < 1.0) {
			showErrorAlert("Somebody workin' legs too much?", editTextQuadriceps.getId());
			isError = true;
		}
		
		if (!isError) {
			nBodyDensity = (1.10938 - (0.0008267 * sumThreeMale) + (0.0000016 * (sumThreeMale * sumThreeMale)) - (0.0002574 * nAge));
			Double nBodyFat = (495 / nBodyDensity) - 450;
			
			textViewResultBodyDensity.setText(nBodyDensity.toString());
			textViewResultPercentFat.setText(nBodyFat.toString());
			
			String percentFatStr = textViewResultPercentFat.getText().toString();
			
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "I want to tell you about your body");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "Your Percent Body Fat is " + "\n" + percentFatStr + "." + "\n" + "Eat more cauliflower!");
			
			mShareActionProvider.setShareIntent(shareIntent);
		}
		
	}
	
	/* Reset */
	private void reset() {
		editTextAge.setText("");
		editTextWeight.setText("");
		editTextPectoral.setText("");
		editTextAbdomen.setText("");
		editTextQuadriceps.setText("");
		textViewResultBodyDensity.setText("");
		textViewResultPercentFat.setText("");
		editTextAge.requestFocus();
	}
	
	/* Shows the error message in an alert dialog
	 * @param errorMessage
	 *            String the error message to show
	 * @param fieldId
	 *            the Id of the field which caused the error. This is required
	 *            so that the focus can be set on that field once the dialog is
	 *            dismissed.
	 */
	private void showErrorAlert(String errorMessage, final int fieldId) {
		new AlertDialog.Builder(this.getActivity()).setTitle("Error")
				.setMessage(errorMessage).setNeutralButton("Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								mCalcView.findViewById(fieldId).requestFocus();
							}
						}).show();
	}
	

}
