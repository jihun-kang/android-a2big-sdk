package com.a2big.android.library.account;

/**
 * IConnector interface has responsibility regarding connecting external system(web service)
 * for getting account information.
 * 
 * @author jihun,kang
 *
 */
public interface IConnector {
	public void loginAccount(String pEmail, String pPassword, IResponseEvent<Object> pResponseEvent);
}
