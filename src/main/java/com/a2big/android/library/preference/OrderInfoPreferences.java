package com.a2big.android.library.preference;

import android.content.Context;
import android.content.SharedPreferences;


public class OrderInfoPreferences {	
	private Context mContext;

	private static SharedPreferences sPrefs = null;
	public static final String KEY_POPUP_ENV = "key_env";
	/*
	 * ProductInfoActivity 
	 */
	public static final String KEY_PRODUCT_INFO_TAB_STATE = "mode_product_info_tab_state";
////////////////////////////////////////////////////////////////////////////////////////////
	public static final String KEY_SENDER_INFO_TAB_STATE = "mode_sender_info_tab_state";
/////////////////////////////////////////////////////////////////////////////////////////////
	public static final String KEY_RECEIVER_INFO_TAB_STATE = "mode_receiver_info_tab_state";
/////////////////////////////////////////////////////////////////////////////////////////////
	public static final String KEY_TAB_STATE = "mode_tab_state";

	
	public static final String KEY_SENDER_TAB = "mode_send_info_tab";
	public static final String KEY_SENDER_NAME_TAB1 = "mode_sender_name_tab1";
	public static final String KEY_SENDER_PHONE_TAB1 = "mode_sender_phone_tab1";
	public static final String KEY_SENDER_ADDR1_TAB1 = "mode_sender_addr1_tab1";
	public static final String KEY_SENDER_ADDR2_TAB1 = "mode_sender_addr2_tab1";

	public static final String KEY_SENDER_NAME_TAB2 = "mode_sender_name_tab2";
	public static final String KEY_SENDER_PHONE_TAB2 = "mode_sender_phone_tab2";
	public static final String KEY_SENDER_ADDR1_TAB2 = "mode_sender_addr1_tab2";
	public static final String KEY_SENDER_ADDR2_TAB2 = "mode_sender_addr2_tab2";
	
	public static final String KEY_RECV_TAB = "mode_recv_info_tab";
	public static final String KEY_RECV_NAME = "mode_recv_name";
	public static final String KEY_RECV_PHONE = "mode_recv_phone";
	public static final String KEY_RECV_ADDR1 = "mode_recv_addr1";
	public static final String KEY_RECV_ADDR2 = "mode_recv_addr2";
	public static final String KEY_RECV_MSG = "mode_recv_msg";

	public static final String KEY_ORDER_ID = "mode_order_id";
	public static final String KEY_PAY_INFO_TAB = "mode_payinfo_tab";
		
	public static final String KEY_BANK_NAME = "mode_bank_name";
	public static final String KEY_ACCOUNT_NO = "mode_account_no";
	public static final String KEY_ACCOUNT_NAME = "mode_account_name";


	public static final String KEY_SHOP_ID = "mode_shop_id";
	public static final String KEY_DELIVER_PRICE = "mode_deliver_price";
	
	
	public static final String KEY_PRODUCT_SELECT_ORDER_ID = "mode_p_select_order_id";
	
	
	public static final String KEY_PUSH_STAT = "mode_push_stat";
	public static final String KEY_PUSH_ORDER_CODE  = "mode_push_order_code";
	public static final String KEY_PUSH_ORDER_STATE = "mode_push_order_state";

	public static final String KEY_USER_TYPE = "mode_user_type";

	public static final String KEY_PUSH_INFO = "mode_push_info";

	
	public static final String KEY_PUSH_SHOP_INFO_NAME 		= "shop_info_name";
	public static final String KEY_PUSH_SHOP_INFO_USER_NAME = "shop_info_user_name";
	public static final String KEY_PUSH_SHOP_INFO_PHONE		= "shop_info_phone";
	public static final String KEY_PUSH_SHOP_INFO_ADDRESS   = "shop_info_address";

	public static final String KEY_RUN_ORDER_TYPE   = "run_order_type";

	
	
	
	public OrderInfoPreferences(Context c) {
		super();
		mContext 		= c;
	
	}
	
	/*
	 * 상품정보
	 */
	
	
	/*
	 * 받는 사람 
	 */
	public String getRecvName() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_RECV_NAME, "");
	}

	public void saveRecvName(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_RECV_NAME, value);
		editor.commit();
	}

	public String getRecvPhone() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_RECV_PHONE,"");
	}

	public void saveRecvPhone(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_RECV_PHONE, value);
		editor.commit();
	}
	
	public String getRecvAddr1() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_RECV_ADDR1,"");
	}

	public void saveRecvAddr1(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_RECV_ADDR1, value);
		editor.commit();
	}
	
	public String getRecvAddr2() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_RECV_ADDR2,"");
	}

	public void saveRecvAddr2(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_RECV_ADDR2, value);
		editor.commit();
	}

	
	
	public String getRecvMessage() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_RECV_MSG,"");
	}

	public void saveRecvMessage(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_RECV_MSG, value);
		editor.commit();
	}

	
	
	

	/*
	 * 보내는 사람 
	 */
	public String getSenderName() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_NAME_TAB1,"");
	}

	public void saveSenderName(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_NAME_TAB1, value);
		editor.commit();
	}

	public String getSenderPhone() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_PHONE_TAB1,"");
	}

	public void saveSenderPhone(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_PHONE_TAB1, value);
		editor.commit();
	}
	
	public String getSenderAddr1() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_ADDR1_TAB1,"");
	}

	public void saveSenderAddr1(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_ADDR1_TAB1, value);
		editor.commit();
	}
	
	public String getSenderAddr2() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_ADDR2_TAB1,"");
	}

	public void saveSenderAddr2(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_ADDR2_TAB1, value);
		editor.commit();
	}
	
	//////////////////////////////////////////////////
	public String getSenderNameTab2() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_NAME_TAB2, "");
	}

	public void saveSenderNameTab2(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_NAME_TAB2, value);
		editor.commit();
	}

	public String getSenderPhoneTab2() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_PHONE_TAB2, "");
	}

	public void saveSenderPhoneTab2(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_PHONE_TAB2, value);
		editor.commit();
	}
	
	public String getSenderAddr1Tab2() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_ADDR1_TAB2, "");
	}

	public void saveSenderAddr1Tab2(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_ADDR1_TAB2, value);
		editor.commit();
	}
	
	public String getSenderAddr2Tab2() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SENDER_ADDR2_TAB2,"");
	}

	public void saveSenderAddr2Tab2(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SENDER_ADDR2_TAB2, value);
		editor.commit();
	}
	
	public String getSelectProductOrderIdForEach() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_PRODUCT_SELECT_ORDER_ID, "");
	}
	public void saveSelectProductOrderIdForEach(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_PRODUCT_SELECT_ORDER_ID, value);
		editor.commit();
	}
///////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * Order ID
	 */
	public String getOrderId() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_ORDER_ID, "");
	}
	public void saveOrderId(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_ORDER_ID, value);
		editor.commit();
	}
///////////
///////////////////////////////////////////////////////////////////////////////////////////////
/*
 * Bank Info
 */
	public String getBankName() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_BANK_NAME, "");
	}
	public void saveBankName(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_BANK_NAME, value);
		editor.commit();
	}
	
	public String getAccountNo() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_ACCOUNT_NO, "");
	}
	public void saveAccountNo(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_ACCOUNT_NO, value);
		editor.commit();
	}
	
	
	public String getAccountName() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_ACCOUNT_NAME, "");
	}
	public void saveAccountName(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_ACCOUNT_NAME, value);
		editor.commit();
	}

	
	public String getShopId() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_SHOP_ID, "");
	}
	public void saveShopId(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_SHOP_ID, value);
		editor.commit();
	}


	public String getDeliverPrice() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_DELIVER_PRICE, "0");
	}
	public void saveDeliverPrice(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_DELIVER_PRICE, value);
		editor.commit();
	}

	public boolean getPushStat() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_PUSH_STAT, false);
	}
	public void savePushStat(Boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_PUSH_STAT, value);
		editor.commit();
	}

	public String getPushOrderCode() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_PUSH_ORDER_CODE, "");
	}
	public void savePushOrderCode(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_PUSH_ORDER_CODE, value);
		editor.commit();
	}
	
	
	public String getPushOrderState() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_PUSH_ORDER_STATE, "");
	}
	public void savePushOrderState(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_PUSH_ORDER_STATE, value);
		editor.commit();
	}
	
	

	
	
	
	public String getUserType() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getString(KEY_USER_TYPE, null);
	}
	public void saveUserType(String value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putString(KEY_USER_TYPE, value);
		editor.commit();
	}
	
	public boolean getPushInfo() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_PUSH_INFO, true);
	}
	
	public void savePushInfo(boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_PUSH_INFO, value);
		editor.commit();
	}

	////////////////////////////////////////////////////////////////////////////

	public boolean getShopInfoName() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_PUSH_SHOP_INFO_NAME, true);
	}
	
	public void saveShopInfoName(boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_PUSH_SHOP_INFO_NAME, value);
		editor.commit();
	}

	
	public boolean getShopInfoUserName() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_PUSH_SHOP_INFO_USER_NAME, true);
	}
	
	public void saveShopInfoUserName(boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_PUSH_SHOP_INFO_USER_NAME, value);
		editor.commit();
	}
	
	public boolean getShopInfoPhone() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_PUSH_SHOP_INFO_PHONE, true);
	}
	
	public void saveShopInfoPhone(boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_PUSH_SHOP_INFO_PHONE, value);
		editor.commit();
	}

	public boolean getShopInfoAddress() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_PUSH_SHOP_INFO_ADDRESS, true);
	}
	
	public void saveShopInfoAddress(boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_PUSH_SHOP_INFO_ADDRESS, value);
		editor.commit();
	}
////////////////////////////////////////////////////////////////////////////////
	/*
	 * 0: 주문
	 * 1: 카트에서 주문  
	 */
	public boolean getRunOrderType() {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		return sPrefs.getBoolean(KEY_RUN_ORDER_TYPE, false);
	}
	
	public void saveRunOrderType(boolean value) {
		if (sPrefs == null) {
			sPrefs = mContext.getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putBoolean(KEY_RUN_ORDER_TYPE, value);
		editor.commit();
	}

	
}