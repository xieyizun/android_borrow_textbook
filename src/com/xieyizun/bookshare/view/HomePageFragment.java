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

//���home��ÿһҳpage
public class HomePageFragment extends Fragment {
	//��ǲ�ͬ��ҳ
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
    	//��Ļ������תʱ�������¼���
    	this.setRetainInstance(true);
    }
    
    //������ͼ�������һҳ
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_content, container, false);
        //��ÿһҳ��������ݣ�����ʹ��listView����
        lv = (ListView)rootView.findViewById(R.id.content);
		final ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		//��ȡ���ݲ���䵽ÿ��page
		
		int count = (int)(Math.random()*100) + 10;
		getAndSetData(listItem, count);
		
        return rootView;
    }
    
    //��ȡ���ݲ�����䵽ÿһҳ
    public void getAndSetData(final ArrayList<HashMap<String, Object>> item_list, final int count) {
		//�������д������
		for (int i = 0; i < count; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", R.drawable.ic_launcher);
			map.put("ItemName", "c++" + i + "_" + this.position);
			map.put("ItemAuthor", "̷��ǿ");
			map.put("ItemPublish", "�廪��ѧ������");
			
			item_list.add(map);
		}
		
		//ʹ��SimpleAdapter����̬����listItem�е����ݰ󶨵�lv��
		SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), item_list, 
				R.layout.fragment_home_item, new String[] {"ItemImage","ItemName", "ItemAuthor","ItemPublish"},
				new int[] {R.id.item_image, R.id.item_name, R.id.item_author, R.id.item_publish});
		
		lv.setAdapter(simpleAdapter);
		
        //����ÿ��ĵ���¼�
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
