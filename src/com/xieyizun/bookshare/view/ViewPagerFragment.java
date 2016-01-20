package com.xieyizun.bookshare.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.xieyizun.bookshare.R;
import com.xieyizun.bookshare.action.BookDetailActivity;

public class ViewPagerFragment extends Fragment {
	//ʹ��Fragment�����ã�����home��user����
	private int type;
	
	//ҳ��
	private ViewPager mViewPager;
	private SectionsPagerAdapter sectionsPagerAdapter;
	//tab��ǩ
	private TabHost mTabHost;
	
	//ͨ����̬�����ķ�����������fragment��ʵ��
	public static ViewPagerFragment newInstance(int type) {
		ViewPagerFragment f = new ViewPagerFragment();
		Bundle args = new Bundle();
		args.putInt("type", type);
		f.setArguments(args);
		return f;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.type = this.getArguments().getInt("type");	
		
		//������Ļ��תʱ�������fragment
		this.setRetainInstance(true);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundles) {
		
		View view = null;
		//home
		if (type == 0) {
			view = (View)inflater.inflate(R.layout.activity_home, container, false);
		    mViewPager = (ViewPager)view.findViewById(R.id.viewPager1);
		} else if (type == 1) {
			//user
			view = (View)inflater.inflate(R.layout.fragment_user, container, false);
			mViewPager = (ViewPager)view.findViewById(R.id.viewPager2);
		}
		
		//���ҳ�����ݣ�sectionsPagerAdapter�������ÿһҳ
		sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), type);
		
		mViewPager.setAdapter(sectionsPagerAdapter);	
		
		//��ҳ�淢�ͻ���ʱ���ı�tab������
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//ҳ��Ķ�ʱ�ı�tab
				mTabHost.setCurrentTab(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		//��tab��page��Ӧ����
		mTabHost = (TabHost)view.findViewById(android.R.id.tabhost);
		mTabHost.setup();
		
		//Ϊpage��Ӷ�Ӧ��tab
		for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
			if (type == 0) {
				mTabHost.addTab(mTabHost.newTabSpec(String.valueOf(i))
					.setIndicator(sectionsPagerAdapter.getPageTitle(i))
					.setContent(R.id.viewPager1));
			} else if (type == 1) {
				mTabHost.addTab(mTabHost.newTabSpec(String.valueOf(i))
						.setIndicator(sectionsPagerAdapter.getPageTitle(i))
						.setContent(R.id.viewPager2));
			}
		}
		
		//Ϊtab��ӵ���¼�����
		TabWidget tabWidget = mTabHost.getTabWidget();
		
		for (int i = 0; i != tabWidget.getChildCount(); i++) {
			//index�������ڲ�����ʹ�ã�������Ϊfinal
			final int index = i;
			tabWidget.getChildAt(i).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mTabHost.setCurrentTab(index);
					mViewPager.setCurrentItem(index);
				}
				
			});
		}
		//������ʾ��һ����ǩ��Ӧ��ҳ�棬��ʵ���ǽ�����һ��ҳ���л�
		mViewPager.setCurrentItem(1);
		mViewPager.setCurrentItem(0);
		
		return view;
	}		
	
	//�����������ڽ���SectionFragment�࣬������װ�ؽ�viewpager��ÿһҳ
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private int type;
		public SectionsPagerAdapter(FragmentManager fm, int type) {
			super(fm);
			this.type = type;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			Fragment f = null;
			if (type == 0)
				f = HomePageFragment.newInstance(position);
			else if (type == 1) {
				//����position�Ĳ�ͬ�����ز�ͬ��fragment
				//�ֱ�Ϊ�ҵĽ̲ģ����ļ�¼�����Ѻ�˽��		
				f = UserPageFragment.newInstance(position);
			}
			return f;
		}

		@Override
		public int getCount() {
			int count = 0;
			if (type == 0)
				//homeһ����6��page
				count = 6;
			else if (type == 1)
				count = 4;
			return count;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			if (type == 0) {
				switch (position) {
             		case 0:
             			return getString(R.string.title_section1);
             		case 1:
             			return getString(R.string.title_section2);
             		case 2:
             			return getString(R.string.title_section3);
             		case 3:
             			return getString(R.string.title_section4);
             		case 4:
             			return getString(R.string.title_section5);
             		case 5:
             			return getString(R.string.title_section6);
			 		}
			} else if (type == 1) {
				switch(position) {
					case 0:
						return getString(R.string.my_book);
					case 1:
						return getString(R.string.my_record);
					case 2:
						return getString(R.string.my_friends);
					case 3:
						return getString(R.string.my_letter);
				}
			}
			 return null;
		}
	}
}