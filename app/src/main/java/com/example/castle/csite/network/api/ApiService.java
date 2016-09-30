package com.example.castle.csite.network.api;

import com.example.castle.csite.bean.RecommendBanner;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by castle on 16-9-8.
 * api服务类
 */
public interface ApiService {

    @POST("login")
    Observable<Object> login(@Query("username") String username, @Query("pwd") String pwd);
    @POST("regist")
    Observable<Object> regist(@Query("username") String username, @Query("pwd") String pwd);

    @GET("api/bangumi_recommend?appkey=1d8b6e7d45233436&build=426003&cursor=-1&mobi_app=android&pagesize=10&platform=android&ts=1474782271000&sign=3b9fc6d3ac5d4a326530a54e3241e8b3")
    Observable<RecommendBanner> getRecommendBanner();

}
