package com.example.castle.csite.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * toast消息工具类
 */
public class ToastUtil {

    public static void showShort(String msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //提供重载
    public static void showShort(@StringRes int msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(String msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showLong(@StringRes int msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
