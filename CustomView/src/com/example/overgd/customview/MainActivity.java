package com.example.overgd.customview;

import java.util.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends ListActivity {
	
	ArrayList<MyFriend> addrs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addrs = new ArrayList<MainActivity.MyFriend>();
		addrs.add(new MyFriend(R.drawable.ic_launcher, "가길동", "서울"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "나길동", "대전"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "다길동", "대구"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "라길동", "부산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "마길동", "아산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "나길동", "대전"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "다길동", "대구"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "라길동", "부산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "마길동", "아산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "나길동", "대전"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "다길동", "대구"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "라길동", "부산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "마길동", "아산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "나길동", "대전"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "다길동", "대구"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "라길동", "부산"));
		addrs.add(new MyFriend(R.drawable.ic_launcher, "마길동", "아산"));
	
		setListAdapter(new OwnAdapter(addrs));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Toast.makeText(MainActivity.this, "선택한 이름 : "+addrs.get(position).name, Toast.LENGTH_SHORT).show();
	}
	
	class MyFriend {
		
		int pic;
		String name;
		String addr;
		
		public MyFriend(int pic, String name, String addr) {
			this.pic = pic;
			this.name = name;
			this.addr = addr;
		}
		public int getPic() {
			return pic;
		}
		public void setPic(int pic) {
			this.pic = pic;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		
	}
	
	class OwnAdapter extends BaseAdapter {

		ArrayList<MyFriend> addrs;
		
		
		public OwnAdapter(ArrayList<MyFriend> addrs) {
			super();
			this.addrs = addrs;
		}

		@Override
		public int getCount() {
			return addrs.size();
		}

		@Override
		public Object getItem(int position) { //현재 사용안함
			return null;
		}

		@Override
		public long getItemId(int position) { //현재 사용안함
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder viewHolder = null;
			
			if(convertView == null) { //재사용할 뷰가 없는 경우
				//뷰를 만들어야 한다.
				LayoutInflater inflater = MainActivity.this.getLayoutInflater(); //
				convertView = inflater.inflate(R.layout.my_friend, parent, false);
				viewHolder = new ViewHolder();
				viewHolder.iv = (ImageView)convertView.findViewById(R.id.imageView1);
				viewHolder.tv1 = (TextView)convertView.findViewById(R.id.textView1);
				viewHolder.tv2 = (TextView)convertView.findViewById(R.id.textView2);
				convertView.setTag(viewHolder);
			}else { //재사용할 뷰가 있는 경우
				//없어진 것의 데이터를 바꿔준다.
				viewHolder = (ViewHolder)convertView.getTag();
			}

			int img = addrs.get(position).getPic(); //친구 이미지 불러오기
			String name = addrs.get(position).getName(); //친구 이름 불러오기
			String addr = addrs.get(position).getAddr(); //친구 주소 불러오기
			viewHolder.iv.setImageResource(img);
			viewHolder.tv1.setText(name);
			viewHolder.tv2.setText(addr);
			
			return convertView;
		}
		
	}
	
	static class ViewHolder {
		ImageView iv;
		TextView tv1;
		TextView tv2;
	}
}
