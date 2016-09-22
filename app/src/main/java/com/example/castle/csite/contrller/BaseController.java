package com.example.castle.csite.contrller;

import android.content.Context;

import com.example.castle.csite.listener.IModelChangeListener;


/**
 * @author 吴志强
 * @time 2016/9/1  17:18
 * @desc ${TODD}
 */
public abstract class BaseController {

	protected Context mContext;
	protected IModelChangeListener mListener;

	public void setModelChangeListener(IModelChangeListener listener) {
		mListener = listener;
	}

	public BaseController(Context context){
		mContext=context;
	}

	public void sendAsyncMessage(final int action, final Object... values){
		new Thread(){
		    public void run(){
		        handleMessage(action,values);
		    }
		}.start();
	}

	public void sendMessage(final int action, final Object... values){
		handleMessage(action,values);
	}

	protected abstract void handleMessage(int action, Object... values);


}
