package com.a2big.android.library.init;

/**
 * 	Free for modification and distribution
 * @author jihun,kang
 *
 */


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

//import com.a2big.outer.main.activities.login.kakao.common.log.Logger;
import com.a2big.android.library.adapters.Setting.SettingManager;
import com.a2big.android.library.account.IConnector;
import com.a2big.android.library.core.CoreAccessHelper;
import com.a2big.android.library.network.manager.NetworkManager;
import com.a2big.android.library.response.ResponseManager;
import com.a2big.android.library.utils.DevLog;
import com.a2big.android.library.utils.Utils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;


public class A2BigApp extends Application {
    private static final String TAG = "A2BigApp";
    //private static com.a2big.android.library.init.A2BigApp mInstance;
    private static volatile A2BigApp mInstance = null;

    private static Context mAppContext;
    private static Activity currentActivity;

    ///private static A2BigApp mCore;
    private boolean mStarted;
    private boolean mKakaoLogin;
    private volatile CoreAccessHelper mAccessor = null;
    private volatile NetworkManager mNetworkManager = null;
    private volatile SettingManager mSettingManager = null;
    private volatile Utils mUtils = null;
    private volatile ResponseManager mResponseManager = null;


    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private boolean mClosetRefresh;
    private boolean mContentRefresh;

    ///private UserMainDietMenuObject.MenuItem mSelectMenuItem;

    @Override
    public void onCreate() {
        super.onCreate();

        ///////  Mint.enableLogging(true);
        //////  Mint.initAndStartSession(this, "76ef4494");

        ////////  Localytics.integrate(this);

        /// A2BigApp.mCore = this;
        mInstance = this;
        ///////KakaoSDK.init(new KakaoSDKAdapter());


        this.setAppContext(getApplicationContext());

        mStarted = false;
        mKakaoLogin  = false;
        onStart();

        /////////  KissAnalytics.startAnalytics(getApplicationContext());

        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("Version", mUtils.getAppVersion());
        properties.put("platform", "Android");
        properties.put("Device", mUtils.getModel());
        /////////  LocAnalytics.tagEvent(EventTypes.TYPE_APP_INFORMATION, properties, 0);


    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ////////   Mint.closeSession(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    public static com.a2big.android.library.init.A2BigApp getInstance(){
        return mInstance;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public void setAppContext(Context mAppContext) {
        this.mAppContext = mAppContext;
    }


    public static com.a2big.android.library.init.A2BigApp getApplication() {
        return mInstance;
    }



    public void onStart() {
        if(mStarted == false) {
            mStarted = true;
            connectCoreEngine();
            getNetworkManager();
            getSettingManager();
            getUtils();
            getResponseManager();
        }
    }


    private void connectCoreEngine() {
        if(mAccessor == null) {
            synchronized(this) {
                if(mAccessor == null) {
                    mAccessor = new CoreAccessHelper();
                }
            }
        }
    }

    public IConnector getConnector() {
        if(mAccessor == null)
            connectCoreEngine();

        return mAccessor.getConnector();
    }

/*
    public UserMainDietMenuObject.MenuItem getSelectMenuItem() {
        return mSelectMenuItem;
    }
*/
    /*
    public void  setSelectMenuItem(UserMainDietMenuObject.MenuItem m) {
        mSelectMenuItem = m;
    }
*/
    public NetworkManager getNetworkManager() {
        if(mNetworkManager == null) {
            synchronized(this) {
                if(mNetworkManager == null) {
                    mNetworkManager = new NetworkManager();
                }
            }
        }

        return mNetworkManager;
    }

    public SettingManager getSettingManager() {
        if(mSettingManager == null) {
            synchronized(this) {
                if(mSettingManager == null) {
                    mSettingManager = new SettingManager();
                }
            }
        }

        return mSettingManager;
    }

    public Utils getUtils() {
        if(mUtils == null) {
            synchronized(this) {
                if(mUtils == null) {
                    mUtils = new Utils(getApplicationContext());
                }
            }
        }

        return mUtils;
    }

    public ResponseManager getResponseManager() {
        if(mResponseManager == null) {
            synchronized(this) {
                if(mResponseManager == null) {
                    mResponseManager = new ResponseManager();
                }
            }
        }

        return mResponseManager;
    }




    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mInstance);

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();

        if(mImageLoader == null)
            mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache());

        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        DevLog.defaultLogging("Adding request to queue: " + req.getUrl());
        getRequestQueue().add(req);
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        DevLog.defaultLogging("Adding request to queue: " + req.getUrl());
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if(mRequestQueue != null)
            mRequestQueue.cancelAll(tag);
    }

    public void setClosetReFresh(boolean pClosetRefresh) {
        mClosetRefresh = pClosetRefresh;
    }

    public boolean getClosetRefresh() {
        return mClosetRefresh;
    }

    public void setContentReFreshing(boolean pContentRefresh) {
        mContentRefresh = pContentRefresh;
    }

    public boolean getContentRefreshing() {
        return mContentRefresh;
    }



    public static Activity getCurrentActivity() {
        ////Logger.d("++ currentActivity : " + (currentActivity != null ? currentActivity.getClass().getSimpleName() : ""));
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        A2BigApp.currentActivity = currentActivity;
    }
    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static A2BigApp getGlobalApplicationContext() {
        if(mInstance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return mInstance;
    }


    public boolean getKakaoLogin(){ return mKakaoLogin; }
    public void setKakaoLoginInit(){
        mKakaoLogin = true;
    }
}