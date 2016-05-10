package com.a2big.android.library.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by a2big on 16. 4. 26..
 */
public class BannerViewPager extends ViewPager {
    private boolean enabled;

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    /**
     * Custom implementation to enable or not swipe :)
     *
     * @param enabled
     *            true to enable swipe, false otherwise.
     */
    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
