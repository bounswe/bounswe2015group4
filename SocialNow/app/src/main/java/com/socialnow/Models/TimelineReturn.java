package com.socialnow.Models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mertcan on 12/15/2015.
 */
public class TimelineReturn {


    List<User_Event> user_events;
    List<User_Group> user_groups;

    public TimelineReturn() {
        user_events = new ArrayList<>();
        user_groups = new ArrayList<>();
    }

    public List<User_Event> getUser_events() {

        return user_events;
    }

    public void setUser_events(List<User_Event> user_events) {
        this.user_events = user_events;
    }

    public List<User_Group> getUser_groups() {
        return user_groups;
    }

    public void setUser_groups(List<User_Group> user_groups) {
        this.user_groups = user_groups;
    }
}