package com.example.overgd.menu;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.option_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.home:
			return true;
		case R.id.exit:
			finish();
			return true;
		case R.id.digital:
			Toast.makeText(this, "디지털 선택", Toast.LENGTH_SHORT).show();
			Intent intent1 = new Intent(this, DigitalClockActivity.class);
			startActivity(intent1);
			return true;
		case R.id.analog:
			Toast.makeText(this, "아날로그 선택", Toast.LENGTH_SHORT).show();
			Intent intent2 = new Intent(this, AnalogClockActivity.class);
			startActivity(intent2);
			return true;
		case R.id.chrono:
			Toast.makeText(this, "초 선택", Toast.LENGTH_SHORT).show();
			Intent intent3 = new Intent(this, ChronoClockActivity.class);
			startActivity(intent3);
			return true;

		}

		return super.onOptionsItemSelected(item);
	}

}
