package com.example.overgd.listview;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class MainActivity extends ListActivity { //ListActivity를 상속하여 만들 경우 XML에 List의 ID를 @android:id/list로 지정해줘야 한다.
	
	private String[] items = {"강남", "서초", "동작", "신도림", "성북", "반포", "영등포", "종로", "서대문", "구로"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items));
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, items);
//		//복수개의 데이터는 Adapter를 통해서 넣을 수 있다.
//		//android.R은 안드로이드 자체에 내장되어 있는 리소스이다.
//		ListView lv = (ListView)findViewById(R.id.list);
//		lv.setAdapter(adapter); //리스트와 어댑터 연결
//		lv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				//특정 항목 선택시 나온다.
//				Toast.makeText(MainActivity.this, "선택한 항목 : "+, Toast.LENGTH_LONG).show();
//			}
//		});       //Activity를 상속하여 만들 경우
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "선택한 항목 : "+items[position], Toast.LENGTH_SHORT).show();
	}
}
