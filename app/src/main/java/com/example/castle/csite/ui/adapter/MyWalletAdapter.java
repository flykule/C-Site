package com.example.castle.csite.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.fragment.B_CoinFragment;
import com.example.castle.csite.ui.fragment.CoinFragment;
import com.example.castle.csite.util.UiUtils;

/**
 * Created by castle on 16-9-24.
 * 我的收藏viewpager适配器
 */
public class MyWalletAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;
    public MyWalletAdapter(FragmentManager fm) {
        super(fm);
        mTitles = UiUtils.getArray(R.array.MyWallet);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new B_CoinFragment();
            case 1:
                return new CoinFragment();

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
