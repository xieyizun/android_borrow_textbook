package com.xieyizun.bookshare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.xieyizun.bookshare.action.SearchActivity;
import com.xieyizun.bookshare.action.UserActivity;
import com.xieyizun.bookshare.utils.UserPopupWindow;
import com.xieyizun.bookshare.view.BookPublishFragment;
import com.xieyizun.bookshare.view.FriendFragment;
import com.xieyizun.bookshare.view.ViewPagerFragment;
import com.xieyizun.bookshare.view.userlogin.LoginFragment;

public class MainActivity extends FragmentActivity {
	
	//判断用户是否登录
	private SharedPreferences sp;
	
	private RadioGroup bottomTabs;
	
	private FragmentManager fm;
	private FragmentTransaction tranction;
	
	private ViewPagerFragment homeFragment;
	
	private ViewPagerFragment userFragment;
	
	private FriendFragment friendFragment;
	
	private Fragment currentFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);      
        
    	if (savedInstanceState == null) {
    		homeFragment = ViewPagerFragment.newInstance(0);
    		friendFragment = new FriendFragment();
    		userFragment = ViewPagerFragment.newInstance(1);
    		
    		fm = getSupportFragmentManager();
    		tranction = fm.beginTransaction();
    		
    		tranction.add(R.id.main_content, homeFragment);
    		tranction.show(homeFragment); 		
    		
    		tranction.add(R.id.main_content, userFragment);
    		tranction.hide(userFragment);
    		
    		tranction.add(R.id.main_content, friendFragment);
    		tranction.hide(friendFragment);
    		
    		tranction.commit();
    		
    		currentFragment = homeFragment;
    		
    	}
		
        bottomTabs = (RadioGroup)this.findViewById(R.id.tab_menu);
        //监听radio点击事件
        bottomTabs.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch(checkedId) {
					//主页面
					case R.id.home_tab:
						if (currentFragment != null && !currentFragment.equals(homeFragment)) {
							if (!homeFragment.isAdded()) {
								fm.beginTransaction().add(R.id.main_content, homeFragment)
								.hide(currentFragment)
								.show(homeFragment)
								.commit();
							} else {
								fm.beginTransaction().hide(currentFragment)
								.show(homeFragment)
								.commit();							
							}
							currentFragment = homeFragment;	
						}
						break;
					//个人页面
					case R.id.person_tab:
						if (currentFragment != null && !currentFragment.equals(userFragment)) {
							if (!userFragment.isAdded()) {
								fm.beginTransaction().add(R.id.main_content, userFragment)
								.hide(currentFragment)
								.show(userFragment)
								.commit();
							} else {
								fm.beginTransaction().hide(currentFragment)
								.show(userFragment)
								.commit();
							}
							currentFragment = userFragment;
						}						
						break;
					//社区页面
					case R.id.friend_tab:
						if (currentFragment != null && !currentFragment.equals(friendFragment)) {
							if (!friendFragment.isAdded()) {
								fm.beginTransaction().add(R.id.main_content, friendFragment)
								.hide(currentFragment).show(friendFragment).commit();
							} else {
								fm.beginTransaction().hide(currentFragment)
								.show(friendFragment).commit();		
							}
							currentFragment = friendFragment;
						}
						break;

					default:
						break;
				}
			}     	
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    		case R.id.action_search:
    			startActivity(new Intent(this, SearchActivity.class));    			
    			break;
    		case R.id.action_settings:
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
