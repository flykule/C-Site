package com.example.castle.csite.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by castle on 16-10-2.
 * 自定义RecyclerView控件
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //自定义View类型
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    //头部控件
    private View mHeaderView;
    //尾部控件
    private View mFooterView;

    private static final int TYPE_FOOTER = 2;

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new ViewHolder(mHeaderView);
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }
        return getNormalViewHolder(parent);
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        if (mFooterView == null) return TYPE_NORMAL;
        if (mHeaderView != null && position +1 == getItemCount()) return TYPE_FOOTER;
        if (mHeaderView == null && position  == getItemCount()) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    //得到ViewHolder，希望被重写
    @NonNull
    protected abstract RecyclerView.ViewHolder getNormalViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_NORMAL:
                //动态设置正常item大小，避免多余空隙
                LinearLayoutCompat.LayoutParams params =
                        new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT);
                holder.itemView.setLayoutParams(params);
                bindNormal(holder,position);
                break;
        }
    }

    protected abstract void bindNormal(RecyclerView.ViewHolder holder,int position);

    @Override
    public int getItemCount() {
        return getItemsCount();
    }

    //返回item数量，由父类实现
    protected abstract int getItemsCount();

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
