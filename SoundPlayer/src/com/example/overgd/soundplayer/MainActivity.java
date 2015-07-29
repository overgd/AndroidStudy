package com.example.overgd.soundplayer;

import android.app.*;
import android.media.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {

	MediaPlayer m_sound_background, m_sound_1, m_sound_2;
	SoundPool m_sound_pool;
	Button btn1, btn2, btn3, exit;
	int m_sound_id1, m_sound_id2, m_sound_play_id1, m_sound_play_id2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_sound_background = MediaPlayer.create(this, R.raw.background);
//		m_sound_1 = MediaPlayer.create(this, R.raw.effect1);
//		m_sound_2 = MediaPlayer.create(this, R.raw.effect2);
		m_sound_pool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		// 5 : 동시에 재생되는 최대 오디오 스트림의 갯수
		// AudioManager.STREAM_MUSIC : 재생되는 출력스트림의 종류(음악)
		// 0 : 음질의 수준, 기본값은 0, 현재 사용되지 않음
		m_sound_id1 = m_sound_pool.load(this, R.raw.effect1, 1);
		m_sound_id2 = m_sound_pool.load(this, R.raw.effect2, 2);
		m_sound_background.start(); // 음악 재생

		btn1 = (Button) findViewById(R.id.button1);
		btn2 = (Button) findViewById(R.id.button2);
		btn3 = (Button) findViewById(R.id.button3);
		exit = (Button) findViewById(R.id.button4);

		btn1.setOnClickListener(new OnClickListener() {

			TextView tv = (TextView) findViewById(R.id.result);

			@Override
			public void onClick(View v) {

				if (m_sound_background.isPlaying()) { // 재생 중인 경우
					tv.setText("현재 배경음악이 재생 중입니다.");
					m_sound_background.pause(); // 잠시 재생을 멈춘다.
				} else { // 재생 중이 아닌 경우
					tv.setText("현재 배경음악이 중지상태입니다.");
					m_sound_background.start(); // 다시 재생한다.
				}

			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			
			TextView tv = (TextView) findViewById(R.id.result);
			
			@Override
			public void onClick(View v) {
				m_sound_play_id1 = m_sound_pool.play(m_sound_id1, 1, 1, 0, 0, 1);
				// m_sound_id1 : 재생할 오디오의 번호
				// 1 : 왼쪽 소리 크기 (0.0부터 1.9까지)
				// 1 : 오른쪽 소리 크기
				// 0 : 우선순위 (0 : 가장낮음)
				// 0 : 반복유무 (0 : 반복안함, 1 : 반복함)
				// 1 : 정상속도 (2 : 2배속, 0.5 : 0.5배속)
				tv.setText("효과1를 재생했습니다.");
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {
			
			TextView tv = (TextView) findViewById(R.id.result);
			
			@Override
			public void onClick(View v) {
				
				m_sound_play_id2 = m_sound_pool.play(m_sound_id2, 1, 1, 0, 0, 1);
				tv.setText("효과2를 재생했습니다.");
			}
		});

		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		m_sound_background.stop();
		m_sound_background.release();
		m_sound_pool.stop(m_sound_play_id1);
		m_sound_pool.stop(m_sound_play_id2);
	}
}
