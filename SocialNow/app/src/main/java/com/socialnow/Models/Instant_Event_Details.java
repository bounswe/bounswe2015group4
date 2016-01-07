package com.socialnow.Models;

import java.util.Calendar;

/**
 * Created by mugekurtipek on 06/01/16.
 */
public class Instant_Event_Details {
    private long id;

String ownerToken;

    int duration_in_minutes;

    String instant_event_description;

    String title;

    int time_remaining;

    String instant_event_location;

    User instant_event_owner;

    public Instant_Event_Details() {

    }
    public Instant_Event_Details(Instant_Event e) {
        id = e.getId();

        duration_in_minutes = e.getDuration_in_minutes();
        title=e.getTitle();
        instant_event_description = e.getInstant_event_description();
        instant_event_location = e.getInstant_event_location();
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getDuration_in_minutes() {
        return duration_in_minutes;
    }

    public void setDuration_in_minutes(int duration_in_minutes) {
        this.duration_in_minutes = duration_in_minutes;
    }

    public int getTime_remaining() {
        return time_remaining;
    }

    public void setTime_remaining(int time_remaining) {
        this.time_remaining = time_remaining;
    }
    public String getInstant_event_description() {
        return instant_event_description;
    }

    public void setInstant_event_description(String instant_event_description) {
        this.instant_event_description = instant_event_description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstant_event_location() {
        return instant_event_location;
    }

    public void setInstant_event_location(String instant_event_location) {
        this.instant_event_location = instant_event_location;
    }

    public User getInstant_event_owner() {
        return instant_event_owner;
    }

    public void setInstant_event_owner(User instant_event_owner) {
        this.instant_event_owner = instant_event_owner;
    }

    public String getOwnerToken() {
        return ownerToken;
    }

    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }

}
