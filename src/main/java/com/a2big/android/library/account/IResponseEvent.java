package com.a2big.android.library.account;

/**
 * IResponseEvent interface indicates callback which is related with the activated events by IConnector.
 * 
 * @author jihun,kang
 *
 * @param <T>
 */
public interface IResponseEvent<T extends Object> {

	/**
	 * It is called when account information is requested.
	 * 
	 * @param pParams
	 */
	public void onResponse(T pParams);
}
