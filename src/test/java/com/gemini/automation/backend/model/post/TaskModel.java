package com.gemini.automation.backend.model.post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskModel {
    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("description")
    @Expose
    public String description;


    @SerializedName("isComplete")
    @Expose
    public boolean isComplete;

    public TaskModel(String title, String description, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
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
