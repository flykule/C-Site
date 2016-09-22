package com.example.castle.csite.contrller;


import android.app.Activity;
import android.content.Context;

import com.a520it.jd.JDApplication;
import com.a520it.jd.db.UserDao;

/**
 * @author 吴志强
 * @time 2016/9/2  19:44
 * @desc ${TODD}
 */
public abstract class UserController extends BaseController{

	protected UserDao mDao;
	protected long mUserId;

	public UserController(Context context) {
		super(context);
		mDao = new UserDao(context);
		JDApplication jdApp= (JDApplication) ((Activity)context).getApplication();
		if(jdApp.mUserInfo!=null) {
		    mUserId=jdApp.mUserInfo.getId();
		}
	}
}
