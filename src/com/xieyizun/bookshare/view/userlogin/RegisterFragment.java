package com.xieyizun.bookshare.view.userlogin;

import com.xieyizun.bookshare.MainActivity;
import com.xieyizun.bookshare.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RegisterFragment extends Fragment {

	private EditText user_name;
	private EditText user_email;
	private EditText user_password;
	private EditText user_repeate_password;
	private Button register;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle args) {
		View rootView = (View)inflater.inflate(R.layout.fragment_user_register, containter, false);
		
		register = (Button)rootView.findViewById(R.id.register);
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getActivity(), MainActivity.class));
				//注册成功后，销毁注册activity
				getActivity().finish();
			}
		});
		
		return rootView;
	}
}
