package com.example.castle.csite.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.util.ImageLoader;
import com.example.castle.csite.util.UiUtils;
import com.example.castle.csite.view.BannerView;

import java.util.List;

/**
 * Created by castle on 16-8-18.
 * 轮播图adapter
 */
public class BannerPagerAdapter implements BannerView.Adapter {
    private final List<RecommendBanner.Banner> mBanners;

    public BannerPagerAdapter( List<RecommendBanner.Banner> banners) {
        mBanners = banners;
    }


    @Override
    public boolean isEmpty() {
        return mBanners.size() <= 0;
    }

    @Override
    public View getView(int position) {
        LayoutInflater inflater = LayoutInflater.from(UiUtils.getContext());
        View view = inflater.inflate(R.layout.item_banner, null);
        ImageView bannerView = (ImageView) view.findViewById(R.id.iv_banner);
        ImageLoader.load(UiUtils.getContext(),mBanners.get(position).getCover(),bannerView);
        return view;
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

}
