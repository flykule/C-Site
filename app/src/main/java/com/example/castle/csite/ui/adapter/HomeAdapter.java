package com.example.castle.csite.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.fragment.FindFragment;
import com.example.castle.csite.ui.fragment.SubareaFragment;
import com.example.castle.csite.ui.fragment.VideoCollectFragment;
import com.example.castle.csite.util.UiUtils;

/**
 * Created by castle on 16-9-24.
 * 首页viewpager适配器
 */
public class HomeAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;

    public HomeAdapter(FragmentManager fm) {
        super(fm);
        mTitles = UiUtils.getArray(R.array.Home);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 3:
                return new SubareaFragment();
            case 5:
                return new FindFragment();           
                  
            default:
                return new VideoCollectFragment();

        }
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
