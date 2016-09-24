package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.castle.csite.R;
import com.example.castle.csite.bean.RResult;
import com.example.castle.csite.cons.IdiyMessage;
import com.example.castle.csite.contrller.RegistController;
import com.example.castle.csite.listener.IModelChangeListener;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;

/**
 * @author 吴志强
 * @time 2016/9/22  14:27
 * @desc ${TODD}
 */
@BindLayout(id= R.layout.activity_register)
public class RegistActivity extends BaseActivity implements IModelChangeListener {

	private EditText mNameEt;
	private EditText mPwdEt;
	private EditText mSurePwdEt;
	private Button mConfirmBtn;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case IdiyMessage.REGIST_ACTION_RESULT:
					handleRegistResult(msg);
					break;
			}
		}
	};

	private void handleRegistResult(Message msg) {
		RResult result = (RResult) msg.obj;
		if (result.isSuccess()) {
			//跳转到登录
			finish();
		} else {
			tip("注册失败:"+result.getErrorMsg());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNameEt = (EditText) findViewById(R.id.username_et);
		mPwdEt = (EditText) findViewById(R.id.pwd_et);
		mSurePwdEt = (EditText) findViewById(R.id.surepwd_et);
		mConfirmBtn = (Button)findViewById(R.id.confirm_btn);
		mConfirmBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				confirmClick(v);
			}
		});

		mController = new RegistController(this);
		mController.setModelChangeListener(this);
	}

	public void confirmClick(View view){
		String name = mNameEt.getText().toString().trim();
		String pwd = mPwdEt.getText().toString().trim();
		String surePwd = mSurePwdEt.getText().toString().trim();
		if(TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(surePwd)) {
			tip("请输入完整信息!");
			return;
		}
		if(!pwd.equals(surePwd)) {
			tip("两次输入密码不一致");
			return;
		}
		mController.sendAsyncMessage(IdiyMessage.REGIST_ACTION,name,pwd);
	}

	@Override
	public void onModelChange(int action, Object... values) {
		mHandler.obtainMessage(action, values[0]).sendToTarget();
	}
}

