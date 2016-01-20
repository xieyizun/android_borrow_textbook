package com.xieyizun.bookshare.action;

import com.xieyizun.bookshare.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class SearchActivity extends Activity {

	private EditText search_content;
	private Button search_button;
	
	private ListView search_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		search_content = (EditText)this.findViewById(R.id.search_input);
		search_button = (Button)this.findViewById(R.id.search);
		
		search_result = (ListView)this.findViewById(R.id.search_result_list);
		
		
		//ËÑË÷¿ò¼àÌýÆ÷
		search_content.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}		
		});
		
		//ËÑË÷°´Å¥¼àÌýÆ÷
		search_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (search_content.getText().length() == 0) {
					Toast.makeText(SearchActivity.this, "ÇëÊäÈëÊéÃû", Toast.LENGTH_SHORT).show();			
				} else {
					Toast.makeText(SearchActivity.this, "ÕýÔÚËÑË÷...", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
