package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.network.api.ApiService;
import com.example.castle.csite.ui.adapter.BannerPagerAdapter;
import com.example.castle.csite.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by castle on 16-9-21.
 * 推荐页面
 */
public class RecommendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.banner_view_pager)
    ViewPager mBannerViewPager;
    @Bind(R.id.recommend_recycler_view)
    RecyclerView mRecommendRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ApiService mApiService;
    private List<RecommendBanner.Banner> mBanners;
    private Subscription mSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        getBanners();
        return view;
    }

    private void getBanners() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient())
                .baseUrl("http://bangumi.bilibili.com/")
                .build();
        mApiService = retrofit.create(ApiService.class);
        mSubscription = mApiService.getRecommendBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecommendBanner>() {
                    @Override
                    public void onCompleted() {
                        LogUtils.d("读取数据完成，拿到推荐页面banner");
                        initBanner();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d(e.getMessage());
                    }

                    @Override
                    public void onNext(RecommendBanner recommendBanner) {
                        mBanners = recommendBanner.getResult();
                    }
                });
    }

    private void initBanner() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        List<View> views = new ArrayList<>();
        for (int i = 0; i < mBanners.size(); i++) {
            View view = inflater.inflate(R.layout.item_banner, null);
            views.add(view);
        }
        BannerPagerAdapter mBannerAdapter = new BannerPagerAdapter(views, mBanners);
        mBannerViewPager.setAdapter(mBannerAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (mSubscription == null) {
            if (!mSubscription.isUnsubscribed()) {
                mSubscription.unsubscribe();
            }
        }
    }

    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
