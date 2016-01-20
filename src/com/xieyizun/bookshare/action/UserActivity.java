package com.xieyizun.bookshare.action;

import com.xieyizun.bookshare.MainActivity;
import com.xieyizun.bookshare.R;
import com.xieyizun.bookshare.utils.UserPopupWindow;
import com.xieyizun.bookshare.view.ViewPagerFragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class UserActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		//用户已经加载用户页面	
		Fragment f = ViewPagerFragment.newInstance(1);
		getSupportFragmentManager().beginTransaction().add(R.id.user_content, f).commit();
	}
	
	//系统返回键
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.user_setting:
				showPopupWindow();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showPopupWindow() {
		Rect frame = new Rect();
		getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		
		//int x = getActionBar().getHeight() - frame.top;
		int y = getActionBar().getHeight() + frame.top;
		
		View parentView = getLayoutInflater().inflate(R.layout.activity_user, null);
		
		UserPopupWindow popup = new UserPopupWindow(this);
		popup.showAtLocation(parentView, Gravity.RIGHT | Gravity.TOP, 20, y);		
		
	}
}
