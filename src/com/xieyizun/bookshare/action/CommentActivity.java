package com.xieyizun.bookshare.action;

import com.xieyizun.bookshare.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CommentActivity extends Activity {
	
	private ListView comment_list;
	private EditText comment_input;
	private Button comment_send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		
		comment_list = (ListView)this.findViewById(R.id.comment_list);
		comment_input = (EditText)this.findViewById(R.id.comment_input);
		comment_send = (Button)this.findViewById(R.id.send_comment);
		
		
	}
}
