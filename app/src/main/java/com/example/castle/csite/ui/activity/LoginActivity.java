package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.castle.csite.R;
import com.example.castle.csite.bean.RResult;
import com.example.castle.csite.bean.UserAndPwd;
import com.example.castle.csite.cons.IdiyMessage;
import com.example.castle.csite.contrller.LoginController;
import com.example.castle.csite.listener.IModelChangeListener;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.util.ActivityUtil;
import com.example.castle.csite.util.MyApplication;
import com.example.castle.csite.view.BindLayout;

import java.lang.ref.WeakReference;

/**
 * @author 吴志强
 * @time 2016/9/21  21:18
 * @desc ${TODD}
 */
@BindLayout(id= R.layout.activity_login)
public class LoginActivity extends BaseActivity implements IModelChangeListener, View.OnClickListener {
	private TextView mLoginTv;
	private ImageView mArrowIv;
	private TextView mForgetPwdTv;
	private EditText mUsernameEt;
	private EditText mPwdEt;
	private Button mLoginBtn;
	private Button mRegisterBtn;
	private LoginController mController;
	private RelativeLayout mInvisiableRl;
	private RelativeLayout mVisiableRl;

	private ImageView mIc22iV;
	private ImageView mIc33iV;

	private MyHandler mMyHandler =new MyHandler(this);
	private String mName;
	private String mPwd;

	public static class MyHandler extends Handler{

		private WeakReference<LoginActivity> mWeakReference;

		public MyHandler(LoginActivity activity) {
			mWeakReference=new WeakReference<LoginActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what){
				case IdiyMessage.LOGIN_ACTION_RESULT:
					handleLoginResult(msg);
					break;
				case IdiyMessage.SAVE_USER_ACTION_RESULT:
					handleSaveUserInfo((Boolean)msg.obj);
					break;
				case IdiyMessage.QUERY_USER_ACTION_RESULT:
					handleQueryUserInfo((UserAndPwd)msg.obj);
					break;
			}
		}

		private void handleQueryUserInfo(UserAndPwd bean) {
			if(bean!=null) {
				mWeakReference.get().mUsernameEt.setText(bean.name);
				mWeakReference.get().mPwdEt.setText(bean.pwd);
			}
		}

		private void handleSaveUserInfo(Boolean isSaved) {
			if(isSaved) {
				ActivityUtil.start(mWeakReference.get(),MainActivity.class);
			}else {
				mWeakReference.get().toastMsg("保存用户信息异常");
			}
		}

		private void handleLoginResult(Message msg) {
			RResult result = (RResult) msg.obj;
			if(result.isSuccess()) {
				if(mWeakReference!=null&&mWeakReference.get()!=null) {
					UserAndPwd login = JSON.parseObject(result.getResult(), UserAndPwd.class);
					// 1. 保存用户的信息到Application
					((MyApplication)(mWeakReference.get().getApplication())).mUserInfo=login;
					// 2. 保存账号还有密码到数据库
					mWeakReference.get().mController.sendAsyncMessage(IdiyMessage.SAVE_USER_ACTION,
							mWeakReference.get().mUsernameEt,mWeakReference.get().mPwdEt);
				}
			}else {
				mWeakReference.get().toastMsg("登陆失败:"+result.getErrorMsg());
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mMyHandler.removeCallbacksAndMessages(null);
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initController();
		initUI();
		mController.sendAsyncMessage(IdiyMessage.QUERY_USER_ACTION,0);
	}

	private void initController() {
		mController=new LoginController(this);
		mController.setModelChangeListener(this);
	}

	private void initUI() {
		mIc22iV = (ImageView)findViewById(R.id.ic22_iv);
		mIc33iV = (ImageView)findViewById(R.id.ic33_iv);

		mInvisiableRl = (RelativeLayout)findViewById(R.id.invisible_rl);
		mVisiableRl = (RelativeLayout)findViewById(R.id.visible_rl);

		mArrowIv=(ImageView) findViewById(R.id.arrow_img);
		mArrowIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityUtil.start(LoginActivity.this,MainActivity.class);
			}
		});
		mLoginTv= (TextView)findViewById(R.id.login_tv);
		mForgetPwdTv = (TextView) findViewById(R.id.forget_pwd_tv);
		mUsernameEt = (EditText) findViewById(R.id.username_et);
		mPwdEt = (EditText) findViewById(R.id.pwd_et);
		mPwdEt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mIc22iV.setBackgroundResource(R.drawable.ic_22_hide);
				mIc33iV.setBackgroundResource(R.drawable.ic_33_hide);
			}
		});
		mLoginBtn = (Button) findViewById(R.id.login_btn);
		mLoginBtn.setOnClickListener(this);
		mRegisterBtn = (Button) findViewById(R.id.register_btn);
	}

	@Override
	public void onModelChange(int action, Object... values) {
		mMyHandler.obtainMessage(action,values[0]).sendToTarget();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.login_btn:
				loginClick(v);
				break;
			case R.id.register_btn:
				registClick(v);
				break;
		}
	}


	public void loginClick(View view){
		mName = mUsernameEt.getText().toString().trim();
		mPwd = mPwdEt.getText().toString().trim();
		if(TextUtils.isEmpty(mName)||TextUtils.isEmpty(mPwd)) {
			toastMsg("账号或者密码不能为空");
			return;
		}
		// 登录 告诉LoginController 想做一个登录请求(需要携带登录的参数)
		mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, mName, mPwd);
	}

	public void registClick(View view){
		ActivityUtil.startWithoutFinish(this,RegistActivity.class);
	}
}
