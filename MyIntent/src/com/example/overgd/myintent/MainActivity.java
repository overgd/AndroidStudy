package com.example.overgd.myintent;

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
	
		new CountDownTimer(30000, 1000) {
			
			TextView txt = (TextView)findViewById(R.id.textView1);
			
			@Override
			public void onTick(long millisUntilFinished) {
				
				txt.setText(millisUntilFinished+" 타이머");
				
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
			}
			
		}.start();
		
		Button btn_change = (Button)findViewById(R.id.btn_change);
		btn_change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				//new Intetne(this <-이 부분이 안되는 이유는 익명클래스를 뜻할 수 있기 때문에
				
				startActivity(intent);
			}
		});
	}
}
