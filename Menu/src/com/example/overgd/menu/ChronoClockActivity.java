package com.example.overgd.menu;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.ContextMenu.*;
import android.widget.*;

public class ChronoClockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chronometer);
		Chronometer timer = (Chronometer) findViewById(R.id.chronometer1);
		registerForContextMenu(timer); // 초시계에 컨텍스트메뉴를 설정
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		Chronometer ch = (Chronometer) findViewById(R.id.chronometer1);

		switch (item.getItemId()) {

		case R.id.start:
			ch.start();
			break;
		case R.id.stop:
			ch.stop();
			break;
		case R.id.reset:
			ch.setBase(SystemClock.elapsedRealtime());
			break;

		}

		return super.onContextItemSelected(item);
	}

}
