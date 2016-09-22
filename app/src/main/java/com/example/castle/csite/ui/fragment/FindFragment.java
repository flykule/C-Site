package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.activity.InterestActivity;
import com.example.castle.csite.util.ActivityUtil;

/**
 * @author 灰太狼
 * @time 2016/9/22  14:10
 * @desc 发现的Fragment
 */
public class FindFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mInterest;
    private RelativeLayout mHuati;
    private RelativeLayout mHuodong;
    private RelativeLayout mYuanquang;
    private RelativeLayout mPaihang;
    private RelativeLayout mGame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initUI();
    }

    private void initUI() {
        //兴趣圈
        mInterest = (RelativeLayout) getActivity().findViewById(R.id.interest);
        mInterest.setOnClickListener(this);
        //话题中心
        mHuati = (RelativeLayout) getActivity().findViewById(R.id.huati);
        mHuati.setOnClickListener(this);
        //活动中心
        mHuodong = (RelativeLayout) getActivity().findViewById(R.id.huodong);
        mHuodong.setOnClickListener(this);
        //原创排行榜
        mYuanquang = (RelativeLayout) getActivity().findViewById(R.id.yuanquang);
        mYuanquang.setOnClickListener(this);
        //全区排行榜
        mPaihang = (RelativeLayout) getActivity().findViewById(R.id.paihang);
        mPaihang.setOnClickListener(this);
        //游戏中心
        mGame = (RelativeLayout) getActivity().findViewById(R.id.game);
        mGame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.interest:
                ActivityUtil.start(getActivity(), InterestActivity.class);
                break;
            case R.id.huati:

                break;
            case R.id.huodong:

                break;
            case R.id.yuanquang:

                break;
            case R.id.paihang:

                break;
            case R.id.game:

                break;
        }
    }
}



