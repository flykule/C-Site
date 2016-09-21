package com.example.castle.csite.network.api;

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


}
