package com.xieyizun.bookshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.xieyizun.bookshare.R;
import com.xieyizun.bookshare.view.userlogin.LoginFragment;

public class LoginActivity extends FragmentActivity {

	private FragmentManager fm;
	
	private Fragment loginFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		loginFragment = new LoginFragment();
		
		fm = getSupportFragmentManager();
		FragmentTransaction tranction = fm.beginTransaction();
		tranction.add(R.id.user_login_frame, loginFragment).commit();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_register:
				startActivity(new Intent(this, RegisterActivity.class));
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
