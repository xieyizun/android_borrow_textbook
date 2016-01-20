package com.xieyizun.bookshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.xieyizun.bookshare.R;
import com.xieyizun.bookshare.view.userlogin.RegisterFragment;

public class RegisterActivity extends FragmentActivity {
	private Fragment registerFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_register);
		
		registerFragment = new RegisterFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.register_user_frame, registerFragment).commit();
	}
}
