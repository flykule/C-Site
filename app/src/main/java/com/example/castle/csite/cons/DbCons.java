package com.example.castle.csite.cons;

import android.provider.BaseColumns;

/**
 * @author 吴志强
 * @time 2016/9/2  19:14
 * @desc ${TODD}
 */
public class DbCons implements BaseColumns {

	public static final String DB_NAME="csite.db";

	public static final int DB_VERSION=1;

	public static final String USER_TABLE="user";
	public static final String _NAME="name";
	public static final String _PWD="pwd";
}
