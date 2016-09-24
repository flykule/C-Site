package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TabHost;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.fragment.B_CoinFragment;
import com.example.castle.csite.ui.fragment.CoinFragment;
import com.example.castle.csite.util.FragmentTabHost;
import com.example.castle.csite.view.BindLayout;

/**
 * @author 吴志强
 * @time 2016/9/21  20:11
 * @desc ${TODD}
 */
@BindLayout(id=R.layout.activity_wallet)
public class MyWalletActivity extends FragmentActivity{
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentTabHost host = (FragmentTabHost) findViewById(R.id.tabs);
		host.setup(MyWalletActivity.this, getSupportFragmentManager(), R.id.tabContent);

		TabHost.TabSpec one = host.newTabSpec("0");
		one.setIndicator("B币");

		TabHost.TabSpec two = host.newTabSpec("1");
		two.setIndicator("硬币");

		host.addTab(one, B_CoinFragment.class,null);
		host.addTab(two, CoinFragment.class,null);
	}
}
