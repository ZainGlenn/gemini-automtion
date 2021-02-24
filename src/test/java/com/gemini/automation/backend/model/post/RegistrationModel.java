package com.gemini.automation.backend.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationModel {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public RegistrationModel(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
