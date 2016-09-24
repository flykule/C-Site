package com.example.castle.csite.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.castle.csite.R;
import com.example.castle.csite.listener.ITopBarChangeListener;

/**
 * @author liqin
 * @time 2016/9/23  20:54
 * @desc ${TODD}
 */
public class MyCollectTopBar extends LinearLayout
        implements View.OnClickListener,ViewPager.OnPageChangeListener{


    public ITopBarChangeListener mListener;
    private TextView mVideo;
    private TextView mSubject;
    private TextView mCustom;
    private View mVideoIndic;
    private View mSubjectIndic;
    private View mCustomIndic;

    int mCurrentTabId=-1;


    public MyCollectTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTopBarClickListener(ITopBarChangeListener listener){
        mListener =listener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //将一个布局xml文件转换成View  LayoutInflater
        findViewById(R.id.frag_video_ll).setOnClickListener(this);
        findViewById(R.id.frag_subject_ll).setOnClickListener(this);
        findViewById(R.id.frag_custom_ll).setOnClickListener(this);

        mVideo = (TextView) findViewById(R.id.frag_video);
        mSubject = (TextView) findViewById(R.id.frag_subject);
        mCustom = (TextView) findViewById(R.id.frag_custom);
        mVideoIndic = findViewById(R.id.video_view);
        mSubjectIndic = findViewById(R.id.subject_view);
        mCustomIndic = findViewById(R.id.custom_view);
    }

    /**
     * 设置所有的item指示器恢复默认
     */
    private void indicate(){
        mVideo.setSelected(false);
        mSubject.setSelected(false);
        mCustom.setSelected(false);
    }
    private void changeIndicStyle(View v){
        mVideoIndic.setVisibility(View.INVISIBLE);
        mSubjectIndic.setVisibility(View.INVISIBLE);
        mCustomIndic.setVisibility(View.INVISIBLE);
        v.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        //如果点击的ID是当前的id  就不需要改变Tab页
        if(mCurrentTabId==v.getId()) {
            return;
        }
        mCurrentTabId=v.getId();

        indicate();
        mListener.onTopItemClick(v.getId());
        switch (v.getId()) {
            case R.id.frag_video_ll :
                mVideo.setSelected(true);
                changeIndicStyle(mVideoIndic);

                break;
            case R.id.frag_subject_ll :
                mSubject.setSelected(true);
                changeIndicStyle(mSubjectIndic);
                break;
            case R.id.frag_custom_ll :
                mCustom.setSelected(true);
                changeIndicStyle(mCustomIndic);
                break;
        }

    }

    /**
     * 默认显示首页
     */
    public void defaultShow(){
        //模拟用户点击了R.id.frag_main_ll容器
        findViewById(R.id.frag_video).performClick();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                changeIndicStyle(mVideoIndic);
                break;
            case 1:
                changeIndicStyle(mSubjectIndic);
                break;
            case 2:
                changeIndicStyle(mCustomIndic);
                break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
