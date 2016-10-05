package com.example.castle.csite.network.api;

import com.example.castle.csite.bean.RecommendBanner;
import com.example.castle.csite.bean.RecommendContent;
import com.example.castle.csite.bean.RecommendLiveRegion;
import com.example.castle.csite.bean.RecommendRegion;

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
    //用于得到热门推荐块的内容
    @GET("x/show/hot/old?platform=android&device=&build=426003&ts=1475481371000&access_key=")
    Observable<RecommendRegion> getRecommendRegion();
    //用于得到热门直播块的内容
    @GET("x/show/live?appkey=1d8b6e7d45233436&build=426003&channel=bili&mobi_app=android&plat=0&platform=android&rand=4&ts=1475574875000&sign=4c01d47591ecc222140dd53f045854cc")
    Observable<RecommendLiveRegion> getRecommendLiveRegion();

}
