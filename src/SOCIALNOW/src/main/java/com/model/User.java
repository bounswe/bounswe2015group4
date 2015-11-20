package com.model;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erdem on 11/17/2015.
 */
public class User {
    @Override
    public String toString() {
        return "User{" +
                "errorJSON=" + errorJSON +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", Role='" + Role + '\'' +
                ", name='" + name + '\'' +
                ", events=" + events +
                '}';
    }

    public ErrorJSON getErrorJSON() {
        return errorJSON;
    }

    public void setErrorJSON(ErrorJSON errorJSON) {
        this.errorJSON = errorJSON;
    }
    String  id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    ErrorJSON errorJSON;
    String email;
    String password;
    String Role;
    String name;
    List<Event> events = new ArrayList<Event>() ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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
