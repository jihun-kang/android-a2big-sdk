package com.a2big.android.library.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class SquareButton extends Button {
 
    public SquareButton(Context context) {
        super(context);
    }
 
    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public SquareButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
 
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
 
        width = Math.min(width, height);
        height = width;
 
        setMeasuredDimension(width, height);
    }
}