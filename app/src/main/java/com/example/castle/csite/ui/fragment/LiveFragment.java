package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.castle.csite.R;

/**
 * @author 灰太狼
 * @time 2016/9/25  13:11
 * @desc 直播页面
 */
public class LiveFragment extends Fragment {

    private ViewPager mVp_live;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mVp_live = (ViewPager) getActivity().findViewById(R.id.vp_live);
    }
}
