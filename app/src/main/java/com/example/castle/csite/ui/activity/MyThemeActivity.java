package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;



/**
 * @author 吴志强
 * @time 2016/9/21  19:55
 * @desc 主题选择
 */
@BindLayout(id = R.layout.activity_mytheme)
public class MyThemeActivity extends BaseActivity {

    private Button mBlackBtn;
    private Button mPinkBtn;
    private ImageView mArrowIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
