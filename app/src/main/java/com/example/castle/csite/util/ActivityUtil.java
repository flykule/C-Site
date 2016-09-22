package com.example.castle.csite.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @author 吴志强
 * @time 2016/9/1  17:25
 * @desc ${TODD}
 */
public class ActivityUtil {

	public static void start(Context c, Class clazz){
		Intent intent = new Intent(c, clazz);
		c.startActivity(intent);
		((Activity)c).finish();
	}

	public static void startWithoutFinish(Context c, Class clazz){
		Intent intent = new Intent(c, clazz);
		c.startActivity(intent);
	}
}
