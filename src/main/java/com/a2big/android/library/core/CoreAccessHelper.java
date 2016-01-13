package com.a2big.android.library.core;

/**
 * 	Free for modification and distribution
 * @author jihun,kang
 *
 */

import com.a2big.android.library.account.A2bigHandler;
import com.a2big.android.library.account.IConnector;

public class CoreAccessHelper {
	private volatile SharedTask mSharedTask = null;
	private volatile IConnector mConnector = null;

	
	private SharedTask getSharedTask() {
		if(mSharedTask == null) {
			
			synchronized(this) {
				if(mSharedTask == null) {
					mSharedTask = new SharedTask("Common_Thread");
					mSharedTask.start();
				}
			}
		}
		
		return mSharedTask;
	}
	
	public IConnector getConnector() {
		if(mConnector == null) {
			
			synchronized(this) {
				if(mConnector == null) 
					mConnector = new A2bigHandler(getSharedTask());
			}
			
		}
		
		return mConnector;
	}
}
