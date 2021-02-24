package com.gemini.automation.backend.model.get;

import com.google.gson.annotations.SerializedName;

public class AuthResponseModel {
    @SerializedName("token")
    private String token;
    @SerializedName("expires")
    private String expires;

    public AuthResponseModel() {
    }

    public String getToken() {
        return token;
    }

    public String getExpires() {
        return expires;
    }
}
