package com.example.castle.csite.bean;

/**
 * @author 吴志强
 * @time 2016/9/1  17:19
 * @desc ${TODD}
 */
public class RResult {
	private String errorMsg;
	private String result;
	private boolean success;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
