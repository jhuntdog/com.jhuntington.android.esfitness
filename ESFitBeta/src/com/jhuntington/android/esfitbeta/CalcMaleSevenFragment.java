package  com.jhuntington.android.esfitbeta;

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



public class CalcMaleSevenFragment extends Fragment {
	
	public static final String DEBUG_TAG = "CalcMaleSevenFragment";
	
	private boolean mSoloFragment = false;
	private View mCalcView = null;
	
	private EditText editTextAge;
	private EditText editTextWeight;

	private EditText editTextTriceps;
	private EditText editTextPectoral;
	private EditText editTextMidaxilla;
	private EditText editTextSubscapula;
	private EditText editTextAbdomen;
	private EditText editTextSuprailiac;
	private EditText editTextQuadriceps;
	
	private Button btnCalc;
	private Button btnReset;
	
	private TextView textViewResultBodyDensity;
	private TextView textViewResultPercentFat;
	
	private ShareActionProvider mShareActionProvider;
	
	public CalcMaleSevenFragment() {
		super();
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
    		Bundle savedInstanceState) {
		
		mCalcView = (View) inflater.inflate(R.layout.calc_male_seven_site, container, false);
		
		editTextAge = (EditText) mCalcView.findViewById(R.id.editTextAge);
        editTextAge.requestFocus();
        editTextWeight = (EditText) mCalcView.findViewById(R.id.editTextWeight);
        
        editTextTriceps = (EditText) mCalcView.findViewById(R.id.editTextTriceps);
        editTextPectoral = (EditText) mCalcView.findViewById(R.id.editTextPectoral);
        editTextMidaxilla = (EditText) mCalcView.findViewById(R.id.editTextMidaxilla);
        editTextSubscapula = (EditText) mCalcView.findViewById(R.id.editTextSubscapula);
        editTextAbdomen = (EditText) mCalcView.findViewById(R.id.editTextAbdomen);
        editTextSuprailiac = (EditText) mCalcView.findViewById(R.id.editTextSuprailiac);
        editTextQuadriceps = (EditText) mCalcView.findViewById(R.id.editTextQuadriceps);
        
        btnCalc = (Button) mCalcView.findViewById(R.id.btnCalc);
		btnCalc.setEnabled(false);
		btnReset = (Button) mCalcView.findViewById(R.id.btnReset);
        
		textViewResultBodyDensity = (TextView) mCalcView.findViewById(R.id.textViewResultBodyDensity);
		textViewResultPercentFat = (TextView) mCalcView.findViewById(R.id.textViewResultPercentFat);
		
		/* KeyListeners */
		editTextAge.setOnKeyListener(mKeyListener);
		editTextWeight.setOnKeyListener(mKeyListener);

		editTextTriceps.setOnKeyListener(mKeyListener);
		editTextPectoral.setOnKeyListener(mKeyListener);
		editTextMidaxilla.setOnKeyListener(mKeyListener);
		editTextSubscapula.setOnKeyListener(mKeyListener);
		editTextAbdomen.setOnKeyListener(mKeyListener);
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
			case R.id.editTextTriceps:
			case R.id.editTextPectoral:
			case R.id.editTextMidaxilla:
			case R.id.editTextSubscapula:
			case R.id.editTextAbdomen:
			case R.id.editTextSuprailiac:
			case R.id.editTextQuadriceps:
				btnCalc.setEnabled(editTextAge.getText().length() > 0
						&& editTextWeight.getText().length() > 0
						&& editTextTriceps.getText().length() > 0
						&& editTextPectoral.getText().length() > 0
						&& editTextMidaxilla.getText().length() > 0
						&& editTextSubscapula.getText().length() > 0
						&& editTextAbdomen.getText().length() > 0
						&& editTextSuprailiac.getText().length() > 0
						&& editTextQuadriceps.getText().length() > 0);
				break;
			}
			return false;
		}

	};
	


	
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
		double nPectoral = Double.parseDouble(editTextPectoral.getText().toString());
		double nMidaxilla = Double.parseDouble(editTextMidaxilla.getText().toString());
		double nSubscapula = Double.parseDouble(editTextSubscapula.getText().toString());
		double nAbdomen = Double.parseDouble(editTextAbdomen.getText().toString());
		double nSuprailiac = Double.parseDouble(editTextSuprailiac.getText().toString());
		double nQuadriceps = Double.parseDouble(editTextQuadriceps.getText().toString());
		
		double sumSevenMale = (nTriceps + nPectoral + nMidaxilla + nSubscapula + nAbdomen + nSuprailiac + nQuadriceps);
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
		if (nTriceps < 1.0) {
			showErrorAlert("Nice Tri!", editTextTriceps.getId());
			isError = true;
		}
		if (nPectoral < 1.0) {
			showErrorAlert("Enter a valid value for Pecs", editTextPectoral.getId());
			isError = true;
		}
		if (nMidaxilla < 1.0) {
			showErrorAlert("Do you know where midaxilla is", editTextMidaxilla.getId());
			isError = true;
		}
		if (nSubscapula < 1.0) {
			showErrorAlert("Your scapula fat is not sub- anything", editTextSubscapula.getId());
			isError = true;
		}
		if (nAbdomen < 1.0) {
			showErrorAlert("You wish you had no tummy fat", editTextAbdomen.getId());
			isError = true;
		}
		if (nSuprailiac < 1.0) {
			showErrorAlert("Your iliacs aren't that super", editTextSuprailiac.getId());
			isError = true;
		}
		if (nQuadriceps < 1.0) {
			showErrorAlert("Somebody workin' legs too much?", editTextQuadriceps.getId());
			isError = true;
		} 
		
		if (!isError) {
			nBodyDensity = (1.112 - (0.00043499 * sumSevenMale) + (0.00000055 * (sumSevenMale * sumSevenMale)) - (0.00028826 * nAge));
			Double nBodyFat = (495 / nBodyDensity) - 450;
			
			textViewResultBodyDensity.setText(nBodyDensity.toString());
			textViewResultPercentFat.setText(nBodyFat.toString());
			
			String percentFatStr = textViewResultPercentFat.getText().toString();
			
			Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Let me tell you about your body");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "Your Percent Body Fat is " + "\n" + percentFatStr + "." + "\n" + "One spray-tan away from sex-god.");
			
			mShareActionProvider.setShareIntent(shareIntent);
			
		}
		
		
	}
	
	
	
	/* Reset */
	private void reset() {
		editTextAge.setText("");
		editTextWeight.setText("");
		editTextTriceps.setText("");
		editTextPectoral.setText("");
		editTextMidaxilla.setText("");
		editTextSubscapula.setText("");
		editTextAbdomen.setText("");
		editTextSuprailiac.setText("");
		editTextQuadriceps.setText("");
		textViewResultBodyDensity.setText("");
		textViewResultPercentFat.setText("");
		editTextAge.requestFocus();
	}
	
	
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
