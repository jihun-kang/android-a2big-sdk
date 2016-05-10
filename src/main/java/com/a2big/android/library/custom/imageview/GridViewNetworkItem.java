package com.a2big.android.library.custom.imageview;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by a2big on 16. 5. 10..
 */

public class GridViewNetworkItem extends NetworkImageView {

    public GridViewNetworkItem(Context context) {
        super(context);
    }

    public GridViewNetworkItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewNetworkItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}
