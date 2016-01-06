package com.socialnow.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by mugekurtipek on 24/11/15.
 */
public class Event implements Serializable {
    // ------------------------
    // PRIVATE FIELDS

    private long id;

    String title;

    String event_description;

    String event_start_date;

    String event_end_date;

    String event_location;

    String event_host_token;

    String event_participants;

    String event_comments;

    String tags;

    String type;

    String event_photo;

    User[] event_participant_users;

    String visibleTo;


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

//    public Event(String visibleTo, String event_date,User[] event_participant_users,Long id, String title,String type, String event_photo, String event_comments,String event_description,String event_participants,String tags,String event_host_token, String event_location){
//        this.id = id;
//        this.title = title;
//        this.event_host_token = event_host_token;
//        this.event_location = event_location;
//        this.event_description = event_description;
//
//        this.event_participants = event_participants;
//        this.tags = tags;
//        this.event_comments = event_comments;
//        this.event_photo =event_photo;
//        this.type = type;
//        this.event_participant_users = event_participant_users;
//        this.visibleTo = visibleTo;
//        this.event_start_date = event_date;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String get_visibleTo() {
        return visibleTo;
    }

    public void set_visibleTo(String visibleTo) {
        this.visibleTo = visibleTo;
    }
    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }


    public String getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_date(String event_date) {
        this.event_start_date = event_date;
    }

    public String getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(String event_date) {
        this.event_end_date = event_date;
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

    public String getEvent_tags() {
        return tags;
    }

    public void setEvent_tags(String event_tags) {
        this.tags = event_tags;
    }

    public String getEvent_type() {
        return type;
    }

    public void setEvent_type(String event_type) {
        this.type = event_type;
    }

    public User[] getEvent_participant_users() {
        return event_participant_users;
    }

    public void setEvent_participant_users(User[] event_participant_users) {
        this.event_participant_users = event_participant_users;
    }

    public String getEvent_date_as_date() {
        Long  date_as_long = Long.parseLong(getEvent_start_date());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm");
        String date = "Start: "  + ft.format(new Date(date_as_long));
        date_as_long = Long.parseLong(getEvent_end_date());
        date += "\nEnd  : " + ft.format(new Date(date_as_long));
        return date;
    }
}
