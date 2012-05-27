package com.jhuntington.android.esfit.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockFragment;
import com.jhuntington.android.esfit.R;
import com.jhuntington.android.esfit.R.id;
import com.jhuntington.android.esfit.R.layout;
import com.jhuntington.android.esfit.R.string;

public class JillianFragment extends SherlockFragment {
	
	private static final String DEBUG_TAG = "JillianStreamFragment";
	
	private WebView mWebView;
	private View mLoadingSpinner;
	
	public JillianFragment() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.webview_stream, container, false);
		
		mLoadingSpinner = root.findViewById(R.id.loading_spinner);
		mWebView = (WebView) root.findViewById(R.id.webview);
		
		mWebView.setWebViewClient(mWebViewClient);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
		
		String webStream = getString(R.string.jillian_stream_url);
		mWebView.loadUrl(webStream);
		
		
		return root;
		
	}
	
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	public void refresh() {
		mWebView.reload();
	}
	
	private WebViewClient mWebViewClient = new WebViewClient() {
		
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageFinished(view, url);
			mLoadingSpinner.setVisibility(View.GONE);
			mWebView.setVisibility(View.VISIBLE);
		}
		
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.startsWith("javascript")) {
				return false;
			}

			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}
		
	};
	

}
