package com.a2big.android.library;

import android.content.res.Resources;

/**
 * Created by a2big on 15. 12. 15..
 */
public class Say {
    public String hello(Resources res){
        return res.getString(R.string.hello);
    }
}

