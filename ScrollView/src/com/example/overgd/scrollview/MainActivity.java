package com.example.overgd.scrollview;

import android.app.*;
import android.content.res.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

	ScrollView scrollView;
	ImageView imageView;
	BitmapDrawable bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		scrollView = (ScrollView) findViewById(R.id.scrollView1);
		imageView = (ImageView) findViewById(R.id.imageView1);
		scrollView.setHorizontalScrollBarEnabled(true);
		// 스크롤바 활성화
		Resources res = getResources();
		bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
		// 첫번째 이미지를 불러옴
		int bitmapWidth = bitmap.getIntrinsicWidth();
		int bitmapHeight = bitmap.getIntrinsicHeight();
		imageView.setImageDrawable(bitmap); // 첫번째 이미지 출력
		imageView.getLayoutParams().width = bitmapWidth;
		imageView.getLayoutParams().height = bitmapHeight;
		// 이미지 뷰의 가로, 세로 길이를 이미지의 가로, 세로 길이로 설정
	}

	public void myButtonClick(View v) {

		changeImage();

	}

	public void changeImage() {
		
		Resources res = getResources();
		bitmap = (BitmapDrawable)res.getDrawable(R.drawable.image02);
		//두번째 이미지 불러옴
		int bitmapWidth = bitmap.getIntrinsicWidth();
		int bitmapHeight = bitmap.getIntrinsicHeight();
		imageView.setImageDrawable(bitmap); // 두번째 이미지 출력
		imageView.getLayoutParams().width = bitmapWidth;
		imageView.getLayoutParams().height = bitmapHeight;
		
	}
}
