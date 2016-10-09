package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.adapter.mythemeAdapter;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;



/**
 * @author 吴志强
 * @time 2016/9/21  19:55
 * @desc 主题选择
 */
@BindLayout(id = R.layout.activity_mytheme)
public class MyThemeActivity extends BaseActivity {


    private ListView mLv_showtheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_mytheme);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initUI();
    }

    private void initUI() {
        mLv_showtheme = (ListView) findViewById(R.id.lv_showtheme);
        mLv_showtheme.setAdapter(new mythemeAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mytheme, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
