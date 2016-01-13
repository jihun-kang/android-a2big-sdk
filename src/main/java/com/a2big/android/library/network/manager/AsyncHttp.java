package com.a2big.android.library.network.manager;

import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.response.ResponseManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class AsyncHttp {
	
	private ResponseManager mResponseManager;
	
	public AsyncHttp() {
		A2BigApp app = A2BigApp.getApplication();
		mResponseManager = app.getResponseManager();
	}
	
	public String requestAPI(String url, int uid, String token) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("user_id", String.valueOf(uid)));
		nameValuePairs.add(new BasicNameValuePair("login_token", token));
		return mResponseManager.analysePostResponse(url, nameValuePairs);
	}
	
	public String postRequestAPI(String url) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		return mResponseManager.analysePostResponse(url, nameValuePairs);
	}
	
	public String getRequestAPI(String url) {
		return mResponseManager.analyseGetResponse("api/version");
	}
}
