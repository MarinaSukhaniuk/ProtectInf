package com.sukhaniuk.databases.models;

/**
 * Created by Marina on 13.09.2016.
 */
public class Alert {
    private String type;
    private String title;
    private String text;

    public Alert(String type, String title, String text) {
        this.type = type;
        this.title = title;
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {

        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
