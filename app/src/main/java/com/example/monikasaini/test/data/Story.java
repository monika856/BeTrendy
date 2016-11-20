package com.example.monikasaini.test.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Monika on 20/11/16.
 */

public class Story implements Serializable{

    private String description;
    private String id;
    private String verb;
    @SerializedName("db")
    private String userId;
    private String url;
    private String si ;
    private String type;
    private String title;
    @SerializedName("like_flag")
    private Boolean likeFlag;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("comment_count")
    private int commentCount;



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getLikeFlag() {
        return likeFlag;
    }

    public void setLikeFlag(Boolean likeFlag) {
        this.likeFlag = likeFlag;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
