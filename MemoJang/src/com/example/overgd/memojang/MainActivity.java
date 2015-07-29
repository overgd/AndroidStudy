package com.example.overgd.memojang;

import java.io.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {
	
	private static final String FILENAME = "memo.txt";
	private EditText content;
	private CheckBox check;
	private Button save, load, exit;
	
	private String loadExternal() { //SD카드에서 불러오기
		
		String state = Environment.getExternalStorageState();
		StringBuffer content = new StringBuffer();
		FileInputStream fis = null;
		String value = null;
		
		try {
			
			if(state.equals(Environment.MEDIA_MOUNTED)) {
				
				File dir = Environment.getExternalStorageDirectory();
				File file = new File(dir, FILENAME);
				fis = new FileInputStream(file);
				Reader reader = new InputStreamReader(fis, "UTF-8");
				
				int size = fis.available();
				char[] buffer = new char[size];
				reader.read(buffer);
				reader.close();
				value = new String(buffer);
				
			}else {
				Toast.makeText(this, "SD카드가 없습니다.", Toast.LENGTH_SHORT).show();
			}
			
		}catch(Exception e) {
			Toast.makeText(this, "SD카드에서 읽는 도중 예외가 발생했습니다.", Toast.LENGTH_SHORT).show();
		}
		return value;
	}
	
	private String loadInternal() { //내장메모리에서 불러오기
	
		FileInputStream fis = null;
		String value = null;
		try {
			fis = openFileInput(FILENAME);
			Reader reader = new InputStreamReader(fis, "UTF-8");
			int size = fis.available();
			char[] buffer = new char[size];
			reader.read(buffer);
			reader.close();
			value = new String(buffer);
			Toast.makeText(this, "내장 메모리에서 불러왔습니다.", Toast.LENGTH_SHORT).show();
		}catch(Exception e) {
			Toast.makeText(this, "내장 메모리에서 읽는 도중 예외가 발생했습니다.", Toast.LENGTH_SHORT).show();
		}
		return value;
	}
	
	private void saveExternal(String str) {
		
		String state = Environment.getExternalStorageState();
		try {
			
			if(state.equals(Environment.MEDIA_MOUNTED)) { //SD가 있는 경우
				
				File dir = Environment.getExternalStorageDirectory();
				File file = new File(dir, FILENAME);
				FileOutputStream fos = new FileOutputStream(file);
				Writer writer = new OutputStreamWriter(fos, "UTF-8");
				writer.write(str);
				writer.close();
				
			}else { //SD가 없는 경우
				Toast.makeText(this, "SD카드가 연결되지 않았습니다.", Toast.LENGTH_SHORT).show();
			}
			
		}catch(Exception e) {
			Toast.makeText(this, "SD카드에 저장에 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void saveInternal(String str) {
		
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(FILENAME, MODE_PRIVATE);
			Writer writer = new OutputStreamWriter(fos, "UTF-8");
			writer.write(str);
			writer.close();
			Toast.makeText(this, "내부 저장소에 저장했습니다.", Toast.LENGTH_SHORT).show();
		}catch(Exception e) {
			Toast.makeText(this, "내부 저장소에 저장하는 도중 문제가 발생했습니다.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		check = (CheckBox)findViewById(R.id.checkBox1);
		content = (EditText)findViewById(R.id.editText1);
		save = (Button)findViewById(R.id.button1);
		load = (Button)findViewById(R.id.button2);
		exit = (Button)findViewById(R.id.button3);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(check.isChecked()) { //SD카드에 저장
					saveExternal(content.getText().toString());
				}else { //내장에 저장
					saveInternal(content.getText().toString());
					//내장에 저장하는 메서드 호출
				}
				
			}
		});
		
		load.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if(check.isChecked()) { //SD카드에서 불러옴
					content.setText(loadExternal());
				}else { //내장에서 불러옴
					content.setText(loadInternal());
				}
				
			}
		});
	
		exit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
			
			}
		});
	}
}
