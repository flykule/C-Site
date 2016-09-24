package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.castle.csite.R;
import com.example.castle.csite.listener.ITopBarChangeListener;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.ui.fragment.CustomCollectFragment;
import com.example.castle.csite.ui.fragment.SubjectCollectFragment;
import com.example.castle.csite.ui.fragment.VideoCollectFragment;
import com.example.castle.csite.view.BindLayout;
import com.example.castle.csite.view.MyCollectTopBar;

/**
 * @author liqin
 * @time 2016/9/23  20:21
 * @desc 我的收藏页面
 */

@BindLayout(id = R.layout.activity_mycollect)
public class MyCollectActivity extends BaseActivity
        implements ITopBarChangeListener{

    private MyCollectTopBar mTopBar;
    private VideoCollectFragment mVideoFragment;
    private SubjectCollectFragment mSubjectFragment;
    private CustomCollectFragment mCustomFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        mTopBar = (MyCollectTopBar)findViewById(R.id.top_bar);
        mTopBar.setTopBarClickListener(this);
        mVideoFragment =new VideoCollectFragment();
        mSubjectFragment =new SubjectCollectFragment();
        mCustomFragment =new CustomCollectFragment();

        mTopBar.defaultShow();
    }

    @Override
    public void onTopItemClick(int id) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (id) {
            case R.id.frag_video_ll:
                transaction.replace(R.id.frag_bar, mVideoFragment);
                break;
            case R.id.frag_subject_ll:
                transaction.replace(R.id.frag_bar, mSubjectFragment);
                break;
            case R.id.frag_custom_ll:
                transaction.replace(R.id.frag_bar, mCustomFragment);
                break;
        }
        transaction.commit();
    }
}
