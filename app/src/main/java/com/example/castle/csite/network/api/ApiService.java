package com.example.castle.csite.network.api;

import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.bean.RecommendContent;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by castle on 16-9-8.
 * api服务类
 */
public interface ApiService {


    //用于得到推荐页面的Banner
    @GET("x/banner?plat=4&build=426003&channel=bili")
    Observable<RecommendBanner> getRecommendBanner();

    //用于得到推荐页面的内容
    @GET("x/show/old?appkey=1d8b6e7d45233436&build=426003&channel=bili&mobi_app=android&platform=android&screen=xxhdpi&ts=1475290047000&sign=c204bbf11276db5aa2173408751bc1ba")
    Observable<RecommendContent> getRecommendContent();

}
