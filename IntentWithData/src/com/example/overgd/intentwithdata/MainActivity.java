package com.example.overgd.intentwithdata;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {

	private static final int FIRST_CHILD = 1; //requestCode는 헷갈리기 때문에 상수선언해서 넣어준다.
	private static final int SECOND_CHILD = 2;
	
	Button btn1;
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn1 = (Button)findViewById(R.id.btn_parent);
		edit = (EditText)findViewById(R.id.edt_parent);
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, SecondActivity.class);
				intent.putExtra("KEY1", edit.getText().toString());
				startActivityForResult(intent, FIRST_CHILD);
			}
		});
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { //자동으로 호출되는 메서드

		if(requestCode == FIRST_CHILD) {
			if(resultCode == RESULT_OK) { //정상적으로 처리되었으면 받는 코드
				String result = data.getExtras().getString("KEY1");
				edit.setText(result);
			}else {
				edit.setText("실패");
			}
		}else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
	
	
}
