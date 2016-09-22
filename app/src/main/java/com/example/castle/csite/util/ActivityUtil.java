package com.example.castle.csite.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @author 灰太狼
 * @time 2016/9/1  18:25
 * @desc Activity 的工具类
 */
public class ActivityUtil {
    public static void start(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public static void startWithoutFinish(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
        
    }


}
