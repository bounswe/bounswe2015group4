package com.socialnow.Models;

import java.util.Date;

/**
 * Created by mugekurtipek on 24/11/15.
 */
public class Event {
    // ------------------------
    // PRIVATE FIELDS

    private long id;

    private String title;

    private String event_description;

    private Date event_date;

    private String event_location;

    private String event_host_token;

    private String event_participants;

    private String event_comments;

    private String event_photo;

  /*  public Event(Event_Form e_f) {
        title = e_f.getTitle();
        event_description = e_f.getEvent_description();
        event_date = e_f.getEvent_date();
        event_location = e_f.getEvent_location();
        event_host_token = e_f.getEvent_host_token();
        event_participants = e_f.getEvent_participants();
        event_comments = e_f.getEvent_comments();
        event_photo = e_f.getEvent_photo();
    }*/
    public  Event(){

    }
/*
    public Event(Error_JSON e) {
        id = -1;
        title = e.toString();
    }*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_host_token() {
        return event_host_token;
    }

    public void setEvent_host_token(String event_host_token) {
        this.event_host_token = event_host_token;
    }

    public String getEvent_participants() {
        return event_participants;
    }

    public void setEvent_participants(String event_participants) {
        this.event_participants = event_participants;
    }

    public String getEvent_comments() {
        return event_comments;
    }

    public void setEvent_comments(String event_comments) {
        this.event_comments = event_comments;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    // ------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
