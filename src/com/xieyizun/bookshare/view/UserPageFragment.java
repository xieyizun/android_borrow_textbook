package com.xieyizun.bookshare.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.xieyizun.bookshare.R;

//用于填充每一Page
public class UserPageFragment extends Fragment {
	//postion记录在viewpager中的第几页
	private int position;	
	private ListView lv;
	
	public static UserPageFragment newInstance(int position) {
		UserPageFragment f = new UserPageFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		f.setArguments(args);
		return f;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.position = this.getArguments().getInt("position");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundles) {
		View rootView = (View)inflater.inflate(R.layout.fragment_user_page, container, false);
		lv = (ListView)rootView.findViewById(R.id.user_content_list);
		//存放往listview中填充的数据的容器,根据页面的不同填充不同的数据,每个item注册不同的监听事件
		//分别为我的教材，借阅记录，我的好友，我的私信
		ArrayList<HashMap<String, Object>> item_list = new ArrayList<HashMap<String, Object>>();
		
		int count = (int)(Math.random()*100) + 10;
		getAndSetData(item_list, count);
		
		return rootView;
	}
	
	//用于填充每page的listview的数据
	public void getAndSetData(ArrayList<HashMap<String, Object>> item_list, final int count) {
		for (int i = 0; i < count; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("image", R.drawable.home);
			item.put("text", "hello world" + this.position + "_" + i);
			
			item_list.add(item);
		}	
		
		//创建adapter来讲数据填充到ListView
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), item_list, 
				R.layout.fragment_user_page_item, new String[] {"image", "text"},
				new int[] {R.id.image, R.id.text});
		
		this.lv.setAdapter(adapter);
		
		//设置lv的item的点击事件
		this.lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View item, int position,
					long id) {
				Toast.makeText(getActivity(), "test"+ count, Toast.LENGTH_SHORT).show();
			}			
		});
	}
}