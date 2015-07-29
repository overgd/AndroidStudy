package com.example.overgd.sensor;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.hardware.*;
import android.os.*;
import android.view.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SensorMovementView(this));
	}
}

class SensorMovementView extends View implements SensorEventListener {

	int m_x = 0; // 안드로이드 인형의 좌표
	int m_y = 0;

	SensorManager sensor;
	String display;

	public SensorMovementView(Context context) {
		super(context);
		sensor = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensor.registerListener(this, sensor.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
		// SensorManager.SENSOR_DALAY_FASTEST : 가장 빠른 센서감지 속도
		// SensorManager.SENSOR_DALAY_GAME : 게임에 적합한 센서감지 속도
		// SensorManager.SENSOR_DALAY_NORMAL : 기본적인 센서감지 속도
		// SensorManager.SENSOR_DALAY_UI : UI처리에 적합한 센서감지 속도
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ORIENTATION:
				float heading = event.values[0]; // 해당 좌표 읽음
				float pitch = event.values[1]; // 피치 좌표 읽음
				float roll = event.values[2]; // 롤 좌표 읽음
				display = "좌표 : ";
				display = display + "heading : " + Float.toString(heading);
				display = display + ", pitch : " + Float.toString(pitch);
				display = display + ", roll : " + Float.toString(roll);
				m_x = m_x - (int) roll;
				m_y = m_y - (int) pitch;
				if (m_x < 0) {
					m_x = 0;
				}
				if (m_y < 0) {
					m_y = 0;
				}
				if (m_x > (getWidth() - 50)) {
					m_x = getWidth() - 50;
				}
				if (m_y > (getHeight() - 50)) {
					m_y = getHeight() - 50;
				}
				break;
			}
		}
		invalidate(); // onDraw메서드를 호출해서 화면 갱신
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher), m_x, m_y, null);

		Paint p = new Paint();
		p.setTextSize(20);
		p.setColor(Color.GREEN);
		canvas.drawText(display, 0, 20, p);

	}

}
