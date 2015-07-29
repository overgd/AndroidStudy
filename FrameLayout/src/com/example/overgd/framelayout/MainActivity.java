package com.example.overgd.framelayout;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.Animation.*;
import android.widget.*;

public class MainActivity extends Activity {

	boolean isPageOpen = false;
	Animation toLeft, toRight;
	LinearLayout layout;
	Button btn;

	class AnimListener implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {

			if (isPageOpen) {
				isPageOpen = false;
				layout.setVisibility(View.INVISIBLE);
				btn.setText("보이기");
			} else {
				isPageOpen = true;
				btn.setText("감추기");
			}

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn = (Button) findViewById(R.id.button3);
		layout = (LinearLayout) findViewById(R.id.m_layout);
		toLeft = AnimationUtils.loadAnimation(this, R.anim.move_to_left);
		toRight = AnimationUtils.loadAnimation(this, R.anim.move_to_right);
		
		AnimListener listener = new AnimListener();
		toLeft.setAnimationListener(listener);
		toRight.setAnimationListener(listener);
		
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (isPageOpen) {
					layout.startAnimation(toRight);
				} else {
					layout.setVisibility(View.VISIBLE);
					layout.startAnimation(toLeft);
				}
			}
		});

	}

}
