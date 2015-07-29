package com.example.overgd.calendardemo1;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.*;
import android.widget.DatePicker.*;
import android.widget.TimePicker.*;

public class MainActivity extends Activity {

	TimePicker tp;
	DatePicker dp;
	CheckBox ck;
	TextView result, time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tp = (TimePicker) findViewById(R.id.timePicker1);
		dp = (DatePicker) findViewById(R.id.datePicker1);
		ck = (CheckBox) findViewById(R.id.checkBox1);
		result = (TextView) findViewById(R.id.textView1);
		time = (TextView) findViewById(R.id.textView2);

		ck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				tp.setEnabled(isChecked);
				
				if(ck.isChecked()) {
					tp.setVisibility(View.VISIBLE);
				}else {
					tp.setVisibility(View.INVISIBLE);
				}
				
				
			}
		});
		
		tp.setOnTimeChangedListener(new OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

				String msg = "";
				msg = msg + hourOfDay + "시, " + minute + "분";
				time.setText(msg);
				tp.setCurrentHour(hourOfDay);
				tp.setCurrentMinute(minute);

			}
		});

		dp.init(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), new OnDateChangedListener() {

			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

				String msg = "";
				msg = msg + year + "년, " + monthOfYear + "월, " + dayOfMonth + "일";
				result.setText(msg);
				
			}
		});
	}
}
