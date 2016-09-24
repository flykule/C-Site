package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.TabHost;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.fragment.B_CoinFragment;
import com.example.castle.csite.ui.fragment.CoinFragment;
import com.example.castle.csite.util.FragmentTabHost;

/**
 * @author 吴志强
 * @time 2016/9/23  17:08
 * @desc ${TODD}
 */
public class TabActivity extends FragmentActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

		FragmentTabHost host = (FragmentTabHost) findViewById(R.id.tabs);
		host.setup(TabActivity.this, getSupportFragmentManager(), R.id.tabContent);

		TabHost.TabSpec one = host.newTabSpec("0");
		one.setIndicator("B币");

		TabHost.TabSpec two = host.newTabSpec("1");
		two.setIndicator("硬币");

		host.addTab(one, B_CoinFragment.class,null);
		host.addTab(two, CoinFragment.class,null);
	}


}
