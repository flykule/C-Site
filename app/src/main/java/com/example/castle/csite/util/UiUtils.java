package com.example.castle.csite.util;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.util.DisplayMetrics;


/**
 * Created by castle on 16-8-19.
 * ui工具类
 */
public class UiUtils {
    /**
     * 得到上下文
     * @return 应用上下文
     */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /**
     * 得到资源
     * @return 资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     *
     * @param stringRes  字符串资源id
     * @return 字符串
     */
    public static String getString( @StringRes int stringRes) {
        return getResources().getString(stringRes);
    }

    /**
     * @param intRes 需求的资源id
     * @return 指定integer
     */
    public static Integer getInteger(@IntegerRes int intRes) {
        return getResources().getInteger(intRes);
    }

    /**
     * @param intRes 需求的资源id
     * @return 指定的dimension
     */
    public static float getDimension(@DimenRes int intRes) {
        return getResources().getDimension(intRes);
    }

    /**
     *
     * @param colorRes 颜色资源id
     * @return 指定颜色
     */
    public static int getColor(@ColorRes int colorRes) {
        return getResources().getColor(colorRes);
    }

    /**
     * @param drawableRes 图片id
     * @return 指定图片
     */
    public static Drawable getDrawable(@DrawableRes int drawableRes) {
        return getResources().getDrawable(drawableRes);
    }

    /**
     *
     * @param arrayRes 字符串资源id
     * @return 字符串数组
     */
    public static  String[] getArray(@ArrayRes int arrayRes) {
        return getResources().getStringArray(arrayRes);
    }

    /**
     * 得到包名
     * @return 包名
     */
    public static String getPackageName() {
        return getContext().getPackageName();
    }

    /**
     *
     * @return 主线程的ID
     */
    public static long getMainThreadId() {
        return MyApplication.getMainThread();
    }

    /**
     *
     * @return 得到Handler
     */
    public static Handler getHandler() {
        return MyApplication.getHandler();
    }

    /**
     * 安全的执行一个task
     * @param task 要执行的task
     */
    public static void postTastSafely(Runnable task) {
        long curThreadId = Process.myTid();
        long mainThreadId = getMainThreadId();
        //判断当前是否在主线程
        if (curThreadId == mainThreadId) {
            //在主线程，直接执行任务
            task.run();
        } else {
            //交给主线程的handler去进行
            getHandler().post(task);
        }
    }

    /**
     * 实现dp和px的转化
     * @param dip dp
     * @return px
     */
    public static int dip2px(int dip) {
        float density = getResources().getDisplayMetrics().density;
        float ppi = getResources().getDisplayMetrics().densityDpi;
        return (int) (dip * density + .5f);
    }

    /**
     * 实现px和dp的转化
     * @param px px
     * @return dp
     */
    public static int px2dip(int px) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (px / density + .5f);
    }


    /**
     * 得到设备高度
     * @return 当前设备高度
     */
    public static int getDeviceHeight() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(displayMetrics.heightPixels / displayMetrics.density);
    }

    /**
     * 得到设备宽度
     * @return 当前设备宽度
     */
    public static int getDeviceWidth() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(displayMetrics.widthPixels / displayMetrics.density);
    }

}
