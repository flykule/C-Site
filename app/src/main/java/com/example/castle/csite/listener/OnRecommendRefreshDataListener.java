package com.example.castle.csite.listener;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.ui.adapter.RecommendImageRecyclerAdapter;

import java.util.List;

/**
 * Created by castle on 16-10-3.
 * 当需要刷新数据时实现的接口
 */
public interface OnRecommendRefreshDataListener extends OnRefreshDataListener<List<RecommendContent.ResultBean>>{
    //刷新块数据的时候调用
    void onRefreshRegion(TextView tv,
                                                               ImageView iv,
                                                               RecommendImageRecyclerAdapter adapter);

}
