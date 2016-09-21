package com.example.castle.csite.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by castle on 16-8-14.
 * json工具类，用于解析json，提供通用性
 */
public class JsonUtil {
    private static Gson mGson;


    public static <T> T parse(String jsonStr, Class<T> tClass) {
        getGson();
        if (TextUtils.isEmpty(jsonStr)||tClass==null) {
            return null;
        }
        return mGson.fromJson(jsonStr, tClass);
    }

    private static Gson getGson() {
        if (mGson == null) {
            synchronized (Gson.class) {
                if (mGson == null) {
                    mGson = new Gson();
                }
            }
        }
        return mGson;
    }

    public static <T> List<T> parseByType(String jsonStr, Class<T[]> tClass) {
        getGson();
        if (TextUtils.isEmpty(jsonStr)||tClass==null) {
            return null;
        }
        T[] arr = new Gson().fromJson(jsonStr, tClass);
        List<T> beanList = new ArrayList<>();
        beanList.addAll(Arrays.asList(arr));
        return beanList;
    }



    public static <T> List<T> parseArray(@NonNull String jsonStr, @NonNull Class<T> tClass) {
        getGson();
        List<Map> tmpList = parseByType(jsonStr, Map[].class);
        List<T> resultList = new ArrayList<>(tmpList.size());
        for(Map map:tmpList){
            String tmpJson = mGson.toJson(map);
            resultList.add(parse(tmpJson,tClass));
        }
        return resultList;
    }
}
