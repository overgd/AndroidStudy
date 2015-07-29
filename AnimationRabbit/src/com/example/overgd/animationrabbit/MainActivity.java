package com.example.overgd.animationrabbit;

import android.app.Activity;
import android.content.*;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new AnimationRabbit(this));
	}

	class AnimationRabbit extends View {

		public int MOTION = 0;
		public int MOVE = 0;

		public AnimationRabbit(Context context) {

			super(context);

			Runnable mts = new MotionSpeed(this, 100);
			Runnable ms = new MoveSpeed(this, 10);
			Thread set1 = new Thread(mts);
			Thread set2 = new Thread(ms);
			set1.start();
			set2.start();

		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (MOTION == 0) {
				canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rabbit_1), 50, 50+(MOVE*5), null);
			} else if (MOTION == 1) {
				canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rabbit_2), 50, 50+(MOVE*5), null);
			}
		}
	}

}
