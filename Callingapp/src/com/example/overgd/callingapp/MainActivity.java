package com.example.overgd.callingapp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent i = new Intent("com.overgd.called");
				i.putExtra("MY_KEY", "송신 데이터입니다.");
				startActivityForResult(i, 1);
				
			}
		});
	}
}
