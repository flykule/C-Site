package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseFragment;
import com.example.castle.csite.view.BindLayout;

/**
 * @author 灰太狼
 * @time 2016/10/4  11:13
 * @desc 离线缓存
 */
@BindLayout(id = R.layout.fragment_offline_cache)
public class OffLineCacheFragment extends BaseFragment {
   
  
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initData() {
        
    }

    @Override
    protected Interactor getInteractor() {
        return null;
    }
}
