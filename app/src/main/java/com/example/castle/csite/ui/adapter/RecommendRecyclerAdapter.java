package com.example.castle.csite.ui.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.cons.RecommendConst;
import com.example.castle.csite.listener.OnRecommendRefreshDataListener;
import com.example.castle.csite.ui.base.BaseRecyclerAdapter;
import com.example.castle.csite.util.UiUtils;

import java.util.List;

import butterknife.Bind;

/**
 * Created by castle on 16-10-1.
 * 推荐页面RecyclerView适配器
 */
public class RecommendRecyclerAdapter extends BaseRecyclerAdapter {

    private final OnRecommendRefreshDataListener mListener;
    private final Animation mAnimation;
    private List<RecommendContent.ResultBean> mBeanList;
    private LayoutInflater mInflater;

    public RecommendRecyclerAdapter(List<RecommendContent.ResultBean> beanList,
                                    OnRecommendRefreshDataListener listener) {
        mBeanList = beanList;
        mListener = listener;
        mAnimation = AnimationUtils.loadAnimation(UiUtils.getContext(), R.anim.refresh_rotate);
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setDuration(500);
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder getNormalViewHolder(ViewGroup parent) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_recommend_content, parent, false);
        view.setMinimumHeight(parent.getMeasuredHeight()/4);
        return new ViewHolder(view);
    }

    @Override
    protected void bindNormal(RecyclerView.ViewHolder holder,int position) {
        final ViewHolder myHolder = (ViewHolder) holder;
        final RecommendContent.ResultBean resultBean = mBeanList.get(position-1);
        LinearLayoutCompat.LayoutParams params =
                new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        myHolder.itemView.setLayoutParams(params);
        myHolder.mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                //如果是第一个(添加HeaderView)   还有就是最后一个(FooterView)
                return resultBean.getType().equals("weblink") ? myHolder.mLayoutManager.getSpanCount() : 1;
            }
        });
        //根据类型进行区分
        switch (resultBean.getType()) {
            case RecommendConst.RECOMMEND:
                initRecommendRegion(myHolder);
                break;
            case RecommendConst.LIVE:
                initLiveRegion(myHolder, resultBean);
                return;
            default:
                hideHeadAndFoot(myHolder);
                break;
        }
        //因为有多个type，必须动态设置宽高

        List<RecommendContent.ResultBean.BodyBean> body = resultBean.getBody();
        RecommendImageRecyclerAdapter adapter = new RecommendImageRecyclerAdapter(body);
        myHolder.mGroupImageRecycler.setAdapter(adapter);

    }

    private void initLiveRegion(final ViewHolder myHolder, RecommendContent.ResultBean resultBean) {
        hideHeadAndFoot(myHolder);
        View view = mInflater.inflate(R.layout.header_live, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_head_live_middle);
        tv.setText(resultBean.getHead().getCount());
        tv.setTextColor(UiUtils.getColor(R.color.colorPrimary));
        setHead(myHolder,view);
        final RecommendLiveRegionAdapter liveRegionAdapter = new RecommendLiveRegionAdapter(resultBean.getBody());
        myHolder.mGroupImageRecycler.setAdapter(liveRegionAdapter);
        View footView = mInflater.inflate(R.layout.foot_refresh_more, null);
        setFoot(myHolder,footView);
        RelativeLayout layout = (RelativeLayout) footView.findViewById(R.id.foot_refresh_container);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = startRotate(myHolder);
                TextView textView = (TextView) myHolder.mFooterView.findViewById(R.id.tv_refresh_more);
                textView.setText("玩命加载中");
                mListener.onRefreshRegion(textView,imageView,liveRegionAdapter,RecommendConst.LIVE);
            }
        });
    }

    private void hideHeadAndFoot(ViewHolder myHolder) {
        myHolder.mHeaderView.setVisibility(View.GONE);
        myHolder.mFooterView.setVisibility(View.GONE);
    }

    private void initRecommendRegion(final ViewHolder myHolder) {
        setHead(myHolder,R.layout.header_hot);
        setFoot(myHolder,R.layout.foot_hot);
        myHolder.mFooterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshHotRegion(myHolder);
            }
        });
    }

    private void setFoot(ViewHolder myHolder,int layoutId) {
        myHolder.mFooterView.removeAllViews();
        myHolder.mFooterView.setVisibility(View.VISIBLE);
        View hotFoot = mInflater.inflate(layoutId, null);
        myHolder.mFooterView.addView(hotFoot);
    }
    private void setFoot(ViewHolder myHolder,View  hotFoot) {
        myHolder.mFooterView.removeAllViews();
        myHolder.mFooterView.setVisibility(View.VISIBLE);
        myHolder.mFooterView.addView(hotFoot);
    }

    private void setHead(ViewHolder myHolder,int layoutId) {
        myHolder.mHeaderView.removeAllViews();
        View hotHead = mInflater.inflate(layoutId, null);
        myHolder.mHeaderView.addView(hotHead);
        myHolder.mHeaderView.setVisibility(View.VISIBLE);
    }

    private void setHead(ViewHolder myHolder,View head) {
        myHolder.mHeaderView.removeAllViews();
        myHolder.mHeaderView.addView(head);
        myHolder.mHeaderView.setVisibility(View.VISIBLE);
    }

    //推荐块刷新
    private void refreshHotRegion(ViewHolder myHolder) {

        TextView textView = (TextView) myHolder.mFooterView.findViewById(R.id.tv_refresh_more);
        ImageView imageView = startRotate(myHolder);
        textView.setText("嘿咻嘿咻～");
        RecommendImageRecyclerAdapter adapter = ((RecommendImageRecyclerAdapter) myHolder.mGroupImageRecycler.getAdapter());
        mListener.onRefreshRegion(textView, imageView, adapter,RecommendConst.RECOMMEND);
    }

    @NonNull
    private ImageView startRotate(ViewHolder myHolder) {
        ImageView imageView = (ImageView) myHolder.mFooterView.findViewById(R.id.iv_refresh_more);
        imageView.startAnimation(mAnimation);
        return imageView;
    }


    @Override
    protected int getItemsCount() {
         return mBeanList.size()+1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final GridLayoutManager mLayoutManager;
        @Bind(R.id.header_view)
        FrameLayout mHeaderView;
        @Bind(R.id.group_image_recycler)
        RecyclerView mGroupImageRecycler;
        @Bind(R.id.footer_view)
        FrameLayout mFooterView;

        ViewHolder(View itemView) {
            super(itemView);
            mHeaderView = (FrameLayout) itemView.findViewById(R.id.header_view);
            mGroupImageRecycler = (RecyclerView) itemView.findViewById(R.id.group_image_recycler);
            mFooterView = (FrameLayout) itemView.findViewById(R.id.footer_view);
            //注意，为了方便复用，在这里注册掉统一的LayoutManager以及ItemDecoration
            mLayoutManager = new GridLayoutManager(UiUtils.getContext(), 2);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mGroupImageRecycler.setLayoutManager(mLayoutManager);
            mGroupImageRecycler.addItemDecoration(new RecommendItemDecoration());

        }
    }

    private static class RecommendItemDecoration extends RecyclerView.ItemDecoration {
        //默认间距
        private int mSpace = 15;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = mSpace+5;
            outRect.right = mSpace+5;
            outRect.bottom = mSpace;
            //只有第一个item需要增加间隔
            // Add top margin only for the first item to avoid double space between items
            if(parent.getChildPosition(view) == 0||parent.getChildPosition(view) == 1)
                outRect.top = mSpace;
        }
    }

    public List<RecommendContent.ResultBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<RecommendContent.ResultBean> beanList) {
        mBeanList = beanList;
    }

    public OnRecommendRefreshDataListener getListener() {
        return mListener;
    }
}
