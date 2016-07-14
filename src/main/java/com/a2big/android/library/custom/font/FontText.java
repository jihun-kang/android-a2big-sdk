package com.a2big.android.library.custom.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;



import org.apache.commons.lang3.StringUtils;


public class FontText extends TextView {
    public FontText(Context context) {
        super(context);

        applyCustomFont(context,0);
    }

    public FontText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context,1);
    }

    public FontText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context,1);
    }

    private void applyCustomFont(Context context,int type) {
        Typeface customFont;
        switch(type){
            case 0:
                customFont = FontCache.getTypeface("The170.ttf", context);
                setTypeface(customFont);
                break;
            case 1:
                customFont = FontCache.getTypeface("The130.ttf", context);
                setTypeface(customFont);
                break;
            default:
                customFont = FontCache.getTypeface("The170.ttf", context);
                setTypeface(customFont);
                break;
        }
    }
}
