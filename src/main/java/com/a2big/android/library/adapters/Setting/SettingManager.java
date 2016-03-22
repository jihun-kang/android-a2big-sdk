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

	public static final String PREFERENCE_BOOKING_DOC_ID = "preference_booking_doc_id";

	//outer
	public static final String PREFERENCE_SHOW_EVENT_DAY = "preference_show_event_day";
	public static final String PREFERENCE_GPS_CUR_LAT = "preference_gps_cur_lat";
	public static final String PREFERENCE_GPS_CUR_LONG = "preference_gps_cur_long";
	public static final String PREFERENCE_GPS_CUR_ADDR = "preference_gps_cur_addr";
	public static final String PREFERENCE_GPS_CUR_ADDR_LAST = "preference_gps_cur_addr_last";

	public static final String PREFERENCE_SELECT_ADDR1 = "preference_select_addr1";
	public static final String PREFERENCE_SELECT_ADDR2 = "preference_select_addr2";
	public static final String PREFERENCE_SELECT_ADDR3 = "preference_select_addr3";
	public static final String PREFERENCE_SELECT_FULL_ADDR = "preference_select_full_addr";


	public static final String PREFERENCE_SELECT_LATITUDE = "preference_select_lat";
	public static final String PREFERENCE_SELECT_LONGITUDE = "preference_select_long";

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

	public String getUserName() {
		return getSharedPreferences().getString(PREFERENCE_USER_NAME, Constants.EMPTY);
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


	public String getBookingDocId() {
		return getSharedPreferences().getString(PREFERENCE_BOOKING_DOC_ID, null);
	}

	public void setBookingDocId(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_BOOKING_DOC_ID, pStr);
		editor.commit();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	// outer
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setShowEventDay(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SHOW_EVENT_DAY, pStr);
		editor.commit();
	}

	public String getShowEventDay() {
		return getSharedPreferences().getString(PREFERENCE_SHOW_EVENT_DAY, Constants.EMPTY);
	}


	public void setCurGpsLat(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_GPS_CUR_LAT, pStr);
		editor.commit();
	}

	public String getCurGpsLat() {
		return getSharedPreferences().getString(PREFERENCE_GPS_CUR_LAT, Constants.EMPTY);
	}

	public void setCurGpsLong(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_GPS_CUR_LONG, pStr);
		editor.commit();
	}

	public String getCurGpsLong() {
		return getSharedPreferences().getString(PREFERENCE_GPS_CUR_LONG, Constants.EMPTY);
	}

	public void setCurGpsAddr(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_GPS_CUR_ADDR, pStr);
		editor.commit();
	}

	public String getCurGpsAddr() {
		return getSharedPreferences().getString(PREFERENCE_GPS_CUR_ADDR, Constants.EMPTY);
	}

	public void setCurGpsAddrLoat(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_GPS_CUR_ADDR_LAST, pStr);
		editor.commit();
	}

	public String getCurGpsAddrLoat() {
		return getSharedPreferences().getString(PREFERENCE_GPS_CUR_ADDR_LAST, Constants.EMPTY);
	}



	public void setSelectAddr1(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SELECT_ADDR1, pStr);
		editor.commit();
	}

	public void setSelectAddr2(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SELECT_ADDR2, pStr);
		editor.commit();
	}


	public void setSelectAddr3(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SELECT_ADDR3, pStr);
		editor.commit();
	}



	public void setSelectLatitude(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SELECT_LATITUDE, pStr);
		editor.commit();
	}

	public void setSelectLongitude(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SELECT_LONGITUDE, pStr);
		editor.commit();
	}

	public void setSelectFullAddr(String pStr) {
		SharedPreferences.Editor editor = getSharedPreferences().edit();
		editor.putString(PREFERENCE_SELECT_FULL_ADDR, pStr);
		editor.commit();
	}


	public String getSelectFullAddr() {
		return getSharedPreferences().getString(PREFERENCE_SELECT_FULL_ADDR, Constants.EMPTY);
	}

	public String getSelectAddr1() {
		return getSharedPreferences().getString(PREFERENCE_SELECT_ADDR1, Constants.EMPTY);
	}

	public String getSelectAddr2() {
		return getSharedPreferences().getString(PREFERENCE_SELECT_ADDR2, Constants.EMPTY);
	}

	public String getSelectAddr3() {
		return getSharedPreferences().getString(PREFERENCE_SELECT_ADDR3, Constants.EMPTY);
	}

	public String getSelectLatitude() {
		return getSharedPreferences().getString(PREFERENCE_SELECT_LATITUDE, Constants.EMPTY);
	}

	public String getSelectLongitude() {
		return getSharedPreferences().getString(PREFERENCE_SELECT_LONGITUDE, Constants.EMPTY);
	}

}
