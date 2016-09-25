package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.util.ActivityUtil;
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
	private ImageView mArrowIv;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBlackBtn = (Button)findViewById(R.id.black_btn);
		mPinkBtn = (Button)findViewById(R.id.pink_btn);
		mArrowIv = (ImageView)findViewById(R.id.arrow_iv);
		mArrowIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.start(MyThemeActivity.this,MainActivity.class);
			}
		});
	}
}
