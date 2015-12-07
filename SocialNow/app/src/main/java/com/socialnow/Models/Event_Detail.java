package com.socialnow.Models;

import java.util.ArrayList;
import com.socialnow.Models.Event;

/**
 * Created by berfu on 7.12.2015.
 */
public class Event_Detail {
    private long id;

    private String event_photo;

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    private String event_title;
    private String event_description;
    private User owner;
    private ArrayList<String> tags= new ArrayList<>();
    public  ArrayList<User> event_participants = new ArrayList<>() ;
   // public  ArrayList<PostDetail> event_posts = new ArrayList<>();

    public Event_Detail() {
    }
    public Event_Detail(Event event) {
        this.id = event.getId();
        this.event_description= event.event_description;
        this.event_title= event.title;
        this.setEvent_photo(event.getEvent_photo());
    }


    /*public ArrayList<Event> getGroup_events() {

        return group_events;
    }*/

    /*public void setGroup_events(ArrayList<Event> group_events) {
        this.group_events = group_events;
    }*/

   /* public ArrayList<PostDetail> getGroup_posts() {
        return event_posts;
    }*/

   /* public void setGroup_posts(ArrayList<PostDetail> group_posts) {
        this.group_posts = group_posts;
    }*/

    public ArrayList<User> getEvent_participants() {
        return event_participants;
    }

    public void setEvent_members(ArrayList<User> event_participants) {
        this.event_participants = event_participants;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   // public  ArrayList<Event> group_events = new ArrayList<>() ;
}
