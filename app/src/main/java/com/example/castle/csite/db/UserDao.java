package com.example.castle.csite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.castle.csite.bean.UserAndPwd;
import com.example.castle.csite.cons.DbCons;


/**
 * @author 吴志强
 * @time 2016/9/2  19:19
 * @desc ${TODD}
 */
public class UserDao {

	private DbOpenHelper mHelper;

	public UserDao(Context context) {
		mHelper = new DbOpenHelper(context);
	}

	public boolean saveUser(String name, String pwd){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DbCons._NAME,name);
		values.put(DbCons._PWD,pwd);
		long insert = db.insert(DbCons.USER_TABLE, null, values);
		return insert!=-1;
	}

	public boolean cleanUserTable(){
		SQLiteDatabase db = mHelper.getWritableDatabase();
		int deleteRows = db.delete(DbCons.USER_TABLE, null, null);
		return deleteRows>0;
	}

	public UserAndPwd queryUserTavle(){
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Cursor cursor = db.query(DbCons.USER_TABLE, new String[]{DbCons._NAME, DbCons._PWD}, null, null, null, null, null);
		while (cursor.moveToNext()){
			String name = cursor.getString(0);
			String pwd = cursor.getString(1);
			return new UserAndPwd(name,pwd);
		}
		return null;
	}
}
