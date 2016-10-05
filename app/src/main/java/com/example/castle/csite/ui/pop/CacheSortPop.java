package com.example.castle.csite.ui.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.castle.csite.R;
import com.example.castle.csite.listener.ICacheSortPopListener;
import com.example.castle.csite.util.ToastUtil;
import com.example.castle.csite.util.UiUtils;

/**
 * @author liqin
 * @time 2016/9/22  19:21
 * @desc ${TODD}
 */
public class CacheSortPop extends BasePopwindow implements View.OnClickListener {

    private ICacheSortPopListener mListener;

    public CacheSortPop(Context c) {
        super(c);
    }

    public void setOnSortChangeListener(ICacheSortPopListener listener) {
        mListener = listener;
    }

    @Override
    protected void initViews(Context c) {
        //contentView 弹出框的显示内容
        // width, height 弹出框的大小
        View contentView = LayoutInflater.from(c).inflate(R.layout.cahe_sort_pop_layout, null);
       
        contentView.findViewById(R.id.title_sort).setOnClickListener(this);
        contentView.findViewById(R.id.time_sort).setOnClickListener(this);
        contentView.findViewById(R.id.episode_sort).setOnClickListener(this);

        mPopupWindow = new PopupWindow(contentView, UiUtils.dip2px(100), -2);

        //1.让mPopupWindow内部的控件获取焦点
        mPopupWindow.setFocusable(true);
        //2.mPopupWindow内部获取焦点后 外部的所有控件就失去了焦点
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //3.如果不马上显示PopupWindow 一般建议刷新界面
        mPopupWindow.update();
    }


    @Override
    public void onClick(View v) {
        if (mListener != null) {
            switch (v.getId()) {
                case R.id.title_sort:
                    mListener.onSortChaneged(ICacheSortPopListener.TITLE_SORT);
                    ToastUtil.showLong("按标题");
                    break;
                case R.id.time_sort:
                    mListener.onSortChaneged(ICacheSortPopListener.TIME_SORT);
                    ToastUtil.showLong("按时间");
                    break;
                case R.id.episode_sort:
                    mListener.onSortChaneged(ICacheSortPopListener.EPISODE_SORT);
                    ToastUtil.showLong("按剧集");
                    break;
            }
        }
        onDismiss();
    }

}
