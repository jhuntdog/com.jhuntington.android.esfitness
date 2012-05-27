package com.jhuntington.android.esfit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.R.id;
import com.jhuntington.android.esfit.R.layout;

public class DashboardClientMeasureFragment extends SherlockFragment {
	
	private View mClientDashboardView = null;
	
	public DashboardClientMeasureFragment() {
		super();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mClientDashboardView = (View) inflater.inflate(R.layout.fragment_dashboard_clientmeasure, container, false);
		
		mClientDashboardView.findViewById(R.id.home_btn_clientmeasure).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "You want to measure a client", Toast.LENGTH_SHORT).show();
				//startActivity(new Intent(getSherlockActivity(), ClientActivity.class));
				
			}
		});
		
		
		return mClientDashboardView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	

}
