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
		
		//���õ������ڵ�view
		this.setContentView(contentView);
		//���ÿ�
		this.setWidth(w/2);
		//���ø�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		//���ÿɵ��
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		//ˢ��״̬
		this.update();
		//ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(Color.DKGRAY);
		//���back��ť�������ط�ʹ����ʧ��������������ܴ���ondismisslistener�����������ؼ��Ȳ���
		this.setBackgroundDrawable(dw);
		//���õ������嶯��Ч��
		this.setAnimationStyle(R.style.AnimationPreview);
		
		LinearLayout addPublishBookLayout = (LinearLayout)contentView.findViewById(R.id.publish_book_layout);
		LinearLayout addSettingsLayout = (LinearLayout)contentView.findViewById(R.id.settings_layout);
		LinearLayout addLogoutLayout = (LinearLayout)contentView.findViewById(R.id.logout_layout);
		LinearLayout addCloseLayout = (LinearLayout)contentView.findViewById(R.id.close_layout);
		
		//����ͼ�鰴ť�����¼�
		addPublishBookLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//startActivity(new Intent(getActivity(), ))
			}
		});
		
		//���ð�ť�����¼�
		addSettingsLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(context, "����", Toast.LENGTH_SHORT).show();
			}
		});
		
		//ע�������¼�
		addLogoutLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toggleRemind(context, "ע���˻�");
			}
		});
		
		//���ùرռ����¼�
		addCloseLayout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toggleRemind(context, "�ر�");
			}
		});
	}
	
	//��ʾpopupwindow
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			//��������ʽ��ʾpopupWindow, parentΪ��ĳ���ؼ���ʼ�����嵯��λ��
			this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 18);
		} else {
			this.dismiss();
		}
	}
	
	//�����Ի�������ȷ���Ƿ�ע����ر�
	public void toggleRemind(final Activity context, final String action) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("ȷ��"+action+"��");
		
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (action.equals("ע���˻�")) {
					//ע��ʱ������룬��ס���룬�Զ���¼
					SharedPreferences sp = context.getSharedPreferences("userInfo", 0);
					SharedPreferences.Editor editor = sp.edit();
					editor.clear();
					editor.commit();
					//�ر�Ӧ��
					context.finish();					
				} else if (action.equals("�ر�")) {
					context.finish();
				}
			}		
		});
		//ȡ����ť
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();				
			}			
		});
		//��ʾ
		builder.create().show();
	}
}
