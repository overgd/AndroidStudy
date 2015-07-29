package com.example.overgd.rabbitanimation;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //제목바 없애기
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //상태바 없애기
		
		View v = (View)new MainActivity.RabbitView(this);
		v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		setContentView(v);
	}

	class RabbitView extends View {

		int width, height;
		int rw, rh;
		int x, y;
		int counter = 0;
		int sx, sy;
		Bitmap[] rabbit = new Bitmap[2];

		public RabbitView(Context context) { // Context라는 객체는 Activity가 동작할 때
												// 필요한 개체
			super(context);

			Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			// 단말기 화면크기를 가져오기

			width = display.getWidth(); // 구버전의 메서드일 경우 -표시가 생김
			height = display.getHeight();
			rabbit[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit_1);
			rabbit[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit_2);

			rw = rabbit[0].getWidth() / 2; // 이동할 경우를 위해 2로 나눈다
			rh = rabbit[1].getWidth() / 2;

			x = 100; // 토끼의 초기 좌표 100, 100
			y = 100;

			sx = 3; // 토끼가 한번에 이동하는 거리
			sy = 3;

			mHandler.sendEmptyMessageDelayed(0, 10); // 0.001초 대기

		}

		Handler mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) { // Handler의 메인
				super.handleMessage(msg);
				invalidate(); // 스레드를 안전한 방법으로 호출하기 위해
				// onDraw를 호출할 때 같은 activity안에 있기 때문에
				// activity와 onDraw호출 대상이 다를 때는 postinvalidate를 써야 한다.
				mHandler.sendEmptyMessageDelayed(0, 10);
			}

		};

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);

			x = x + sx;
			y = y + sy;
			counter++;

			int n = (counter % 20) / 10; // n에는 0아니면 1만 저장

			if ((x < rw)) { // 왼쪽 벽과 충돌한 경우
				x = rw;
				sx = -sx;
			}
			if ((x > width - rw)) { // 오른쪽 벽과 충돌
				x = width - rw;
				sx = -sx;
			}
			if ((y < rh)) { // 천장과 충돌
				y = rh;
				sy = -sy;
			}
			if ((y > height - rh)) { // 바닥과 충돌
				y = height - rh;
				sy = -sy;
			}

			canvas.drawBitmap(rabbit[n], x - rw, y - rh, null);
		}

	}
}
