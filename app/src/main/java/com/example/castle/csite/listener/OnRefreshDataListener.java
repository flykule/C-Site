package com.example.castle.csite.listener;

/**
 * Created by castle on 16-10-3.
 * 当需要刷新数据时实现的接口
 */
public interface OnRefreshDataListener<T> {

    //更新数据的方法
    T OnRefreshData();
}
