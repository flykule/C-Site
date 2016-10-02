package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.network.api.ApiService;
import com.example.castle.csite.ui.adapter.BannerPagerAdapter;
import com.example.castle.csite.ui.adapter.MyLinearLayoutManager;
import com.example.castle.csite.ui.adapter.RecommendRecyclerAdapter;
import com.example.castle.csite.ui.base.BaseFragment;
import com.example.castle.csite.util.LogUtils;
import com.example.castle.csite.util.SimpleSubscriber;
import com.example.castle.csite.util.UiUtils;
import com.example.castle.csite.view.BannerView;
import com.example.castle.csite.view.BindLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;



/**
 * Created by castle on 16-9-21.
 * 推荐页面
 */
@BindLayout(id = R.layout.fragment_recommend)
public class RecommendFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.recommend_recycler_view)
    RecyclerView mRecommendRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    BannerView mBanner;

    private ApiService mApiService;
    private List<RecommendBanner.DataBean> mBanners;
    private RecommendBannerInteractor mInteractor;

    /**
     * 在这里得到banner
     */
    private void getBanners() {
        mApiService = getApi("http://app.bilibili.com/");
        mInteractor = new RecommendBannerInteractor();
        mInteractor.execute(new SimpleSubscriber<RecommendBanner>() {
            @Override
            public void onNext(RecommendBanner recommendBanner) {
                mBanners = recommendBanner.getData();
                LogUtils.d("读取数据完成，拿到推荐页面banner");
                initBanner();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mBanner = new BannerView(getContext());
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        450);
        mBanner.setLayoutParams(params);
        return view;
    }
    /**
     * banner数据已经拿到，在这里初始化
     */
    private void initBanner() {
        BannerPagerAdapter mBannerAdapter = new BannerPagerAdapter(mBanners);
        mBanner.setAdapter(mBannerAdapter);
        mBanner.setSwitchTime(2000);
    }

    /**
     * base类提供的方法，必须重写，在这里获取数据
     */
    @Override
    protected void initData() {
        mSwipeRefreshLayout.setColorSchemeColors(UiUtils.getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setRefreshing(true);
        getBanners();
        getContent();
    }

    private void getContent() {
        RecommendContentInteractor interactor = new RecommendContentInteractor();
        interactor.execute(new SimpleSubscriber<RecommendContent>() {
            @Override
            public void onNext(RecommendContent recommendContent) {
                RecommendRecyclerAdapter adapter = new RecommendRecyclerAdapter(recommendContent.getResult());
                adapter.setHeaderView(mBanner);
                mRecommendRecyclerView.setAdapter(adapter);
                mRecommendRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
            }
        });
    }

    @Override
    protected Interactor getInteractor() {
        return mInteractor;
    }

    /**
     * 当下拉刷新时做的事情，判断是否需要重新获取数据
     */
    @Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 继承Interactor用于获取数据
     */
    class RecommendBannerInteractor extends Interactor<RecommendBanner, String> {
        @Override
        protected Observable<RecommendBanner> buildObservable(String[] parameter) {
            return mApiService.getRecommendBanner();
        }
    }
    /**
     * 继承Interactor用于获取数据
     */
    class RecommendContentInteractor extends Interactor<RecommendContent, String> {
        @Override
        protected Observable<RecommendContent> buildObservable(String[] parameter) {
            return mApiService.getRecommendContent();
        }
    }

}
