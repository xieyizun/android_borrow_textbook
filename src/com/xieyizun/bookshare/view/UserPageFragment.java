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

//�������ÿһPage
public class UserPageFragment extends Fragment {
	//postion��¼��viewpager�еĵڼ�ҳ
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
		//�����listview���������ݵ�����,����ҳ��Ĳ�ͬ��䲻ͬ������,ÿ��itemע�᲻ͬ�ļ����¼�
		//�ֱ�Ϊ�ҵĽ̲ģ����ļ�¼���ҵĺ��ѣ��ҵ�˽��
		ArrayList<HashMap<String, Object>> item_list = new ArrayList<HashMap<String, Object>>();
		
		int count = (int)(Math.random()*100) + 10;
		getAndSetData(item_list, count);
		
		return rootView;
	}
	
	//�������ÿpage��listview������
	public void getAndSetData(ArrayList<HashMap<String, Object>> item_list, final int count) {
		for (int i = 0; i < count; i++) {
			HashMap<String, Object> item = new HashMap<String, Object>();
			item.put("image", R.drawable.home);
			item.put("text", "hello world" + this.position + "_" + i);
			
			item_list.add(item);
		}	
		
		//����adapter����������䵽ListView
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), item_list, 
				R.layout.fragment_user_page_item, new String[] {"image", "text"},
				new int[] {R.id.image, R.id.text});
		
		this.lv.setAdapter(adapter);
		
		//����lv��item�ĵ���¼�
		this.lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View item, int position,
					long id) {
				Toast.makeText(getActivity(), "test"+ count, Toast.LENGTH_SHORT).show();
			}			
		});
	}
}