package com.example.castle.csite.ui.pop;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * @author liqin
 * @time 2016/9/22  19:22
 * @desc ${TODD}
 */
public abstract class BasePopwindow {

    protected Context mContext;

    public BasePopwindow(Context c) {
        mContext=c;
        initViews(c);
    }

    protected PopupWindow mPopupWindow;

    protected abstract void initViews(Context c);

    //1.在某个控件周围显示
    //2.在某个容器里面显示
    public void onShow(View anchorView) {
        mPopupWindow.showAsDropDown(anchorView);
    }

    public void onDismiss() {
        mPopupWindow.dismiss();
    }

}
