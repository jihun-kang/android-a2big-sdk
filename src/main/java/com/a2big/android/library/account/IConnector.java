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
	public void getSiDoAddr(IResponseEvent<Object> pResponseEvent);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Outer API
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addTaskRegUser(String pName,
							   String pSex,
							   String pEmail,
							   String pImage1,
							   String pImage2,
							   String pImage3,
							   String pImage4,
							   String pImage5,
							   String pBirthDay,
							   String pHeight,
							   String pWeight,
							   String pPhone,
							   String pAddress,
							   String pEducation,
							   String pEducationState,
							   String pEtc,
							   String pCareer,
							   String pPrizeGivingDetails,
							   IResponseEvent<Object> pResponseEvent);
	public void addTaskRegManager(String pEmail, String pPassword, IResponseEvent<Object> pResponseEvent);
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
}
