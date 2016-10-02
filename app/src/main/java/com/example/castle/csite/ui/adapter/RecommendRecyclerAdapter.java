package com.example.castle.csite.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.ui.base.BaseRecyclerAdapter;
import com.example.castle.csite.util.UiUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by castle on 16-10-1.
 */

public class RecommendRecyclerAdapter extends BaseRecyclerAdapter {

    private List<RecommendContent.ResultBean> mBeanList;

    public RecommendRecyclerAdapter(List<RecommendContent.ResultBean> beanList) {
        mBeanList = beanList;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder getNormalViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommend_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void bindNormal(RecyclerView.ViewHolder holder,int position) {
        ViewHolder myHolder = (ViewHolder) holder;
        List<RecommendContent.ResultBean.BodyBean> body = mBeanList.get(position).getBody();
        RecommendImageRecyclerAdapter adapter = new RecommendImageRecyclerAdapter(body);
        myHolder.mGroupImageRecycler.setAdapter(adapter);
        myHolder.mGroupImageRecycler.setLayoutManager(new GridLayoutManager(UiUtils.getContext(),2));

    }



    @Override
    protected int getItemsCount() {
         return mBeanList.size()+1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.header_view)
        FrameLayout mHeaderView;
        @Bind(R.id.group_image_recycler)
        RecyclerView mGroupImageRecycler;
        @Bind(R.id.footer_view)
        FrameLayout mFooterView;

        public ViewHolder(View itemView) {
            super(itemView);
            mHeaderView = (FrameLayout) itemView.findViewById(R.id.header_view);
            mGroupImageRecycler = (RecyclerView) itemView.findViewById(R.id.group_image_recycler);
            mFooterView = (FrameLayout) itemView.findViewById(R.id.footer_view);
        }
    }


}
