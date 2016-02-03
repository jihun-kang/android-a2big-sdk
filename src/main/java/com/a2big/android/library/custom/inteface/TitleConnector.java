package com.a2big.android.library.custom.inteface;

/**
 * IConnector interface has responsibility regarding connecting external system(web service)
 * for getting account information.
 *
 * @author jihun,kang
 *
 */
public interface TitleConnector {
    void onReceivedLeft1();
    void onReceivedRight1();
    void onReceivedRight2();
}
