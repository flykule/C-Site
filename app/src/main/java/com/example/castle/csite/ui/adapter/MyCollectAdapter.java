package com.example.castle.csite.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.fragment.CustomCollectFragment;
import com.example.castle.csite.ui.fragment.SubjectCollectFragment;
import com.example.castle.csite.ui.fragment.VideoCollectFragment;
import com.example.castle.csite.util.UiUtils;

/**
 * Created by castle on 16-9-24.
 * 我的收藏viewpager适配器
 */
public class MyCollectAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;
    public MyCollectAdapter(FragmentManager fm) {
        super(fm);
        mTitles = UiUtils.getArray(R.array.MyCollect);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new VideoCollectFragment();
            case 1:
                return new SubjectCollectFragment();
            case 2:
                return new CustomCollectFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
