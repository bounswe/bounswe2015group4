package com.socialnow.Models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private String event_date;
    private String title;
    private String event_location;
    private String event_description;
    private User event_host;
    private String hostName;
    private User owner;
    private ArrayList<String> tags= new ArrayList<>();
    public  ArrayList<User> event_participants = new ArrayList<>() ;
    public  ArrayList<PostDetail> event_posts = new ArrayList<>();

    public Event_Detail() {
    }
    public Event_Detail(Event event) {
        this.id = event.getId();
        this.event_description = event.event_description;
        this.title = event.title;
        this.setEvent_photo(event.getEvent_photo());
        this.setOwner(event.getEvent_participant_users());
        this.setDate(event.getEvent_date());
        this.setEvent_location(event.getEvent_location());
    }

    /*public ArrayList<Event> getGroup_events() {

        return group_events;
    }*/

    /*public void setGroup_events(ArrayList<Event> group_events) {
        this.group_events = group_events;
    }*/

    public ArrayList<PostDetail> getEvent_posts() {
        return event_posts;
    }

    public void setEvent_posts(ArrayList<PostDetail> event_posts) {
        this.event_posts = event_posts;
    }

    public void setEvent_host(User event_host){
        this.event_host = event_host;
    }
    public User getEvent_host(){
        return  this.event_host;
    }
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
    public void setDate(String date) {
        this.event_date = date;
    }
    public void setEvent_location(String location) {
        this.event_location = location;
    }
    public String getEvent_location() {
        return this.event_location;
    }
    public User getOwner() {
        return owner;
    }

    public void setOwner(User[] users ) {

        if(users.length > 0) {
            this.owner = users[0];
            this.hostName = this.owner.getName() + " " + this.owner.getSurname();
        }

        else
            this.hostName = "";

    }

    public String getOwnerName() { return this.hostName; }
    public String getDate() { return this.event_date; }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_title() {
        return title;
    }

    public void setEvent_title(String event_title) {
        this.title = event_title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvent_date_as_date() {
        Long  date_as_long = Long.parseLong(getDate());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        String date = ft.format(new Date(date_as_long));
        return date;
    }
   // public  ArrayList<Event> group_events = new ArrayList<>() ;
}
