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
	//使该Fragment可重用，即被home和user重用
	private int type;
	
	//页面
	private ViewPager mViewPager;
	private SectionsPagerAdapter sectionsPagerAdapter;
	//tab标签
	private TabHost mTabHost;
	
	//通过静态工厂的方法来创建该fragment的实例
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
		
		//发送屏幕旋转时，保存该fragment
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
		
		//填充页面内容，sectionsPagerAdapter用来填充每一页
		sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager(), type);
		
		mViewPager.setAdapter(sectionsPagerAdapter);	
		
		//当页面发送滑动时，改变tab的内容
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//页面改动时改变tab
				mTabHost.setCurrentTab(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		//将tab和page对应起来
		mTabHost = (TabHost)view.findViewById(android.R.id.tabhost);
		mTabHost.setup();
		
		//为page添加对应的tab
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
		
		//为tab添加点击事件监听
		TabWidget tabWidget = mTabHost.getTabWidget();
		
		for (int i = 0; i != tabWidget.getChildCount(); i++) {
			//index在匿名内部类中使用，所以设为final
			final int index = i;
			tabWidget.getChildAt(i).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mTabHost.setCurrentTab(index);
					mViewPager.setCurrentItem(index);
				}
				
			});
		}
		//设置显示第一个标签对应的页面，其实就是进行了一次页面切换
		mViewPager.setCurrentItem(1);
		mViewPager.setCurrentItem(0);
		
		return view;
	}		
	
	//该适配器用于借助SectionFragment类，将数据装载进viewpager的每一页
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
				//根据position的不同，返回不同的fragment
				//分别为我的教材，借阅记录，好友和私信		
				f = UserPageFragment.newInstance(position);
			}
			return f;
		}

		@Override
		public int getCount() {
			int count = 0;
			if (type == 0)
				//home一共有6个page
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