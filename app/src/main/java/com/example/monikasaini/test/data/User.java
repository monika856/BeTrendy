package com.example.monikasaini.test.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Monika on 20/11/16.
 */

public class User implements Serializable{


    private String about;
    private String id;
    @SerializedName("username")
    private String userName;
    private  int followers;
    private int following;
    private String image;
    private String url ;
    private String handle ;
    @SerializedName("is_following")
    private boolean isFollowing;
    private  Long createdOn;


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }

    public boolean isFollowing(){
        return isFollowing;

    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }


}
