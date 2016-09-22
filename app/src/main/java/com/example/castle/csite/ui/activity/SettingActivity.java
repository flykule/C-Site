package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;
import com.example.castle.csite.view.SetCheckBox;

/**
 * @author 灰太狼
 * @time 2016/9/21  19:04
 * @desc 设置与帮助页面
 */
@BindLayout(id = R.layout.activity_setting)
public class SettingActivity extends BaseActivity {

    private SetCheckBox mScb_over;
    private SetCheckBox mScb_qp_play;
    private SetCheckBox mScb_off_animation;
    private SetCheckBox mScb_no_message;
    private SetCheckBox mScb_vide_up;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        onclick();
    }

    /**
     * 自定义控件的点击方法
     */
    private void onclick() {
        mScb_over.setChecked(false);
        mScb_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScb_over.isChecked()) {
                    mScb_over.setChecked(false);
                } else {
                    mScb_over.setChecked(true);
                }
            }
        });
        mScb_qp_play.setChecked(false);
        mScb_qp_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScb_qp_play.isChecked()) {
                    mScb_qp_play.setChecked(false);
                } else {
                    mScb_qp_play.setChecked(true);
                }
            }
        });
        mScb_off_animation.setChecked(false);
        mScb_off_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScb_off_animation.isChecked()) {
                    mScb_off_animation.setChecked(false);
                } else {
                    mScb_off_animation.setChecked(true);
                }
            }
        });
        mScb_no_message.setChecked(false);
        mScb_no_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScb_no_message.isChecked()) {
                    mScb_no_message.setChecked(false);
                } else {
                    mScb_no_message.setChecked(true);
                }
            }
        });
        mScb_vide_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mScb_vide_up.isChecked()) {
                    mScb_vide_up.setChecked(false);
                } else {
                    mScb_vide_up.setChecked(true);
                }
            }
        });
    }


    /**
     * 初始化UI
     */
    private void initUI() {
        //横屏播放
        mScb_over = (SetCheckBox) findViewById(R.id.play_over);
        //全屏播放
        mScb_qp_play = (SetCheckBox) findViewById(R.id.qp_play);
        //禁用动画
        mScb_off_animation = (SetCheckBox) findViewById(R.id.off_Animation);
        //不接收推送消息
        mScb_no_message = (SetCheckBox) findViewById(R.id.no_message);
        //海外视频加速
        mScb_vide_up = (SetCheckBox) findViewById(R.id.vide_up);

    }
}
