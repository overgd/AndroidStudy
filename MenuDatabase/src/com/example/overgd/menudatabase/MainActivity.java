package com.example.overgd.menudatabase;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends ListActivity {

	Button insert, delete, update, select, clear;
	EditText id, name, phone;
	static final String DATABASE_NAME = "Database.db";
	static final String TABLE = "Friends";
	static final String CREATE_TABLE = "create table " + TABLE
			+ " (_id integer primary key autoincrement, name text not null, phone text not null);";
	static final String INSERT = "insert into " + TABLE + " (name, phone) values ";
	static final String DELETE = "delete from " + TABLE;
	static final String UPDATE = "update " + TABLE;
	static SQLiteDatabase DB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		id = (EditText) findViewById(R.id.edt_num);
		name = (EditText) findViewById(R.id.edt_name);
		phone = (EditText) findViewById(R.id.edt_phone);

		openDataBase();
		creatTable();
	}

	private void openDataBase() {

		DB = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null); // 2번째
																				// 파라미터
																				// :
																				// 권한
		if (DB != null) {
			Toast.makeText(this, "DB가 open되었습니다.", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "DB가 open되지 않았습니다.", Toast.LENGTH_SHORT).show();
		}

	}

	private void creatTable() {

		try {
			DB.execSQL(CREATE_TABLE); // 테이블 생성 쿼리 실행
		} catch (Exception e) {
		}

	}

	private void selectConditional() {

		String sql = "select * from " + TABLE + " where 1=1 ";
		String txt_id = id.getText().toString();
		String txt_name = name.getText().toString();
		String txt_phone = phone.getText().toString();

		if (txt_id.length() > 0) { // 아이디가 입력된 경우
			sql = sql + " and _id = " + txt_id;
		}
		if (txt_name.length() > 0) { // 이름이 입력된 경우
			sql = sql + " and name = '" + txt_name + "'";
		}
		if (txt_phone.length() > 0) {// 번호가 입력된 경우
			sql = sql + " and phone = '" + txt_phone + "'";
		}
		sql = sql + ";";
		Cursor c = DB.rawQuery(sql, null);
		startManagingCursor(c);
		String[] from = new String[] { "_id", "name", "phone" };

		int[] to = new int[] { R.id._id, R.id.name, R.id.phone };
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_row, c, from, to);
		setListAdapter(adapter);

	}

	private void insertData() {

		String sql = INSERT;
		String txt_name = name.getText().toString();
		String txt_phone = phone.getText().toString();

		sql = sql + "('" + txt_name + "', '" + txt_phone + "');";

		try {
			DB.execSQL(sql);
			Toast.makeText(this, "삽입되었습니다.", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, "삽입 도중 실패했습니다.", Toast.LENGTH_SHORT).show();
		}

	}

	private void updateData() {

		String sql = UPDATE;
		String txt_name = name.getText().toString();
		String txt_phone = phone.getText().toString();
		boolean flag = false;

		if (!txt_name.equals("")) {
			if (flag == false) { // 처음인지 아닌 지 확인
				sql = sql + " set name='" + txt_name + "' ,";
				flag = true;
			} else {
				sql = sql + " name = '" + txt_name + "' ,";
			}
		}

		if (!txt_phone.equals("")) {
			if (flag == false) {
				sql = sql + " set phone='" + txt_phone + "' ,";
				flag = true;
			} else {
				sql = sql + " phone = '" + txt_phone + "' ,";
			}
		}

		sql = sql.substring(0, sql.length() - 1);
		sql = sql + " where _id = " + id.getText().toString() + ";";

		try {
			DB.execSQL(sql);
			Toast.makeText(this, "변경되었습니다.", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, "변경 도중 예외가 발생했습니다.", Toast.LENGTH_SHORT).show();
		}

	}

	private void deleteData() {

		String sql = DELETE + " where 1 = 1 ";
		String txt_id = id.getText().toString();
		String txt_name = name.getText().toString();
		String txt_phone = phone.getText().toString();

		if (!txt_id.equals("")) {
			sql = sql + " and _id=" + txt_id;
		}
		if (!txt_name.equals("")) {
			sql = sql + " and name='" + txt_name + "' ";
		}
		if (!txt_phone.equals("")) {
			sql = sql + " and phone='" + txt_phone + "' ";
		}

		sql = sql + ";";

		try {
			DB.execSQL(sql);
			Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, "삭제 도중 오류가 생겼습니다.", Toast.LENGTH_SHORT).show();
		}

	}

	private void selectAll() {

		String sql = "select * from " + TABLE + ";";
		Cursor c = DB.rawQuery(sql, null); // select 사용할 시에 rawQuery사용
		// Cursor는 데이터베이스 검색결과를 담는 객체다
		startManagingCursor(c); // startManagingCursor는 DB를 닫는 작업을 자동으로 하도록
		String[] from = new String[] { "_id", "name", "phone" }; // 컬럼이름
		int[] to = new int[] { R.id._id, R.id.name, R.id.phone }; // 위젯이름
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_row, c, from, to);
		// import widget
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.option_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.inesert:
			
			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setMessage("정말로 삽입하시겠습니까?").setCancelable(false)
					.setPositiveButton("예", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							insertData();
							selectAll();
						}

					}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}

					});
			// 취소버튼은 안만듦
			AlertDialog dialog1 = builder1.create();
			dialog1.show();
			break;

		case R.id.delete:
			
			AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
			builder2.setMessage("정말로 삭제하겠습니까?").setCancelable(false)
					.setPositiveButton("예", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							deleteData();
							selectAll();

						}
					}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							dialog.cancel();
						}
					});
			AlertDialog dialog2 = builder2.create();
			dialog2.show();
			break;
			
		case R.id.update:
			
			AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
			builder3.setMessage("정말로 변경하시겠습니까?").setCancelable(false)
					.setPositiveButton("예", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							updateData();
							selectAll();

						}
					}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							dialog.cancel();
						}
					});
			AlertDialog dialog3 = builder3.create();
			dialog3.show();
			break;
			
		case R.id.select:
			
			AlertDialog.Builder builder4 = new AlertDialog.Builder(this);
			builder4.setMessage("정말로 조회하시겠습니까?").setCancelable(false)
					.setPositiveButton("예", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							selectConditional();

						}
					}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							dialog.cancel();
						}
					});
			AlertDialog dialog4 = builder4.create();
			dialog4.show();
			break;
			
		case R.id.clear:
			
			AlertDialog.Builder builder5 = new AlertDialog.Builder(this);
			builder5.setMessage("정말로 지우시겠습니까?").setCancelable(false)
					.setPositiveButton("예", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							MainActivity.this.id.setText("");
							name.setText("");
							phone.setText("");

						}
					}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

							dialog.cancel();
						}
					});
			AlertDialog dialog5 = builder5.create();
			dialog5.show();
			break;
			
		}

		return super.onOptionsItemSelected(item);
	}

}
