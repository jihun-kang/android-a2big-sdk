package com.a2big.android.library.adapters.Setting;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.init.Constants;
import com.a2big.android.library.utils.Utils;

public class SettingManager {
	private static SharedPreferences mSharedPreferences = null;
	public static final String PREFERENCE_LOGIN = "preference_login";
	public static final String PREFERENCE_TOKEN = "preference_token";

	public static final String PREFERENCE_EMAIL = "preference_email";
	public static final String PREFERENCE_USER_ID = "preference_user_id";
	public static final String PREFERENCE_USER_NAME = "preference_user_name";
	public static final String PREFERENCE_USER_TYPE = "preference_user_type";
	public static final String PREFERENCE_USER_PROFILE = "preference_user_profile";

	public static final String PREFERENCE_REG_ID = "preference_reg_id";
	public static final String PREFERENCE_ANDROID_VERSION = "preference_android_version";


	//outer
	public static final String PREFERENCE_SHOW_EVENT_DAY = "preference_show_event_day";


	private Utils mUtils;

	public SharedPreferences getSharedPreferences() {
		if(mSharedPreferences == null)
			mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(A2BigApp.getApplication().getApplicationContext());

		mUtils = A2BigApp.getApplication().getUtils();

		return mSharedPreferences;
	}

	public void setLogin(boolean pFlag) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putBoolean(PREFERENCE_LOGIN, pFlag);
		editor.commit();
	}

	public boolean getLogin() {
		return getSharedPreferences().getBoolean(PREFERENCE_LOGIN, false);
	}

	public void setUserName(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_USER_NAME, pStr);
		editor.commit();
	}
	public void setUserProfile(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_USER_PROFILE, pStr);
		editor.commit();
	}
	public String getUserProfile() {
		return getSharedPreferences().getString(PREFERENCE_USER_PROFILE, Constants.EMPTY);
	}
	public void setUserType(int iValue) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putInt(PREFERENCE_USER_TYPE, iValue);
		editor.commit();
	}

	public void setToken(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_TOKEN, pStr);
		editor.commit();
	}

	public String getToken() {
		return getSharedPreferences().getString(PREFERENCE_TOKEN, Constants.EMPTY);
	}

	public void setEmail(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_EMAIL, pStr);
		editor.commit();
	}

	public String getEmail() {
		return getSharedPreferences().getString(PREFERENCE_EMAIL, Constants.EMPTY);
	}


	public void setUid(int pValue) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putInt(PREFERENCE_USER_ID, pValue);
		editor.commit();
	}

	public int getUid() {
		return getSharedPreferences().getInt(PREFERENCE_USER_ID, Constants.ZERO);
	}

	//public int getUserType() {
	//	return getSharedPreferences().getInt(PREFERENCE_USER_TYPE, Constants.LOGOUT);
	//}

	public String getMarketVersion() {
		return getSharedPreferences().getString(PREFERENCE_ANDROID_VERSION, mUtils.getAppVersion());
	}

	public void setMarketVersion(String pValue) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_ANDROID_VERSION, pValue);
		editor.commit();
	}

	public String getGegId() {
		return getSharedPreferences().getString(PREFERENCE_REG_ID, Constants.EMPTY);
	}

	public void setGegId(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_REG_ID, pStr);
		editor.commit();
	}


	public void logout() {
		setLogin(false);
		setToken(Constants.EMPTY);
		setUid(Constants.ZERO);
		setMarketVersion(mUtils.getAppVersion());
	}



	///////////////

	public void setShowEventDay(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SHOW_EVENT_DAY, pStr);
		editor.commit();
	}

	public String getShowEventDay() {
		return getSharedPreferences().getString(PREFERENCE_SHOW_EVENT_DAY, Constants.EMPTY);
	}

}
