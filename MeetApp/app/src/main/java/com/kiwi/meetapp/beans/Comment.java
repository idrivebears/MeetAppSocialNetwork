package com.kiwi.meetapp.beans;

/**
 * Created by Carlos Alexis on 19/11/2015.
 */
public class Comment {

    private String description, creatorName, date;

    public Comment(String description, String creatorName, String date) {
        this.description = description;
        this.creatorName = creatorName;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
