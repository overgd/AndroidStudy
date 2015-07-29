package com.example.overgd.calledapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String data = getIntent().getStringExtra("MY_KEY");
		TextView tv = (TextView) findViewById(R.id.title);
		if (data != null) {
			tv.setText(data);
		}
	}
}
