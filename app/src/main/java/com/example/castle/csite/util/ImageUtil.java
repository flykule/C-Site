package com.example.castle.csite.util;

import android.graphics.BitmapFactory;

/**
 * Created by castle on 16-8-31.
 * 图片工具类
 */
public class ImageUtil {


    /**
     * 计算图片缩放比，以2的倍数计算
     *
     * @param options   参数项
     * @param reqWidth  需求的宽度
     * @param reqHeight 需求的高度
     * @return 缩放比
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
