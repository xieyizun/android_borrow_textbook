package com.xieyizun.bookshare.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xieyizun.bookshare.R;
import com.xieyizun.bookshare.action.CommentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FriendFragment extends Fragment {

	private ListView question_list;
	private Spinner chat_type;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundles) {
		View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
		
		question_list = (ListView)rootView.findViewById(R.id.question_list);
		chat_type = (Spinner)rootView.findViewById(R.id.chat_type);
		
		//需要往question_list中填充的数据
		ArrayList<HashMap<String, Object>> questions = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> question = new HashMap<String, Object>();
			question.put("avaterID", R.drawable.home);
			question.put("userName", "xieyizun");
			question.put("questionContent", "a questiona questiona questiona questiona questiona questiona question" +
					"a questiona questiona questiona questiona questiona questiona questiona question" +
					"a questiona questiona questiona question");
			
			questions.add(question);
		}
		
		//使用适配器将数据填充到UI中
		QuestionAdapter adapter = new QuestionAdapter(getActivity(), questions, R.layout.fragment_friends_item,
				new String[] {"avaterID", "userName", "questionContent"},
				new int[] {R.id.user_avater, R.id.user_name, R.id.question_content}
				);
		
		question_list.setAdapter(adapter);
		
		//为每个问题item添加监听事件
		question_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View item, int position,
					long itemId) {
				//跳转到评论页面
				startActivity(new Intent(getActivity(), CommentActivity.class));
			}
			
		});
		
		//为问题设置分类
		
		List<String> types = new ArrayList<String>();
	
		//根据home中的tabs设置教材的种类数		
		types.add(getString(R.string.title_section1));
		types.add(getString(R.string.title_section2));
		types.add(getString(R.string.title_section3));
		types.add(getString(R.string.title_section4));
		types.add(getString(R.string.title_section5));
		types.add(getString(R.string.title_section6));
		
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_spinner_item, types);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		chat_type.setAdapter(adapter2);	
		
		return rootView;
	}
	
	//用于填充ListView的adapter
	public class QuestionAdapter extends BaseAdapter {
		//问题视图控件
		private class QuestionView {
			ImageView user_avater;
			TextView user_name;
			TextView question_content;
		}
		//问题列表
		private ArrayList<HashMap<String, Object>> questionList;
		//视图容器
		private LayoutInflater inflater;
		
		private Context context;
		private String[] keyStr;
		private int[] valueViewID;
		
		//问题控件
		private QuestionView questionView;
		
		@SuppressWarnings("static-access")
		public QuestionAdapter(Context c, ArrayList<HashMap<String, Object>> qList, int resourse,
				String[] from, int[] to) {
			this.questionList = qList;
			this.context = c;
			this.inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			this.keyStr = new String[from.length];
			this.valueViewID = new int[to.length];
			
			System.arraycopy(from, 0, keyStr, 0, from.length);
			System.arraycopy(to, 0, valueViewID, 0, to.length);
		}
		@Override
		public int getCount() {
			return questionList.size();
		}

		@Override
		public Object getItem(int position) {
			return questionList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup container) {
			if (convertView != null) {
				questionView = (QuestionView)convertView.getTag();
			} else {
				convertView = inflater.inflate(R.layout.fragment_friends_item, null);
				questionView = new QuestionView();
				//问题描述控件
				questionView.user_avater = (ImageView)convertView.findViewById(R.id.user_avater);
				questionView.user_name = (TextView)convertView.findViewById(R.id.user_name);
				questionView.question_content = (TextView)convertView.findViewById(R.id.question_content);
				
				convertView.setTag(questionView);
			}
			
			//往listview中的item填充数据
			HashMap<String, Object> questionInfo = questionList.get(position);
			if (questionInfo != null) {
				int avaterID = (Integer)questionInfo.get(keyStr[0]);
				String user_name = (String)questionInfo.get(keyStr[1]);
				String question_content = (String)questionInfo.get(keyStr[2]);
				//将数据填充到UI中
				questionView.user_avater.setImageDrawable(questionView.user_avater.getResources()
						.getDrawable(avaterID));
				questionView.user_name.setText(user_name);
				questionView.question_content.setText(question_content);
			}	
			return convertView;
		}
		
	}
}
