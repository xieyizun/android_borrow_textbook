package com.xieyizun.bookshare.utils;

import com.xieyizun.bookshare.MainActivity;
import com.xieyizun.bookshare.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class UserPopupWindow extends PopupWindow {
	private View contentView;
	
	public UserPopupWindow(final Activity context) {
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		contentView = inflater.inflate(R.layout.popmenu_layout, null);
		
		int h = context.getWindowManager().getDefaultDisplay().getHeight();
		int w = context.getWindowManager().getDefaultDisplay().getWidth();
		
		//设置弹出窗口的view
		this.setContentView(contentView);
		//设置宽
		this.setWidth(w/2);
		//设置高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//设置可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		//刷新状态
		this.update();
		//实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(Color.DKGRAY);
		//点击back按钮或其他地方使其消失，设置了这个才能触发ondismisslistener，设置其他控件等操作
		this.setBackgroundDrawable(dw);
		//设置弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);
		
		LinearLayout addPublishBookLayout = (LinearLayout)contentView.findViewById(R.id.publish_book_layout);
		LinearLayout addSettingsLayout = (LinearLayout)contentView.findViewById(R.id.settings_layout);
		LinearLayout addLogoutLayout = (LinearLayout)contentView.findViewById(R.id.logout_layout);
		LinearLayout addCloseLayout = (LinearLayout)contentView.findViewById(R.id.close_layout);
		
		//发布图书按钮监听事件
		addPublishBookLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//startActivity(new Intent(getActivity(), ))
			}
		});
		
		//设置按钮监听事件
		addSettingsLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "设置", Toast.LENGTH_SHORT).show();
			}
		});
		
		//注销监听事件
		addLogoutLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toggleRemind(context, "注销账户");
			}
		});
		
		//设置关闭监听事件
		addCloseLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toggleRemind(context, "关闭");
			}
		});
	}
	
	//显示popupwindow
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			//以下拉方式显示popupWindow, parent为以某个控件开始，定义弹出位置
			this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
		} else {
			this.dismiss();
		}
	}
	
	//弹出对话框，用于确定是否注销或关闭
	public void toggleRemind(final Activity context, final String action) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("确认"+action+"？");
		
		builder.setPositiveButton("确认", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (action.equals("注销账户")) {
					//注销时清除密码，记住密码，自动登录
					SharedPreferences sp = context.getSharedPreferences("userInfo", 0);
					SharedPreferences.Editor editor = sp.edit();
					editor.clear();
					editor.commit();
					//关闭应用
					context.finish();					
				} else if (action.equals("关闭")) {
					context.finish();
				}
			}		
		});
		//取消按钮
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}			
		});
		//显示
		builder.create().show();
	}
}
