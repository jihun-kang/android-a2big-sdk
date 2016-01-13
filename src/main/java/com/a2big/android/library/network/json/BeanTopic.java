package com.a2big.android.library.network.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nirav kalola on 11/8/2014.
 */
public class BeanTopic {

    @SerializedName("title")
    private String title;

    public BeanTopic(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
