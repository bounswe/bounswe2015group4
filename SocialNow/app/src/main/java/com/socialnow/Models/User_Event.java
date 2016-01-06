package com.socialnow.Models;

/**
 * Created by mertcan on 6.1.2016.
 */
public class User_Event {
    User user;
    Event event;
    /**
     *Constructor method
     *
     *@param user related user object
     *@param event related event object
     */
    public User_Event(User user, Event event) {
        this.user = user;
        this.event = event;
    }
    /**
     *Empty constructor method
     */
    public User_Event() {

    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
