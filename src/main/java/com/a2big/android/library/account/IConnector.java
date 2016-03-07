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
							   String pLatitude,
							   String pLongitude,
							   IResponseEvent<Object> pResponseEvent);

	public void addTaskRegManager(String pRegID,			//registrationId
								  String pName,				//이름
								  String pSex,				//성별
								  String pEmail,			//이메일
								  String pCompanyName,		//회사명
								  String pBusinessNo,		//사업자 등록번호
								  String pCompanyAddr,		//사업장 주소
								  IResponseEvent<Object> pResponseEvent);



	public void addTaskGetGeoPhoth(
								  String pEmail,			//이메일
								  String pLat,				//회사명
								  String pLong,				//사업자 등록번호
								  IResponseEvent<Object> pResponseEvent);

	public void getPhotoRoadCasting(IResponseEvent<Object> pResponseEvent);

	//add image, 20160306
	public void addTaskRoadCastingPhoto(
							   String pEmail,
							   String pImage1,
							   String pLatitude,
							   String pLongitude,
							   String pLocation,
							   IResponseEvent<Object> pResponseEvent);

	//add user for login,, 20160306
	public void addTaskUserForSocial(
			String pEmail,
			IResponseEvent<Object> pResponseEvent);

	//like, 20160306
	public void addLikePhoto(
			String pEmail,
			String pPhotoId,
			IResponseEvent<Object> pResponseEvent);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
}
