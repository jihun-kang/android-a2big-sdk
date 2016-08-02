package com.a2big.android.library.custom.inteface;

/**
 * IConnector interface has responsibility regarding connecting external system(web service)
 * for getting account information.
 *
 * @author jihun,kang
 *
 */
public interface ShareDialogConnector {
    void onReceivedMessage(int position,String mType, String mId, String mBoardId, String mTitle, String mImage, String mDesc, String mUrl, String mVideoId,String date, String userid,String readnum);
}
