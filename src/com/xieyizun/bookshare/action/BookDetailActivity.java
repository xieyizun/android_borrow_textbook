package com.xieyizun.bookshare.action;

import java.util.ArrayList;
import java.util.List;

import com.xieyizun.bookshare.MainActivity;
import com.xieyizun.bookshare.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class BookDetailActivity extends Activity {
	
	private ImageView bookImage;
	private TextView bookName;
	private TextView bookAuthor;
	private TextView bookPublish;
	
	private Spinner contactList;
	
	//私信
	private EditText chat_content;
	private Button chat_send;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookdetail);
		
		bookImage = (ImageView)this.findViewById(R.id.bookdetail_image);
		
		bookName = (TextView)this.findViewById(R.id.bookdetail_name);
		bookAuthor = (TextView)this.findViewById(R.id.bookdetail_author);
		bookPublish	= (TextView)this.findViewById(R.id.bookdetail_publish);
		
		contactList = (Spinner)this.findViewById(R.id.bookdetail_contact);
		
		Intent intent = getIntent();
		
		bookImage.setImageResource(intent.getIntExtra("item_image", 0));
		bookName.setText(intent.getStringExtra("item_name"));
		bookAuthor.setText(intent.getStringExtra("item_author"));
		bookPublish.setText(intent.getStringExtra("item_publish"));
		
		//发布者联系方式
		List<String> contacts = new ArrayList<String>();
		contacts.add("邮箱：xxx@qq.com");
		contacts.add("QQ：xxxx");
		contacts.add("电话：xxxxx");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				contacts);
		adapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
		
		contactList.setAdapter(adapter);
		
		chat_content = (EditText)this.findViewById(R.id.chat_content);
		chat_send = (Button)this.findViewById(R.id.send_chat);
	}
}
