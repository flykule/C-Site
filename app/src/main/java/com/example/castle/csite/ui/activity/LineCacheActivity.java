package com.example.castle.csite.ui.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.castle.csite.R;
import com.example.castle.csite.listener.ICacheSortPopListener;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.ui.pop.CacheSortPop;
import com.example.castle.csite.util.FileUtils;
import com.example.castle.csite.util.UiUtils;
import com.example.castle.csite.view.BindLayout;

import java.io.File;

import static com.example.castle.csite.R.id.indicator_tv;
import static com.example.castle.csite.R.id.showSd_tv;


/**
 * @author 灰太狼
 * @time 2016/10/4  12:12
 * @desc 离线缓存页面
 */
@BindLayout(id = com.example.castle.csite.R.layout.activity_linecache)
public class LineCacheActivity extends BaseActivity implements View.OnClickListener, ICacheSortPopListener {

    private ImageView mIv_xiala;
    private CacheSortPop mCacheSortPop;
    private TextView mIndicator_tv;
    private LinearLayout mLl_xiala;
    private ProgressBar mProgressBar;
    private ImageView mRefresh_iv;
    private Animation mAnimation;
    private TextView mShowSd_tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        mIv_xiala = (ImageView) findViewById(R.id.iv_xiala);
        mIndicator_tv = (TextView) findViewById(indicator_tv);
        mIv_xiala.setOnClickListener(this);
        mLl_xiala = (LinearLayout) findViewById(R.id.ll_xiala);
        mLl_xiala.setOnClickListener(this);
        mShowSd_tv = (TextView) findViewById(showSd_tv);

        mProgressBar = (ProgressBar) findViewById(R.id.pb_store);
      /*  long totalMeme = SystemInfoUtils.getTotalMeme(this);
        long avalibMeme = SystemInfoUtils.getAvalibMeme(this);
        mProgressBar.setMax(Integer.parseInt(String.valueOf(totalMeme)));
        mProgressBar.setProgress(Integer.parseInt(String.valueOf(avalibMeme)));*/

        mProgressBar.setMax(Integer.parseInt(String.valueOf((int)getSDTotalSize())));
        mProgressBar.setProgress(Integer.parseInt(String.valueOf( (int)((getSDTotalSize())-getSDAvailableSize()))));

        Drawable drawable = UiUtils.getDrawable(R.drawable.custom_progressbar);
        mProgressBar.setProgressDrawable(drawable);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(50);
        mProgressBar.invalidate();
        //刷新
        mRefresh_iv = (ImageView) findViewById(R.id.refresh_iv);
        mRefresh_iv.setOnClickListener(this);

        mShowSd_tv.setText("主储存"+FileUtils.getSDTotalSize()+"/可用:"+FileUtils.getSDAvailableSize());
        
       /* 
        long avalibMeme = SystemInfoUtils.getAvalibMeme(this);
        long totalMeme = SystemInfoUtils.getTotalMeme(this);*/
        //mProgressBar.setText("总/剩余内存:"+ Formatter.formatFileSize(this,totalMeme)+"/"+Formatter.formatFileSize(this,avalibMeme));
        //LogUtils.i("总/剩余内存" + getSDTotalSize() + "/" + getSDAvailableSize());
    }

    /**
     *  获得SD卡总大小
     * @return
     */
    private long getSDTotalSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    /**
     *  获得sd卡剩余容量，即可用大小
     * @return
     */
    private long getSDAvailableSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return blockSize * availableBlocks;
    }
    
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.linecache, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_xiala:
                //弹出下拉框
                if (mCacheSortPop == null) {
                    mCacheSortPop = new CacheSortPop(this);
                    mCacheSortPop.setOnSortChangeListener(this);
                }
                mCacheSortPop.onShow(mIv_xiala);
                //设置字体颜色
                mIv_xiala.setSelected(true);
                break;
            case R.id.iv_xiala:
                if (mCacheSortPop == null) {
                    mCacheSortPop = new CacheSortPop(this);
                    mCacheSortPop.setOnSortChangeListener(this);
                }
                mCacheSortPop.onShow(mIv_xiala);
                //设置字体颜色
                mIv_xiala.setSelected(true);
                break;
            case R.id.refresh_iv://刷新
                startAnimation();

                break;
        }
    }

    /**
     * 启动动画
     */
    private void startAnimation() {

      /*  mAnimation = AnimationUtils.loadAnimation(UiUtils.getContext(), R.anim.refresh_rotate);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatCount(2);
        mAnimation.setRepeatMode(1);
        mRefresh_iv.startAnimation(mAnimation);
*/
        RotateAnimation animation = new RotateAnimation(0f, 360f, 
                Animation.RELATIVE_TO_SELF,0.5f, 
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setRepeatCount(2);//设置重复次数 
        mRefresh_iv.setAnimation(animation);
        //开始动画
        mRefresh_iv.startAnimation(animation);


    }

    @Override
    public void onSortChaneged(int sort) {
        switch (sort) {
            case ICacheSortPopListener.TITLE_SORT:
                mIndicator_tv.setText("按标题");
                break;
            case ICacheSortPopListener.TIME_SORT:
                mIndicator_tv.setText("按时间");
                break;
            case ICacheSortPopListener.EPISODE_SORT:
                mIndicator_tv.setText("按剧集");
                break;

        }

    }
}
