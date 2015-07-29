package com.example.overgd.calendardemo2;

import java.util.*;

import android.app.*;
import android.app.DatePickerDialog.*;
import android.app.TimePickerDialog.*;
import android.os.*;
import android.text.format.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MainActivity extends Activity {

	TextView mText;
	Button btnDate, btnTime;
	int year, month, day, hour, minute;
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {

		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, dateListener, year, month, day);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, timeSetListener, hour, minute, true);
		}

		return super.onCreateDialog(id);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mText = (TextView) findViewById(R.id.textView1);
		btnDate = (Button) findViewById(R.id.button1);
		btnTime = (Button) findViewById(R.id.button2);
		////////////////////////////////////////////////////////////////////////////

		btnDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		///////////////////////////////////////////////////////////////////////////

		OnClickListener btnTimeOnClick = new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		};

		btnTime.setOnClickListener(btnTimeOnClick);

		///////////////////////////////////////////////////////////////////////////

		final Calendar c = Calendar.getInstance(); // 현재 날짜와 시간 획득
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		updateInfo();

	}

	private DatePickerDialog.OnDateSetListener dateListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

			MainActivity.this.year = year;
			MainActivity.this.month = monthOfYear;
			MainActivity.this.day = dayOfMonth;
			updateInfo();

		}
	};

	private TimePickerDialog.OnTimeSetListener timeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			MainActivity.this.hour = hourOfDay;
			MainActivity.this.minute = minute;
			updateInfo();

		}
	};

	void updateInfo() {

		String str = year + "/" + (month + 1) + "/" + day + ", " + hour + ":" + minute;
		mText.setText(str);

	}
}
