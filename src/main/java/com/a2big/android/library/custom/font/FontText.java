package com.a2big.android.library.custom.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import org.apache.commons.lang3.StringUtils;


public class FontText extends TextView {

    private static final int FONT_DEFAULT = 0;
    private static final int FONT_1      = 1;

    private static final String FONT_NAME_DEFAULT = "The170.ttf";
    private static final String FONT_NAME_1 = "The130.ttf";


    public FontText(Context context) {
        super(context);

        applyCustomFont(context,FONT_DEFAULT);
    }

    public FontText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context,FONT_1);
    }

    public FontText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context,FONT_1);
    }

    private void applyCustomFont(Context context,int type) {
        Typeface customFont;
        switch(type){
            case FONT_DEFAULT:
                customFont = FontCache.getTypeface(FONT_NAME_DEFAULT, context);
                setTypeface(customFont);
                break;
            case FONT_1:
                customFont = FontCache.getTypeface(FONT_NAME_1, context);
                setTypeface(customFont);
                break;
            default:
                customFont = FontCache.getTypeface(FONT_NAME_DEFAULT, context);
                setTypeface(customFont);
                break;
        }
    }
}
