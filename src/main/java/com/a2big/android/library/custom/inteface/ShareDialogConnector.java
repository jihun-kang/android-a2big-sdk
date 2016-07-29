package com.a2big.android.library.custom.inteface;

/**
 * IConnector interface has responsibility regarding connecting external system(web service)
 * for getting account information.
 *
 * @author jihun,kang
 *
 */
public interface ShareDialogConnector {
    void onReceivedMessage(int position, String mTitle, String mDesc, String mUrl);
}
