package com.a2big.android.library.objecttype;

public abstract class AbstractObject {
	protected String TAG_DATA = "data";
	protected String TAG_SUCCESS = "success";
	protected String TAG_MSG = "msg";
	protected String TAG_CODE = "code";

	protected String TAG_RESULT = "results";
	protected String TAG_ERROR = "errors";

	protected String mErrorMessage = "";
	protected int mCode;
	
	public abstract boolean onResponseListener(String pResponse);
	
	public String getErrorMessage() {
		return mErrorMessage;
	}
	
	public int getCode() {
		return mCode;
	}
}
