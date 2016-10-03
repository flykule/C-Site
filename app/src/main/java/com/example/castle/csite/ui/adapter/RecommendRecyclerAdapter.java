package com.example.castle.csite.ui.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.ui.base.BaseRecyclerAdapter;
import com.example.castle.csite.util.LogUtils;
import com.example.castle.csite.util.UiUtils;

import java.util.HashSet;
import java.util.List;

import butterknife.Bind;

/**
 * Created by castle on 16-10-1.
 * 推荐页面RecyclerView适配器
 */
public class RecommendRecyclerAdapter extends BaseRecyclerAdapter {

    private final RecommendItemDecoration mItemDecoration;
    private List<RecommendContent.ResultBean> mBeanList;
    private LayoutInflater mInflater;

    public RecommendRecyclerAdapter(List<RecommendContent.ResultBean> beanList) {
        mBeanList = beanList;
        mItemDecoration = new RecommendItemDecoration();
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder getNormalViewHolder(ViewGroup parent) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.item_recommend_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void bindNormal(RecyclerView.ViewHolder holder,int position) {
        ViewHolder myHolder = (ViewHolder) holder;
        final RecommendContent.ResultBean resultBean = mBeanList.get(position-1);
        //根据类型进行区分
        switch (resultBean.getType()) {
            case "recommend":
                View hotHead = mInflater.inflate(R.layout.header_hot, null);
                myHolder.mHeaderView.addView(hotHead);
                myHolder.mHeaderView.setVisibility(View.VISIBLE);
                break;
            default:
                myHolder.mHeaderView.setVisibility(View.GONE);
                break;
        }

        List<RecommendContent.ResultBean.BodyBean> body = resultBean.getBody();
        RecommendImageRecyclerAdapter adapter = new RecommendImageRecyclerAdapter(body);
        myHolder.mGroupImageRecycler.setAdapter(adapter);
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
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(UiUtils.getContext(), 2);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mGroupImageRecycler.setLayoutManager(gridLayoutManager);
            mGroupImageRecycler.addItemDecoration(new RecommendItemDecoration());
            // gridLayoutManager  布局管理器
            /*gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    //如果是第一个(添加HeaderView)   还有就是最后一个(FooterView)
                    return resultBean.getType().equals("weblink") ? gridLayoutManager.getSpanCount() : 1;
                }
            });*/
        }
    }

    static class RecommendItemDecoration extends RecyclerView.ItemDecoration {
        //默认间距
        private int mSpace = 15;
        private static HashSet<Integer> mList = new HashSet<>();

        private int mHorizontalSpacing = 0;
        private int mVerticalSpacing = 0;
        private boolean mIncludeEdge = false;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int position = parent.getChildAdapterPosition(view);
            //if (mList.contains(position)) return;
            LogUtils.d("当前测量："+position);
            LogUtils.d(state+"");
            LogUtils.d("当前测量子位置："+params.getViewAdapterPosition());
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
            //只有第一个item需要增加间隔
            // Add top margin only for the first item to avoid double space between items
            if(parent.getChildPosition(view) == 0||parent.getChildPosition(view) == 1)
                outRect.top = mSpace;
            mList.add(position);
        }
    }

}
