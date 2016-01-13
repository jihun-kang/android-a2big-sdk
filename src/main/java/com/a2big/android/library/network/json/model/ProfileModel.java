package com.a2big.android.library.network.json.model;

/**
 * Created by a2big on 15. 6. 22..
 */
public class ProfileModel {
    String id;
    String title;
    String image;
    String content;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
