package com.a2big.android.library.network.volley;

import android.graphics.Bitmap;

public class BitmapHelper {

    public static int getSizeInBytes(Bitmap bitmap) {
        if (android.os.Build.VERSION.SDK_INT>=12) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }
}