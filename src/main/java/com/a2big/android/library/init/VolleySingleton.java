package com.a2big.android.library.init;

/**
 * 	Free for modification and distribution
 * @author jihun,kang
 *
 */

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(com.a2big.android.library.init.A2BigApp.getAppContext());

        mImageLoader = new ImageLoader(this.mRequestQueue, new ImageLoader.ImageCache() {

            final int memClass = ((ActivityManager) A2BigApp.getAppContext().getSystemService(
                    Context.ACTIVITY_SERVICE)).getMemoryClass();

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = 1024 * 1024 * memClass / 8;


            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(cacheSize);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
    }

    public static com.a2big.android.library.init.VolleySingleton getInstance(){
        if(mInstance == null){
            mInstance = new com.a2big.android.library.init.VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return this.mRequestQueue;
    }
    
    public ImageLoader getImageLoader(){
        return this.mImageLoader;
    }

}
