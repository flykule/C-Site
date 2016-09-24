package com.example.castle.csite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.castle.csite.cons.DbCons;


/**
 * @author 吴志强
 * @time 2016/9/2  19:12
 * @desc ${TODD}
 */
public class DbOpenHelper extends SQLiteOpenHelper {

	public DbOpenHelper(Context context) {
		super(context, DbCons.DB_NAME, null, DbCons.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table "+DbCons.USER_TABLE+"(" +
		DbCons._ID+" integer primary key autoincrement,"+
		DbCons._NAME+" text,"+
		DbCons._PWD+" text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
