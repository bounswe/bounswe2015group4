package com.model;

import java.io.File;
import java.util.Date;

/**
 * Created by Erdem on 11/17/2015.
 */
public class Event {

    String description;
    String title;
    Date date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
