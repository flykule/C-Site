package com.example.castle.csite.ui.activity;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.ui.fragment.CollectFragment;
import com.example.castle.csite.ui.fragment.FindFragment;
import com.example.castle.csite.ui.fragment.HomeFragment;
import com.example.castle.csite.ui.fragment.SettingFragment;
import com.example.castle.csite.ui.fragment.WalletFragment;
import com.example.castle.csite.util.DoubleClickExit;
import com.example.castle.csite.util.SysUtil;
import com.example.castle.csite.util.ToastUtil;
import com.example.castle.csite.view.BindLayout;

/**
 * Created by castle on 16-9-21.
 */
@BindLayout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity implements FragmentManager.OnBackStackChangedListener {
	private static final String STATE_CURRENT_MENU_ITEM = "STATE_CURRENT_MENU_ITEM";
	Toolbar mToolbar;

	FrameLayout mContentFrame;

	NavigationView mNavigationView;

	DrawerLayout mDrawerLayout;
	//记录当前侧滑item
	private int mCurrentNavItem;
	//侧滑栏切换按钮
	private ActionBarDrawerToggle mDrawerToggle;
	private FragmentManager mFragmentManager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//初始化fragment manager
		mFragmentManager = getSupportFragmentManager();
		mFragmentManager.addOnBackStackChangedListener(this);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mContentFrame = (FrameLayout) findViewById(R.id.content_frame);
		mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		mToolbar.setTitleTextColor(Color.WHITE);
		//初始化toolbar,设置相应属性
		setSupportActionBar(mToolbar);
		//如果是lollipop以后的版本，那么可以设置视图高度
		if (SysUtil.isLollipopOrLater()) {
			mToolbar.setElevation(8f);
		}


		if (getSupportActionBar() != null) {
			//回退键
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
		//设置侧滑栏,以及切换按钮
		setUpSlideMenu();
		mDrawerToggle = new ActionBarDrawerToggle(
				this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close
		);
		mDrawerLayout.addDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			setFragment(new HomeFragment());
			/*setNavBar();
            mCurrentNavItem = R.id.drawer_menu_library;*/
		} else {
			//强制更新指示
			onBackStackChanged();
			mCurrentNavItem = savedInstanceState.getInt(STATE_CURRENT_MENU_ITEM);
		}
		//mNavigationView.getMenu().findItem(mCurrentNavItem).setChecked(true);
	}


	private void setUpSlideMenu() {
		mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				//close if the selected item equals current fragment
				if (mCurrentNavItem == item.getItemId()) {
					mDrawerLayout.closeDrawers();
					return true;
				}
				switch (item.getItemId()) {
					case R.id.drawer_menu_setting:
						pushFragment(new SettingFragment());
						break;
					case R.id.drawer_menu_app:
						pushFragment(new FindFragment());
						break;
					//我的收藏
					case R.id.drawer_menu_collection:
						pushFragment(new CollectFragment());
						break;
					//离线缓存
					case R.id.drawer_menu_cache:
						gotoTarget(OffLineCacheActivity.class);
						break;
					case R.id.drawer_menu_wallet:
						pushFragment(new WalletFragment());
						break;
					case R.id.drawer_menu_theme:
						gotoTarget(MyThemeActivity.class);
						break;
                    /*case R.id.drawer_menu_about:
                        break;
                    case R.id.drawer_menu_browser:
                        setFragment(new BrowserFragment());
                        break;
                    case R.id.drawer_menu_library:
                        setFragment(new LibraryFragment());
                        break;*/
				}
				//save selected id
				mCurrentNavItem = item.getItemId();
				item.setChecked(true);
				mDrawerLayout.closeDrawers();
				return true;
			}
		});
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	//在这里设置侧滑栏显示与关闭
	@Override
	public boolean onSupportNavigateUp() {
		if (!popFragment()) {
			if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
				mDrawerLayout.closeDrawers();
			} else {
				mDrawerLayout.openDrawer(GravityCompat.START);
			}
		}
		return super.onSupportNavigateUp();
	}

	@Override
	public void onBackPressed() {
		if (popFragment()) {
			if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
				mDrawerLayout.closeDrawers();
			}
		} else {
			//实现一定时间内双击退出
			if (!DoubleClickExit.check()) {
				ToastUtil.showShort("再按一次退出");
			} else {
				finish();
			}
		}
	}


	public void pushFragment(Fragment fragment) {
		mFragmentManager
				.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.addToBackStack(null)
				.commit();
	}


	//如果返回栈里面有就弹出fragment，否则返回false
	private boolean popFragment() {
		if (mFragmentManager.getBackStackEntryCount() > 0) {
			mFragmentManager.popBackStack();
			return true;
		}
		return false;
	}

	//pop all and set a new fragment
	private void setFragment(Fragment fragment) {
		if (mFragmentManager.getBackStackEntryCount() > 0) {
			//pop all fragment
			mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}

		mFragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.commit();
	}

	public void setNavBar() {
		//添加观察树，确保渲染完成后再添加头
		mNavigationView.getViewTreeObserver().addOnGlobalLayoutListener(
				new ViewTreeObserver.OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						//api 24以上不能直接找到header并用这种方式替换
						//api 24以下可以直接这么操作
						//在这里设置头
                        /*mFragmentManager.beginTransaction()
                                .replace(R.id.header, new HeaderFragment())
                                .commit();*/
						// Once data has been obtained, this listener is no longer needed, so remove it...
						//完成切换，及时销毁避免内存泄漏
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
							mNavigationView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						} else {
							mNavigationView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
						}
					}
				});

	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
		outPersistentState.putInt(STATE_CURRENT_MENU_ITEM, mCurrentNavItem);
		super.onSaveInstanceState(outState, outPersistentState);
	}

	@Override
	public void onBackStackChanged() {
		mDrawerToggle.setDrawerIndicatorEnabled(mFragmentManager.getBackStackEntryCount() == 0);
	}


}
