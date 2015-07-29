package com.example.overgd.myintent;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);
		
		Button btn_exit = (Button)findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();				
				
			}
		});
	}

}
