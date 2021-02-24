package com.gemini.automation.backend.model.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodoResponseModel {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("isComplete")
    @Expose
    private boolean isComplete;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
