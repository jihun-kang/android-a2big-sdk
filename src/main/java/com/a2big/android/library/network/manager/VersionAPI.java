package com.a2big.android.library.network.manager;

import android.os.AsyncTask;

import com.a2big.android.library.init.A2BigApp;
import com.a2big.android.library.object.objecttype.VersionObject;

public class VersionAPI implements Runnable {
	private Thread thread;
	private NetworkManager mNetworkManager;
	
	public static enum VERSION_RESULT {NO_RESPONSE, NO_NETWORK, SUCCESS}
	
	public interface OnCompleteVersionListener {
		void onComplete(VERSION_RESULT result);
	}
	
	protected OnCompleteVersionListener mCompleteListener;
	
	public void setOnCompleteListener(OnCompleteVersionListener pCompleteListener) {
		mCompleteListener = pCompleteListener;
	}
	
	public VersionAPI() {
		A2BigApp app = A2BigApp.getApplication();
		mNetworkManager = app.getNetworkManager();
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		if(mNetworkManager.isNetworkAvailable(A2BigApp.getApplication().getBaseContext())) {
			
			AsyncHttp async = new AsyncHttp();
			String result = async.getRequestAPI("api/version");
			
			new AsyncTask<String, Void, Void>() {

				@Override
				protected Void doInBackground(String... params) {
					if(params[0] == null) {
						mCompleteListener.onComplete(VERSION_RESULT.NO_RESPONSE);
					} else {
						boolean result = new VersionObject().onResponseListener(params[0]);
						
						if(result) {
							mCompleteListener.onComplete(VERSION_RESULT.SUCCESS);
						} else {
							mCompleteListener.onComplete(VERSION_RESULT.NO_RESPONSE);
						}
					}
					
					return null;
				}
				
			}.execute(result); 
			
		} else {
			mCompleteListener.onComplete(VERSION_RESULT.NO_NETWORK);
		}
	}
	
	
}
