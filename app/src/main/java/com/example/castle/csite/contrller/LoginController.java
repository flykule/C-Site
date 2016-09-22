package com.example.castle.csite.contrller;

import android.content.Context;

import com.example.castle.csite.bean.UserAndPwd;
import com.example.castle.csite.cons.IdiyMessage;
import com.example.castle.csite.util.AESUtils;

import java.util.HashMap;

/**
 * @author 吴志强
 * @time 2016/9/22  12:00
 * @desc ${TODD}
 */
public class LoginController extends UserController{

	public LoginController(Context context) {
		super(context);
	}

	@Override
	protected void handleMessage(int action, Object... values) {
		switch (action){
			case IdiyMessage.LOGIN_ACTION:
				HashMap<String, String> login = login((String) values[0], (String) values[1]);
				mListener.onModelChange(IdiyMessage.LOGIN_ACTION_RESULT,login);
				break;
			case IdiyMessage.SAVE_USER_ACTION:
				boolean saveUser2DB = saveUser2DB((String) values[0], (String) values[1]);
				mListener.onModelChange(IdiyMessage.SAVE_USER_ACTION_RESULT,saveUser2DB);
				break;
			case IdiyMessage.QUERY_USER_ACTION:
				mListener.onModelChange(IdiyMessage.QUERY_USER_ACTION_RESULT,queryUserFromDB());
				break;
		}
	}

	public UserAndPwd queryUserFromDB() {
		UserAndPwd bean = mDao.queryUserTavle();
		if(bean!=null) {
			try {
				return new UserAndPwd(AESUtils.decrypt(bean.name),AESUtils.decrypt(bean.pwd));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private boolean saveUser2DB(String name,String pwd){
		try {
			name= AESUtils.encrypt(name);
			pwd=AESUtils.encrypt(pwd);
			mDao.cleanUserTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDao.saveUser(name,pwd);
	}

	private HashMap<String, String> login(String name,String pwd){
		HashMap<String, String> params = new HashMap<>();
		params.put("username",name);
		params.put("pwd",pwd);
		return params;
	}

}
