package com.example.demo_movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TokenResModel implements Serializable {
    @SerializedName("success")
    private boolean success;

    @SerializedName("expires_at")
    private String expiresAt;

    @SerializedName("request_token")
    private String requestToken;

    public String getRequestToken() {
        return requestToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
