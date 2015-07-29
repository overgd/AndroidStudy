package com.example.overgd.lotto;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {
	
	final int[] IMG_LOTTOS = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5, 
			R.drawable.icon_6, R.drawable.icon_7, R.drawable.icon_8, R.drawable.icon_9, R.drawable.icon_10, 
			R.drawable.icon_11, R.drawable.icon_12, R.drawable.icon_13, R.drawable.icon_14, R.drawable.icon_15, 
			R.drawable.icon_16, R.drawable.icon_17, R.drawable.icon_18, R.drawable.icon_19, R.drawable.icon_20, 
			R.drawable.icon_21, R.drawable.icon_22, R.drawable.icon_23, R.drawable.icon_24, R.drawable.icon_25,
			R.drawable.icon_26, R.drawable.icon_27, R.drawable.icon_28, R.drawable.icon_29, R.drawable.icon_30, 
			R.drawable.icon_31, R.drawable.icon_32, R.drawable.icon_33, R.drawable.icon_34, R.drawable.icon_35, 
			R.drawable.icon_36, R.drawable.icon_37, R.drawable.icon_38, R.drawable.icon_39, R.drawable.icon_40, 
			R.drawable.icon_41, R.drawable.icon_42, R.drawable.icon_43, R.drawable.icon_44, R.drawable.icon_45 };
	
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				TreeSet<Integer> ts = new TreeSet<Integer>();
				while(ts.size() < 6) { //서로 다른 난수 6개 생성(순서대로)
					int num = (int)(Math.random() * 45) + 1;
					ts.add(num);
				}
				Iterator it = ts.iterator();

				int n1 = (Integer) it.next()-1; //배열은 인덱스가 0부터
				ImageView l1 = (ImageView)findViewById(R.id.Lotto1);
				l1.setImageResource(IMG_LOTTOS[n1]);
				int n2 = (Integer) it.next()-1; //배열은 인덱스가 0부터
				ImageView l2 = (ImageView)findViewById(R.id.Lotto2);
				l2.setImageResource(IMG_LOTTOS[n2]);
				int n3 = (Integer) it.next()-1; //배열은 인덱스가 0부터
				ImageView l3 = (ImageView)findViewById(R.id.Lotto3);
				l3.setImageResource(IMG_LOTTOS[n3]);
				int n4 = (Integer) it.next()-1; //배열은 인덱스가 0부터
				ImageView l4 = (ImageView)findViewById(R.id.Lotto4);
				l4.setImageResource(IMG_LOTTOS[n4]);
				int n5 = (Integer) it.next()-1; //배열은 인덱스가 0부터
				ImageView l5 = (ImageView)findViewById(R.id.Lotto5);
				l5.setImageResource(IMG_LOTTOS[n5]);
				int n6 = (Integer) it.next()-1; //배열은 인덱스가 0부터
				ImageView l6 = (ImageView)findViewById(R.id.Lotto6);
				l6.setImageResource(IMG_LOTTOS[n6]);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
