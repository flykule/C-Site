package com.example.castle.csite.listener;

/**
 * @author liqin
 * @time 2016/9/23  16:34
 * @desc ${TODD}
 */
public interface ICacheSortPopListener {

    public static final int TITLE_SORT=1;
    public static final int TIME_SORT=2;
    public static final int EPISODE_SORT=3;

    public void onSortChaneged(int sort);
}
