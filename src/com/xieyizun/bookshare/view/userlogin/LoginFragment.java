package com.xieyizun.bookshare.view.userlogin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.xieyizun.bookshare.MainActivity;
import com.xieyizun.bookshare.R;

public class LoginFragment extends Fragment {
	
	private EditText login_user_name;
	private EditText login_user_password;
	private Button login_button;
	private CheckBox remember_me;
	private CheckBox auto_login;
	
	//用于保存用户名，密码等
	private SharedPreferences sp;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle args) {
		View rootView = (View)inflater.inflate(R.layout.fragment_user_login, container, false);
		
		login_user_name = (EditText)rootView.findViewById(R.id.login_username);
		login_user_password = (EditText)rootView.findViewById(R.id.login_userpassword);
		login_button = (Button)rootView.findViewById(R.id.login);
		remember_me = (CheckBox)rootView.findViewById(R.id.remember);
		auto_login = (CheckBox)rootView.findViewById(R.id.autologin);
		
		getActivity();
		sp = getActivity().getSharedPreferences("userInfo", 0);
		String name = sp.getString("user_name", "");
		String password = sp.getString("user_password", "");
		
		boolean isRemember = sp.getBoolean("user_remember", false);
		boolean isAutoLogin = sp.getBoolean("user_autologin", false);
		
		if (isRemember) {
			login_user_name.setText(name);
			login_user_password.setText(password);
			remember_me.setChecked(true);
		}
		
		if (isAutoLogin) {
			auto_login.setChecked(true);
			//直接跳转到mainactivity
			startActivity(new Intent(getActivity(), MainActivity.class));
			getActivity().finish();
		}
		
		login_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String userName = login_user_name.getText().toString();
				String userPassword = login_user_password.getText().toString();
				
				if (userName.equals("xieyizun") && userPassword.equals("123")) {
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("user_name", userName);
					editor.putString("user_password", userPassword);
					if (remember_me.isChecked())
						editor.putBoolean("user_remember", true);
					else
						editor.putBoolean("user_remember", false);
					if (auto_login.isChecked())
						editor.putBoolean("user_autologin", true);
					else
						editor.putBoolean("user_autologin", false);
					editor.commit();
					//跳转到主页面
					startActivity(new Intent(getActivity(), MainActivity.class));
					//登录成功后，销毁登录activity
					getActivity().finish();
				} else {
					Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return rootView;
	}
}
