package com.a2big.android.library.custom;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

public class SquareNetworkImageView extends NetworkImageView {

    public SquareNetworkImageView(Context context) {
        super(context);
    }

    public SquareNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        /*
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
 
        width = Math.min(width, height);
        height = width;
 
        setMeasuredDimension(width, height);
        */
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}