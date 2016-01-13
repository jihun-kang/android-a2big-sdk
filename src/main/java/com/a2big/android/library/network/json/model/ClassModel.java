package com.a2big.android.library.network.json.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by a2big on 15. 6. 22..
 */
public class ClassModel {
    String user_id;

    @SerializedName("username")
    String user_fucking_name;
    String name;
    ArrayList<ProfileModel> profile;
   ////////////////////// ArrayList<ItemModel> items;

    public String getUser_id() {
        return user_id;
    }

    public String getUser_fucking_name() {
        return user_fucking_name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<ProfileModel> getProfile() {
        return profile;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_fucking_name(String user_fucking_name) {
        this.user_fucking_name = user_fucking_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfile(ArrayList<ProfileModel> profile) {
        this.profile = profile;
    }
    // create constructor, getter/setter, toString
}
