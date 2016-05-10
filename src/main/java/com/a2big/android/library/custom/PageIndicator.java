package com.a2big.android.library.custom;

/**
 * Created by a2big on 15. 6. 8..
 */

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a2big.android.library.custom.inteface.PageConnector;
import com.a2big.android.library.R;


public class PageIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private OnPageChangeListener mListener;
    private ViewPager mViewPager;
    private int mSelectedIndex;
    private PageConnector mEventListener;
    private int mTempPage = 0;
    public PageIndicator(Context pContext) {
        this(pContext, null);
    }

    public PageIndicator(Context pContext, AttributeSet pAttrs) {
        super(pContext, pAttrs);
        setHorizontalScrollBarEnabled(false);
    }

    public void setViewPager(ViewPager pView) {

        if(mViewPager == pView) {
            return;
        }

        if(mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }

        PagerAdapter adapter = pView.getAdapter();
        if(adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance");
        }

        mViewPager = pView;
        pView.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        removeAllViews();
        int count = mViewPager.getAdapter().getCount();
        for(int i = 0; i < count; ++i) {
            ImageView view = new ImageView(getContext());
            view.setImageResource(R.drawable.page_indicator);
            LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 0, 15, 0);
            addView(view, params);
        }


        if(mSelectedIndex > count) {
            mSelectedIndex = count - 1;
        }

        setCurrentItem(mSelectedIndex);

        requestLayout();
    }

    private void setCurrentItem(int pIndex) {
        if(mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been found");
        }

        mSelectedIndex = pIndex;
        mViewPager.setCurrentItem(pIndex);

        int count = getChildCount();
        for(int i = 0; i < count; ++i) {
            View child = getChildAt(i);
            boolean isSelected = (i == pIndex);
            child.setSelected(isSelected);
        }

        int lastPage = count - 1;
        if(pIndex == lastPage){
            mEventListener.onLastPage();
        }
        else if ((mTempPage == lastPage) && (pIndex == (lastPage - 1))) {
            mEventListener.onNormalPage();
        }
        mTempPage = pIndex;

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if(mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if(mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
        if(mListener != null) {
            mListener.onPageSelected(arg0);
        }
    }


    public void setOnLastPage(PageConnector listener){
        mEventListener = listener;
    }


}
