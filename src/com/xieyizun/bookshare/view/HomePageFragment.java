package com.xieyizun.bookshare.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.xieyizun.bookshare.R;
import com.xieyizun.bookshare.action.BookDetailActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

//填充home的每一页page
public class HomePageFragment extends Fragment {
	//标记不同的页
	private int position;
	
	private ListView lv;
	
	public static HomePageFragment newInstance(int position) {
		HomePageFragment f = new HomePageFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		f.setArguments(args);
		return f;
	}
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	this.position = this.getArguments().getInt("position");
    	//屏幕发送旋转时，不重新加载
    	this.setRetainInstance(true);
    }
    
    //创建视图用于填充一页
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_content, container, false);
        //往每一页中填充数据，数据使用listView保存
        lv = (ListView)rootView.findViewById(R.id.content);
		final ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		//获取数据并填充到每个page
		
		int count = (int)(Math.random()*100) + 10;
		getAndSetData(listItem, count);
		
        return rootView;
    }
    
    //获取数据并且填充到每一页
    public void getAndSetData(final ArrayList<HashMap<String, Object>> item_list, final int count) {
		//在数组中存放数据
		for (int i = 0; i < count; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.ic_launcher);
			map.put("ItemName", "c++" + i + "_" + this.position);
			map.put("ItemAuthor", "谭浩强");
			map.put("ItemPublish", "清华大学出版社");
			
			item_list.add(map);
		}
		
		//使用SimpleAdapter将动态数组listItem中的数据绑定到lv中
		SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), item_list, 
				R.layout.fragment_home_item, new String[] {"ItemImage","ItemName", "ItemAuthor","ItemPublish"},
				new int[] {R.id.item_image, R.id.item_name, R.id.item_author, R.id.item_publish});
		
		lv.setAdapter(simpleAdapter);
		
        //设置每项的点击事件
        lv.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("ShowToast") @Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {	
				Intent intent = new Intent(getActivity(), BookDetailActivity.class);
				
				intent.putExtra("item_image", (Integer)item_list.get(position).get("ItemImage"));
				intent.putExtra("item_name", (String)item_list.get(position).get("ItemName"));
				intent.putExtra("item_author", (String)item_list.get(position).get("ItemAuthor"));
				intent.putExtra("item_publish", (String)item_list.get(position).get("ItemPublish"));
				
				startActivity(intent);
			}
        	
        });
    }
}
