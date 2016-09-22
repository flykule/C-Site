package com.example.castle.csite.contrller;


import android.content.Context;

import com.example.castle.csite.db.UserDao;


/**
 * @author 吴志强
 * @time 2016/9/2  19:44
 * @desc ${TODD}
 */
public abstract class UserController extends BaseController{

	protected UserDao mDao;

	public UserController(Context context) {
		super(context);
		mDao = new UserDao(context);
	}
}
