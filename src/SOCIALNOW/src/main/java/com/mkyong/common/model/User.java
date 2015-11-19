package com.mkyong.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erdem on 11/17/2015.
 */
public class User {

    String email;
    int password;
    String Role;
    String name;
    List<Event> events = new ArrayList<Event>() ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
