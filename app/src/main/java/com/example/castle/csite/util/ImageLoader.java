package com.example.castle.csite.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by HugoXie on 16/4/30.
 *
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * 图片加载类,统一适配(方便换库,方便管理)
 */
public class  ImageLoader {

    public static void load(Context context, @DrawableRes int imageRes, ImageView view) {
        Glide.with(context).load(imageRes).into(view);
    }

    /**
     * 提供多种重载
     *
     * @param imageUri 图片uri
     * @param view     imageView
     */
    public static void load(Context context,Uri imageUri, ImageView view) {
        Glide.with(context).load(imageUri).into(view);
    }

    /**
     * 清除缓存
     * @param context 上下文
     */
    public static void clear(Context context) {
        Glide.get(context).clearMemory();
    }


}
