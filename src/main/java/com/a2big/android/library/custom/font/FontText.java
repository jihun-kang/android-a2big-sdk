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

        applyCustomFont(context);
    }

    public FontText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public FontText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("The130.ttf", context);
        setTypeface(customFont);
    }
}
