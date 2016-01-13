package com.a2big.android.library.utils;

import android.app.Activity;
import android.util.Log;

public class DevLog {
	public static final boolean DEBUG = true;
	public static final String TAG = "a2big";
	
	public static void defaultLogging(String pMessage) {
		if(DEBUG && pMessage != null)
			Log.d(TAG, pMessage);
	}
	
	public static void Logging(String pTag, String pMessage) {
		if(DEBUG && pTag != null && pMessage != null)
			Log.d(pTag, pMessage);
	}
	
	public static void Logging(Activity pActivity, String pMessage) {
		if(DEBUG && pActivity != null && pMessage != null)
			Log.d(pActivity.getLocalClassName(), pMessage);
	}
	
	public static void LoggingError(String pTag, String pMessage) {
		if(DEBUG && pTag != null && pMessage != null)
			Log.e(pTag, pMessage);
	}
	
}
