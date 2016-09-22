package com.example.castle.csite.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;

/**
 * Created by castle on 16-9-21.
 */
@BindLayout(id = R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 点击事件
     */
    public void click(View v) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
}
