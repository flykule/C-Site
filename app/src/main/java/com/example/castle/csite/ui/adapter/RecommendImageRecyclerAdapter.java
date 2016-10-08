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
import com.example.castle.csite.util.StringUtil;
import com.example.castle.csite.util.UiUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        showView(holder.mGroupDescText,holder.mTvAnswerWatch,holder.mTvAnswerDanmaku,holder.mTvPlayImageView);
        //如果是weblinnk类型那么设置相关控件隐藏
        if (!StringUtil.nonNull(bean.getTitle())) {
            hideView(holder.mGroupDescText,holder.mTvAnswerWatch,holder.mTvAnswerDanmaku,holder.mTvPlayImageView);
        }
        holder.mTvAnswerWatch.setText(bean.getPlay());
        holder.mTvAnswerDanmaku.setText(bean.getDanmaku());
        holder.mGroupDescText.setText(bean.getTitle());
    }

    public void hideView(View...views) {
        for (View view : views) {
            if (view.getVisibility()== View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
        }
    }
    public void showView(View...views) {
        for (View view : views) {
            if (view.getVisibility()== View.GONE||view.getVisibility()==View.INVISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_tv_play)
        ImageView mTvPlayImageView;
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
            ButterKnife.bind(this,itemView);

            mTvAnswerDanmaku.setTextColor(UiUtils.getColor(R.color.gray));
            mTvAnswerWatch.setTextColor(UiUtils.getColor(R.color.gray));
        }
    }

    public List<RecommendContent.ResultBean.BodyBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<RecommendContent.ResultBean.BodyBean> beanList) {
        mBeanList = beanList;
    }
}
