package com.example.castle.csite.contrller;

import android.content.Context;

import com.example.castle.csite.cons.IdiyMessage;

import java.util.HashMap;

/**
 * @author 吴志强
 * @time 2016/9/22  14:30
 * @desc ${TODD}
 */
public class RegistController extends BaseController {

	public RegistController(Context context) {
		super(context);
	}

	@Override
	protected void handleMessage(int action, Object... values) {
		switch (action){
			case IdiyMessage.REGIST_ACTION:
				HashMap<String, String> regist = login((String) values[0], (String) values[1]);
				mListener.onModelChange(IdiyMessage.REGIST_ACTION_RESULT,regist);
				break;
		}
	}

	private HashMap<String, String> login(String name,String pwd){
		HashMap<String, String> params = new HashMap<>();
		params.put("username",name);
		params.put("pwd",pwd);
		return params;
	}
}
