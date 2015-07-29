package com.example.overgd.intentwithdata;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class SecondActivity extends Activity {

	Button btn1, btn2;
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		String data = getIntent().getStringExtra("KEY1");
		
		btn1 = (Button)findViewById(R.id.btn_child);
		btn2 = (Button)findViewById(R.id.btn_exit);
		edit = (EditText)findViewById(R.id.edt_child);
		
		if(data != null) {
			edit.setText(data);
		}
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String send = edit.getText().toString();
				if(send.length() != 0) { //데이터가 존재하는 경우
					Intent intent = new Intent();
					intent.putExtra("KEY1", send);
					setResult(RESULT_OK, intent);
				}else {
					setResult(RESULT_CANCELED);
				}
				finish();
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				finish();
				
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
}
