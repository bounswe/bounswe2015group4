package com.socialnow.Models;

/**
 * Created by mugekurtipek on 24/11/15.
 */
public class AccessToken {
    private String accessToken;
    private int userId;

    public AccessToken(String UUID, int userId) {
        this.accessToken = UUID;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
