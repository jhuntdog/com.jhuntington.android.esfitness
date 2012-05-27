package com.jhuntington.android.esfitbeta;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DashboardFragment extends Fragment {
	
	private View mDashboardView = null;
	
	public DashboardFragment() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		mDashboardView = (View) inflater.inflate(R.layout.fragment_dashboard, container, false);
		//View root = inflater.inflate(R.layout.fragment_dashboard, container);
		
		
		
		// Attach event handlers
		
		mDashboardView.findViewById(R.id.home_btn_male3site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//startActivity(new Intent(getActivity(), CalcMaleThreeActivity.class));
				startActivity(new Intent(getActivity(), ContentActivity.class));
			}
		});
		
		mDashboardView.findViewById(R.id.home_btn_female3site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getActivity(), "You hit Female 3 Site", Toast.LENGTH_SHORT).show();
				//startActivity(new Intent(getActivity(), CalcFemaleThreeActivity.class));
				startActivity(new Intent(getActivity(), ContentActivity.class));
			}
		});
		
		mDashboardView.findViewById(R.id.home_btn_male7site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getActivity(), "You hit Male 7 Site", Toast.LENGTH_SHORT).show();
				//startActivity(new Intent(getActivity(), CalcMaleSevenActivity.class));
				startActivity(new Intent(getActivity(), ContentActivity.class));
			}
		});
		
		mDashboardView.findViewById(R.id.home_btn_female7site).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Toast.makeText(getActivity(), "You hit Female 7 Site", Toast.LENGTH_SHORT).show();
				//startActivity(new Intent(getActivity(), CalcFemaleSevenActivity.class));
				startActivity(new Intent(getActivity(), ContentActivity.class));
			}
		});
		
		
		return mDashboardView;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
//		ActionBar bar = getActivity().getActionBar();
//		bar.setDisplayHomeAsUpEnabled(false);
		
		setHasOptionsMenu(true);
		
	}
	

}
