package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.example.castle.csite.R;
import com.example.castle.csite.listener.ICacheSortPopListener;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.ui.pop.CacheSortPop;
import com.example.castle.csite.util.SystemInfoUtils;
import com.example.castle.csite.view.BindLayout;

/**
 * @author liqin
 * @time 2016/9/22  11:47
 * @desc 离线缓存
 */

@BindLayout(id = R.layout.activity_offline_cache)
public class OffLineCacheActivity extends BaseActivity
        implements View.OnClickListener,ICacheSortPopListener {

    private TextView mIndicatorTv;
    private CacheSortPop mCacheSortPop;
    private TextView mMenTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_offline_cache);
        initUI();
    }

    private void initUI() {
        mIndicatorTv = (TextView)findViewById(R.id.indicator_tv);
        mIndicatorTv.setOnClickListener(this);

        mMenTv = (TextView)findViewById(R.id.men_tv);
        long avalibMeme = SystemInfoUtils.getAvalibMeme(this);
        long totalMeme = SystemInfoUtils.getTotalMeme(this);
        mMenTv.setText("总/剩余内存:"+ Formatter.formatFileSize(this,totalMeme)+"/"+Formatter.formatFileSize(this,avalibMeme));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.indicator_tv ://标题
                if(mCacheSortPop == null) {
                    mCacheSortPop = new CacheSortPop(this);
                    mCacheSortPop.setOnSortChangeListener(this);
                }
                mCacheSortPop.onShow(mIndicatorTv);
                //设置字体颜色
                mIndicatorTv.setSelected(true);
                break;
        }
    }

    @Override
    public void onSortChaneged(int sort) {
        switch (sort) {
            case ICacheSortPopListener.TITLE_SORT:
                mIndicatorTv.setText("按标题");
                break;
            case ICacheSortPopListener.TIME_SORT:
                mIndicatorTv.setText("按时间");
                break;
            case ICacheSortPopListener.EPISODE_SORT:
                mIndicatorTv.setText("按剧集");
                break;

        }
    }
}
