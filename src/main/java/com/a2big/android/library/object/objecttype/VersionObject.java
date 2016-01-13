package com.a2big.android.library.object.objecttype;

import com.a2big.android.library.Setting.SettingManager;
import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.utils.DevLog;

import org.json.JSONException;
import org.json.JSONObject;


public class VersionObject extends AbstractObject {
	private static final String TAG_API = "api";
	private static final String TAG_ANDROID_VERSION = "android_version";
	private static final String TAG_FORCE_UPDATE = "force_update";
	
	private SettingManager mSettingManager;
	
	public VersionObject() {
		mSettingManager = A2BigApp.getApplication().getSettingManager();
	}
	
	@Override
	public boolean onResponseListener(String pResponse) {
		
		try {
			JSONObject json = new JSONObject(pResponse);
			boolean success = json.getBoolean(TAG_SUCCESS);
			
			if(!success) {
				mErrorMessage = json.getString(TAG_MSG);
				mCode = json.getInt(TAG_CODE);
				return false;
			} 
			
			JSONObject object = json.getJSONObject(TAG_API);
			
			mSettingManager.setMarketVersion(object.getString(TAG_ANDROID_VERSION));
			///mSettingManager.setUpdateState(object.getBoolean(TAG_FORCE_UPDATE));
			
		} catch(JSONException e) {
			e.printStackTrace();
			DevLog.defaultLogging(e.toString());
			return false;
		}
		
		return true;
	}

}
