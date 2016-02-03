/**
 * Created by a2big on 15. 6. 21..
 */

package com.a2big.android.library.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.android.volley.toolbox.NetworkImageView;

/**
 * An {@link android.widget.ImageView} layout that maintains a consistent width to height aspect ratio.
 */
public class DNetworkImageView extends NetworkImageView {

    private double mHeightRatio;

    public DNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DNetworkImageView(Context context) {
        super(context);
    }

    public void setHeightRatio(double ratio) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio;
            requestLayout();
        }
    }

    public double getHeightRatio() {
        return mHeightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DevLog.defaultLogging("widthMeasureSpec...W:"+widthMeasureSpec+" H:"+heightMeasureSpec);



        if (mHeightRatio > 0.0) {
            // set the image views size
            int width = View.MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mHeightRatio);
            setMeasuredDimension(width, height);
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}



