package com.test1.test1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USER_PHOTO")
public class UserPhoto {

    @Id
    private Integer userId;
    private Integer photoId;

    public UserPhoto(Integer userId, Integer photoId) {
        this.userId = userId;
        this.photoId = photoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public boolean show() {
        System.out.println("UserPhoto: userId=" + userId + ", photoId=" + photoId);
        return true;
    }
}