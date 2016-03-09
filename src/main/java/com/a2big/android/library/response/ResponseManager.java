package com.a2big.android.library.response;


import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.network.manager.NetworkManager;
import com.a2big.android.library.utils.DevLog;

import org.apache.http.NameValuePair;

import java.util.List;

public class ResponseManager {
	private NetworkManager mNetworkManager;
	
	public ResponseManager() {
		A2BigApp app = A2BigApp.getApplication();
		mNetworkManager = app.getNetworkManager();
	}
	
	public String analyseGetResponse(String pUrl) {
		DevLog.defaultLogging("JH analyseGetResponse 1111");

		mNetworkManager.connect(mNetworkManager.getServerMode());
		return mNetworkManager.getAndGetString(pUrl);
	}
	
	public String analysePostResponse(String pUrl, List<NameValuePair> pArgs) {
		DevLog.defaultLogging("JH analysePostResponse 222");

		mNetworkManager.connect(mNetworkManager.getServerMode());
		return mNetworkManager.postAndGetString(pUrl, pArgs);
	}


	public String uploadPostResponse(String pUrl,String email, String pTime,
									 String pPhoto, List<NameValuePair> pArgs) {
		mNetworkManager.connect(mNetworkManager.getServerMode());
		DevLog.defaultLogging("uploadPostResponse>>{ mNetworkManager.uploadImageFile(pUrl,pPhoto, pArgs }");


		return mNetworkManager.uploadImageFile(pUrl, pPhoto, pArgs);
	}

	public String imageUploadPostResponse(String pUrl, List<NameValuePair> pArgs) {
		DevLog.defaultLogging("uploadPostResponse>>{ mNetworkManager.imageUploadPostResponse,,,,,,,");

		mNetworkManager.connect(mNetworkManager.getServerMode());
		return mNetworkManager.uploadUserPhoto(pUrl, pArgs);
	}


	public String roadCastImageUploadPostResponse(String pUrl, List<NameValuePair> pArgs) {
		DevLog.defaultLogging("uploadPostResponse>>{ mNetworkManager.roadCastImageUploadPostResponse,,,,,,,");

		mNetworkManager.connect(mNetworkManager.getServerMode());
		return mNetworkManager.uploadRoadCastPhoto(pUrl, pArgs);
	}


	public String loginWithProfilePostResponse(String pUrl, List<NameValuePair> pArgs) {
		DevLog.defaultLogging("uploadPostResponse>>{ mNetworkManager.loginWithProfilePostResponse,,,,,,,");

		mNetworkManager.connect(mNetworkManager.getServerMode());
		return mNetworkManager.loginWithProfile(pUrl, pArgs);
	}



}
