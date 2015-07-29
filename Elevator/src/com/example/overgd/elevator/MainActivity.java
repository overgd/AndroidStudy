package com.example.overgd.elevator;

import java.util.*;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ElevatorView(this));
	}
}

class ElevatorView extends View {

	public int floor_1 = 0;
	public int floor_2 = 0;
	public boolean flag = false;

	public ElevatorView(Context context) {
		super(context);
		Random rand = new Random();

		FirstElevator fe = new FirstElevator(this, rand.nextInt(1000));
		fe.start();// 첫번째 엘레베이터 스레드 생성 및 실행

		Runnable se = new SecondElevator(this, rand.nextInt(1000));
		Thread set = new Thread(se);
		set.start();// 두번째 엘레베이터 스레드 생성 및 실행
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.elebator), 50, 350 - (floor_1 * 50),
				null);
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.elebator), 200, 350 - (floor_2 * 50),
				null);

		if (flag == true) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setTextSize(20);
			paint.setColor(Color.RED);
			canvas.drawText("엘레베이터가 중지되었습니다.", 100, 50, paint);
		}
	}

}