package com.a2big.android.library.network.json;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nirav kalola on 11/8/2014.
 */
public class BeanSubject {

    @SerializedName("subject")
    private String subject;
    @SerializedName("price")
    private String price;
    @SerializedName("auther")
    private String auther;
    @SerializedName("topics")
    private ArrayList<BeanTopic> beanTopics;

    public BeanSubject(ArrayList<BeanTopic> beanTopics, String subject, String price, String auther) {
        this.beanTopics = beanTopics;
        this.subject = subject;
        this.price = price;
        this.auther = auther;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public ArrayList<BeanTopic> getBeanTopics() {
        return beanTopics;
    }

    public void setBeanTopics(ArrayList<BeanTopic> beanTopics) {
        this.beanTopics = beanTopics;
    }
}
