package com.jhuntington.android.esfit.ui;

import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.ShareActionProvider;
import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.R.id;
import com.jhuntington.android.esfit.R.layout;

public class CalcFemaleThreeFragment extends SherlockFragment {
	
	public static final String DEBUG_TAG = "CalcFemaleThreeFragment";
	
	private boolean mSoloFragment = false;
	private View mCalcView = null;
	
	private EditText editTextAge;
	private EditText editTextWeight;

	private EditText editTextTriceps;
	private EditText editTextSuprailiac;
	private EditText editTextQuadriceps;
	
	private Button btnCalc;
	private Button btnReset;
	
	private TextView textViewResultBodyDensity;
	private TextView textViewResultPercentFat;
	private TextView textViewResultLeanWeight;
	private TextView textViewResultFatWeight;
	private TextView textViewResultPopulationAverage;
	private TextView textViewResultScore;
	private TextView textViewResultRating;
	
	private ShareActionProvider mShareActionProvider;
	
	public CalcFemaleThreeFragment() {
		super();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		mCalcView = (View) inflater.inflate(R.layout.calc_female_three_site, container, false);
		
		editTextAge = (EditText) mCalcView.findViewById(R.id.editTextAge);
        editTextAge.requestFocus();
        editTextWeight = (EditText) mCalcView.findViewById(R.id.editTextWeight);

        editTextTriceps = (EditText) mCalcView.findViewById(R.id.editTextTriceps);
        editTextSuprailiac = (EditText) mCalcView.findViewById(R.id.editTextSuprailiac);
        editTextQuadriceps = (EditText) mCalcView.findViewById(R.id.editTextQuadriceps);
        
        btnCalc = (Button) mCalcView.findViewById(R.id.btnCalc);
		btnCalc.setEnabled(false);
		btnReset = (Button) mCalcView.findViewById(R.id.btnReset);
        
		textViewResultBodyDensity = (TextView) mCalcView.findViewById(R.id.textViewResultBodyDensity);
		textViewResultPercentFat = (TextView) mCalcView.findViewById(R.id.textViewResultPercentFat);
		
		textViewResultLeanWeight = (TextView) mCalcView.findViewById(R.id.textViewResultLeanWeight);
		textViewResultFatWeight = (TextView) mCalcView.findViewById(R.id.textViewResultFatWeight);
		textViewResultPopulationAverage = (TextView) mCalcView.findViewById(R.id.textViewResultPopulationAverage);
		textViewResultScore = (TextView) mCalcView.findViewById(R.id.textViewResultScore);
		textViewResultRating = (TextView) mCalcView.findViewById(R.id.textViewResultRating);
        
		/* KeyListeners */
		editTextAge.setOnKeyListener(mKeyListener);
		editTextWeight.setOnKeyListener(mKeyListener);

		editTextTriceps.setOnKeyListener(mKeyListener);
		editTextSuprailiac.setOnKeyListener(mKeyListener);
		editTextQuadriceps.setOnKeyListener(mKeyListener);
		
		/* ClickListeners */
		btnCalc.setOnClickListener(mClickListener);
		btnReset.setOnClickListener(mClickListener);
		
		return mCalcView;
		
	}
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ActionBar bar = getSherlockActivity().getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		bar.setDisplayShowTitleEnabled(true);
		setHasOptionsMenu(true);
		
	}
	

	
	@Override
   public void onPrepareOptionsMenu(Menu menu) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			MenuItem item = menu.findItem(R.id.menu_share);
			mShareActionProvider = (ShareActionProvider) item.getActionProvider();
			//mShareActionProvider.setShareIntent(myShareIntent);
		}
	}
	
	@Override
   public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
    private OnKeyListener mKeyListener = new OnKeyListener() {
		public boolean onKey(View v, int keyCode, KeyEvent event) {

			switch (v.getId()) {
			case R.id.editTextAge:
			case R.id.editTextWeight:
			case R.id.editTextTriceps:
			case R.id.editTextSuprailiac:
			case R.id.editTextQuadriceps:
				btnCalc.setEnabled(editTextAge.getText().length() > 0
						&& editTextWeight.getText().length() > 0
						&& editTextTriceps.getText().length() > 0
						&& editTextSuprailiac.getText().length() > 0
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
		double nTriceps = Double.parseDouble(editTextTriceps.getText().toString());
		double nSuprailiac = Double.parseDouble(editTextSuprailiac.getText().toString());
		double nQuadriceps = Double.parseDouble(editTextQuadriceps.getText().toString());
		
		double sumThreeFemale = (nTriceps + nSuprailiac + nQuadriceps);
		Double nBodyDensity = null;
		boolean isError = false;
		String stdDev = null;
		
		if (nAge < 1.0) {
			showErrorAlert("Enter a valid Age.", editTextAge.getId());
			isError = true;
		}
		if (nWeight < 1.0) {
			showErrorAlert("Enter a valid value for Weight", editTextWeight.getId());
			isError = true;
		}
		if (nTriceps < 1.0) {
			showErrorAlert("Enter a valid value for Pecs", editTextTriceps.getId());
			isError = true;
		}
		if (nSuprailiac < 1.0) {
			showErrorAlert("You wish you had no tummy fat", editTextSuprailiac.getId());
			isError = true;
		}
		if (nQuadriceps < 1.0) {
			showErrorAlert("Somebody workin' legs too much?", editTextQuadriceps.getId());
			isError = true;
		}
		
		if (!isError) {
			nBodyDensity = (1.0994921 - (0.0009929 * sumThreeFemale) + (0.0000023 * (sumThreeFemale * sumThreeFemale)) - (0.0001392 * nAge));
			Double nBodyFat = (495 / nBodyDensity) - 450;
			
			
			Double nFatWeight = ((nWeight * nBodyFat) / 100);
			Double nLeanWeight = (nWeight - nFatWeight);
			Double nPopulationAverage = (21.55 + (0.1 * nAge));
			
			
			if (nBodyFat <= nPopulationAverage) {
				stdDev = "8";
			} else {
				stdDev = "7";
			}
			
			double nStdDev = Double.parseDouble(stdDev);
			
			Double nZscore = ((nPopulationAverage - nBodyFat) / nStdDev);
			
			Double nPE = Math.exp(-1.8355027 * (Math.abs(nZscore) - 0.23073201));
			Double nPercRegression = (-0.41682992 * (nPE - 1) / (nPE + 1) + 0.58953708);
			
			
			textViewResultBodyDensity.setText(nBodyDensity.toString());
			textViewResultPercentFat.setText(nBodyFat.toString());
			
			textViewResultFatWeight.setText(nFatWeight.toString());
			textViewResultLeanWeight.setText(nLeanWeight.toString());
			textViewResultPopulationAverage.setText(nPopulationAverage.toString());
			
			//Score
			if (nZscore > 0) {
				Double nScoreValue = (double) Math.round(nPercRegression * 100);
				textViewResultScore.setText(nScoreValue.toString());		
			}
			if (nZscore <= 0) {
				Double nScoreValue = (double) Math.round((1 - nPercRegression) * 100);
				textViewResultScore.setText(nScoreValue.toString());		
			}
			
			//Rating
			if (nZscore >= 1) {
				textViewResultRating.setText("Excellent");
			}
			if (nZscore < 1 && nZscore >= 0.5) {
				textViewResultRating.setText("Good");
			}
			if (nZscore < 0.5 && nZscore >= -0.5) {
				textViewResultRating.setText("Average");
			}
			if (nZscore < -0.5 && nZscore >= -1) {
				textViewResultRating.setText("Fair");
			}
			if (nZscore < -1) {
				textViewResultRating.setText("Poor");
			}


			
			String percentFatStr = textViewResultPercentFat.getText().toString();
			
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");	
			shareIntent.putExtra(Intent.EXTRA_TEXT, "Your Percent Body Fat is " + "\n" + percentFatStr + "." + "\n" + "Flaunt them butt dimples!");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "State of your body");
			
			mShareActionProvider.setShareIntent(shareIntent);
		}
		
	}
	
	/* Reset */
	private void reset() {
		editTextAge.setText("");
		editTextWeight.setText("");
		editTextTriceps.setText("");
		editTextSuprailiac.setText("");
		editTextQuadriceps.setText("");
		textViewResultBodyDensity.setText("");
		textViewResultPercentFat.setText("");
		textViewResultLeanWeight.setText("");
		textViewResultFatWeight.setText("");
		textViewResultPopulationAverage.setText("");
		textViewResultScore.setText("");
		textViewResultRating.setText("");
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
