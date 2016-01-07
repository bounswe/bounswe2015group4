package com.socialnow.Models;

/**
 * Created by mertcan on 7.1.2016.
 */
public class NotificationRetrieve {
    User from_user;
    User to_user;
    Event event;
    public User getFrom_user() {
        return from_user;
    }

    public void setFrom_user(User from_user) {
        this.from_user = from_user;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
