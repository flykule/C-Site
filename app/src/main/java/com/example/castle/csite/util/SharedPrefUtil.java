package com.example.castle.csite.util;

import android.content.SharedPreferences;

/**
 * Created by castle on 16-8-31.
 * sharePref工具类
 */
public class SharedPrefUtil {
    public static SharedPreferences getPreferences() {
        return UiUtils.getContext().getSharedPreferences(Constants.SETTINGS_NAME, 0);
    }
}
