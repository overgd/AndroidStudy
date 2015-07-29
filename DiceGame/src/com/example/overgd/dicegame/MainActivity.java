package com.example.overgd.dicegame;

import java.util.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {

	int[] img_RedDices = {R.drawable.dice1_1, R.drawable.dice1_2, R.drawable.dice1_3,
			R.drawable.dice1_4, R.drawable.dice1_5, R.drawable.dice1_6};
	int[] img_BlackDices = {R.drawable.dice2_1, R.drawable.dice2_2, R.drawable.dice2_3,
			R.drawable.dice2_4, R.drawable.dice2_5, R.drawable.dice2_6};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		Button btn = (Button)findViewById(R.id.btn_play);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Random rnd = new Random();

				int num1 = rnd.nextInt(6);
				int num2 = rnd.nextInt(6);
				
				Log.w("숫자 1번", Integer.toString(num1));
				Log.w("숫자 2번", Integer.toString(num2));
				
				ImageView img_dice1 = (ImageView)findViewById(R.id.img_dice1);
				ImageView img_dice2 = (ImageView)findViewById(R.id.img_dice2);
				TextView result = (TextView)findViewById(R.id.txt_result);
				
				img_dice1.setImageResource(img_RedDices[num1]);
				img_dice2.setImageResource(img_BlackDices[num2]);
				result.setText(Integer.toString(num1+num2+2));
				
				
				
			}
		});
	}
}
