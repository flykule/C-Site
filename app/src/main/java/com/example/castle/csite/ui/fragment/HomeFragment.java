package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.adapter.HomeAdapter;

/**
 * Created by castle on 16-9-21.
 */
public class HomeFragment extends Fragment{
    TabLayout mTabLayout;

    ViewPager mViewPager;
    //保存之前创建的view，用于防止重复调用出现空白
    View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_mycollect, container, false);
            mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);
            mTabLayout = (TabLayout) mView.findViewById(R.id.tab_layout);
            mViewPager.setAdapter(new HomeAdapter(getFragmentManager()));
            mTabLayout.setupWithViewPager(mViewPager);
        } else {
            container.removeAllViews();
        }
        return mView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
