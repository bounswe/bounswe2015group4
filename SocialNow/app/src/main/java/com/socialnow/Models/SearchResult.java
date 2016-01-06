package com.socialnow.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mertcan on 5.1.2016.
 */
public class SearchResult implements Serializable {
    ArrayList<Event> events;
    ArrayList<Group> groups;
    ArrayList<Group> group;
    ArrayList<User> users;

    public ArrayList<Group> getGroups() {
        if(group == null)
            return groups;
        else
            return group;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<User> getUsers() {
        if(users == null)
            return new ArrayList<User>();
        else
            return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Event> getEvents() {

        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
