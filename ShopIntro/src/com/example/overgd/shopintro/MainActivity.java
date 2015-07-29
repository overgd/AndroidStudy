package com.example.overgd.shopintro;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.*;

public class MainActivity extends Activity {

	private WebView myWeb;
	final private String MY_URL = "http://green809.iptime.org:5529/JBeerTest";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myWeb = (WebView) findViewById(R.id.webView1);
		myWeb.getSettings().setJavaScriptEnabled(true); // 자바스크립트 활성화
		myWeb.getSettings().setUseWideViewPort(true); // 뷰포트 활성화
		myWeb.loadUrl(MY_URL);
	}
}
