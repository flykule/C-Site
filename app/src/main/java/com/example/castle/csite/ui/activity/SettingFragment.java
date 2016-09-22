package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.castle.csite.R;
import com.example.castle.csite.view.SetCheckBox;

/**
 * @author 灰太狼
 * @time 2016/9/21  19:04
 * @desc 设置与帮助页面
 */
public class SettingFragment extends Fragment {

    private SetCheckBox mScb_over;
    private SetCheckBox mScb_qp_play;
    private SetCheckBox mScb_off_animation;
    private SetCheckBox mScb_no_message;
    private SetCheckBox mScb_vide_up;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_setting, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        mScb_over = (SetCheckBox) getActivity().findViewById(R.id.play_over);
        //全屏播放
        mScb_qp_play = (SetCheckBox) getActivity().findViewById(R.id.qp_play);
        //禁用动画
        mScb_off_animation = (SetCheckBox) getActivity().findViewById(R.id.off_Animation);
        //不接收推送消息
        mScb_no_message = (SetCheckBox) getActivity().findViewById(R.id.no_message);
        //海外视频加速
        mScb_vide_up = (SetCheckBox) getActivity().findViewById(R.id.vide_up);

    }
}
