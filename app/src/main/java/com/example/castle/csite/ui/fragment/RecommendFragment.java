package com.example.castle.csite.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.network.api.ApiService;
import com.example.castle.csite.ui.adapter.BannerPagerAdapter;
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
    @Bind(R.id.banner_view_pager)
    BannerView mBanner;

    private ApiService mApiService;
    private List<RecommendBanner.Banner> mBanners;
    private RecommendBannerInteractor mInteractor;

    /**
     * 在这里得到banner
     */
    private void getBanners() {
        mInteractor = new RecommendBannerInteractor();
        mInteractor.execute(new SimpleSubscriber<RecommendBanner>() {
            @Override
            public void onNext(RecommendBanner recommendBanner) {
                mBanners = recommendBanner.getResult();
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
            mApiService = getApi("http://bangumi.bilibili.com/");
            return mApiService.getRecommendBanner();
        }
    }

}
