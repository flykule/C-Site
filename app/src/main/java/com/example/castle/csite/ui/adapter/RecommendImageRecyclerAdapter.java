package com.example.castle.csite.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.util.ImageLoader;
import com.example.castle.csite.util.UiUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by castle on 16-10-1.
 * 推荐页面图片适配器
 */
public class RecommendImageRecyclerAdapter extends RecyclerView.Adapter<RecommendImageRecyclerAdapter.ViewHolder> {


    private List<RecommendContent.ResultBean.BodyBean> mBeanList;

    public RecommendImageRecyclerAdapter(List<RecommendContent.ResultBean.BodyBean> beanList) {
        mBeanList = beanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecommendContent.ResultBean.BodyBean bean = mBeanList.get(position);
        ImageLoader.load(UiUtils.getContext(),bean.getCover(),holder.mGroupImageView);
        holder.mTvAnswerWatch.setText(bean.getPlay());
        holder.mTvAnswerDanmaku.setText(bean.getTitle());
        holder.mGroupDescText.setText(bean.getTitle());
    }


    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.group_image_view)
        ImageView mGroupImageView;
        @Bind(R.id.group_desc_text)
        TextView mGroupDescText;
        @Bind(R.id.tv_answer_danmaku)
        TextView mTvAnswerDanmaku;
        @Bind(R.id.tv_answer_watch)
        TextView mTvAnswerWatch;

        public ViewHolder(View itemView) {
            super(itemView);
            mGroupImageView = (ImageView) itemView.findViewById(R.id.group_image_view);
            mGroupDescText = (TextView) itemView.findViewById(R.id.group_desc_text);
            mTvAnswerDanmaku = (TextView) itemView.findViewById(R.id.tv_answer_danmaku);
            mTvAnswerWatch = (TextView) itemView.findViewById(R.id.tv_answer_watch);
        }
    }


}
