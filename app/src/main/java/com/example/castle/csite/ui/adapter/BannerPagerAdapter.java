package com.example.castle.csite.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.util.ImageLoader;
import com.example.castle.csite.util.UiUtils;

import java.util.List;

/**
 * Created by castle on 16-8-18.
 * 轮播图adapter
 */
public class BannerPagerAdapter extends PagerAdapter {
    private static BannerPagerAdapter mInstance;
    private final List<View> mViews;
    private final List<RecommendBanner.Banner> mBanners;

    public BannerPagerAdapter(List<View> views, List<RecommendBanner.Banner> banners) {
        mViews = views;
        mBanners = banners;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner);
        RecommendBanner.Banner banner = mBanners.get(position);
        if (banner != null) {
            ImageLoader.load(UiUtils.getContext(),banner.getCover(),imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position%mViews.size()));
    }
}
