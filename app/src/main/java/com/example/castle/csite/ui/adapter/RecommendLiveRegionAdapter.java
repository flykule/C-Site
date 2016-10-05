package com.example.castle.csite.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
import com.example.castle.csite.view.DrawableCenterTextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by castle on 16-10-1.
 * 推荐页面直播适配器
 */
public class RecommendLiveRegionAdapter extends RecyclerView.Adapter<RecommendLiveRegionAdapter.ViewHolder> {


    private List<RecommendContent.ResultBean.BodyBean> mBeanList;

    public RecommendLiveRegionAdapter(List<RecommendContent.ResultBean.BodyBean> beanList) {
        mBeanList = beanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_live_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecommendContent.ResultBean.BodyBean bean = mBeanList.get(position);
        ImageLoader.load(UiUtils.getContext(), bean.getCover(), holder.mGroupImageView);
        if (StringUtil.nonNull(bean.getUp())) {
            holder.mTvLiveUp.setText(bean.getUp());
        } else {
            holder.mTvLiveUp.setText(bean.getName());
        }
        holder.mTvLiveWatch.setText(bean.getOnline());
        holder.mGroupDescText.setText(bean.getTitle());
        String area = bean.getArea();
        int end = area.length()+2;
        area = "#" + area + "#"+bean.getTitle();;
        SpannableStringBuilder style=new SpannableStringBuilder(area);
        style.setSpan(new ForegroundColorSpan(UiUtils.getColor(R.color.colorPrimary)), 0, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        holder.mGroupDescText.setText(style);
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
        @Bind(R.id.tv_live_up)
        TextView mTvLiveUp;
        @Bind(R.id.tv_live_watch)
        DrawableCenterTextView mTvLiveWatch;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mTvLiveWatch.setTextColor(UiUtils.getColor(R.color.gray));
            mTvLiveUp.setTextColor(UiUtils.getColor(R.color.gray));
        }
    }

    public List<RecommendContent.ResultBean.BodyBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<RecommendContent.ResultBean.BodyBean> beanList) {
        mBeanList = beanList;
    }
}
