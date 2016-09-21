package com.example.castle.csite.util;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.castle.csite.network.api.ApiService;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by castle on 16-8-19.
 * 我的application应用类，在这里进行必要的初始化
 */
public class MyApplication extends Application{

    private static Context mContext;
    private static Handler mHandler;
    private static int mMainThread;

    private HashMap<String, String> mProtocolCacheMap = new HashMap<>();

    public  ApiService getService() {
        return mService;
    }

    private static ApiService mService;

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHandler() {
        return mHandler;
    }

    public static int getMainThread() {
        return mMainThread;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mHandler = new Handler();
        /**
         * tid:线程id
         * uid:用户id
         * pid:进程id
         */
        mMainThread = android.os.Process.myTid();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        mService = retrofit.create(ApiService.class);
    }

    public HashMap<String, String> getProtocolCacheMap() {
        return mProtocolCacheMap;
    }
}
