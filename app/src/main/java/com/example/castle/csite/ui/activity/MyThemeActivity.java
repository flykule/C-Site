package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;

/**
 * @author 吴志强
 * @time 2016/9/21  19:55
 * @desc ${TODD}
 */
@BindLayout(id= R.layout.activity_theme)
public class MyThemeActivity extends BaseActivity{

	private Button mBlackBtn;
	private Button mPinkBtn;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBlackBtn = (Button)findViewById(R.id.black_btn);
		mPinkBtn = (Button)findViewById(R.id.pink_btn);

	}
}
