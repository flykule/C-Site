package com.example.castle.csite.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.ui.activity.WebActivity;
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
        final LayoutInflater inflater = LayoutInflater.from(UiUtils.getContext());
        View view = inflater.inflate(R.layout.item_banner, null);
        ImageView bannerView = (ImageView) view.findViewById(R.id.iv_banner);
        final RecommendBanner.Banner banner = mBanners.get(position);
        bannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UiUtils.getContext(), WebActivity.class);
                intent.putExtra(WebActivity.WEB_URL, banner.getLink());
                intent.putExtra(WebActivity.WEB_TITLE, banner.getTitle());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UiUtils.getContext().startActivity(intent);
            }
        });
        ImageLoader.load(UiUtils.getContext(),banner.getCover(),bannerView);
        return view;
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

}
