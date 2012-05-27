package com.jhuntington.android.esfit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.R.id;
import com.jhuntington.android.esfit.R.layout;

public class DashboardFragment extends SherlockFragment {
	
	private View mDashboardView = null;
	OnDbItemSelectedListener mListener;
	//private ShareActionProvider mShareActionProvider;
	
	public DashboardFragment() {
		super();
	}
	
	public interface OnDbItemSelectedListener {
		public void onDbItemSelected();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnDbItemSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnDbItemSelectedListener");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mDashboardView = (View) inflater.inflate(R.layout.fragment_dashboard, container, false);
		
		// Attach event handlers
		
		mDashboardView.findViewById(R.id.home_btn_male3site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//startActivity(new Intent(getSherlockActivity(), ContentActivity.class));
				mListener.onDbItemSelected();
			}
		});
		
		mDashboardView.findViewById(R.id.home_btn_female3site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//startActivity(new Intent(getSherlockActivity(), ContentActivity.class));
				mListener.onDbItemSelected();
			}
		});
		
		mDashboardView.findViewById(R.id.home_btn_male7site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//startActivity(new Intent(getSherlockActivity(), ContentActivity.class));
				mListener.onDbItemSelected();
			}
		});
		
		mDashboardView.findViewById(R.id.home_btn_female7site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//startActivity(new Intent(getSherlockActivity(), ContentActivity.class));
				mListener.onDbItemSelected();
			}
		});
		
		return mDashboardView;
	}
		
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		ActionBar bar = getSherlockActivity().getSupportActionBar();
		bar.setDisplayHomeAsUpEnabled(false);
		
		setHasOptionsMenu(true);
		
	}
	
/*	@Override
    public void onPrepareOptionsMenu(Menu menu) {
		MenuItem item = menu.findItem(R.id.menu_share);
		mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	
	}
	
	// Call to update the share intent
	private void setShareIntent(Intent shareIntent) {
	    if (mShareActionProvider != null) {
	    	
	    	shareIntent = new Intent(Intent.ACTION_SEND);
	    	//Intent shareIntent = new Intent(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Thinking 'bout your body");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "Eat more cauliflower!");
			
	    	
	        mShareActionProvider.setShareIntent(shareIntent);
	    }
	}*/
	

}
