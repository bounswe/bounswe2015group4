package com.socialnow.Models;

import java.util.ArrayList;

/**
 * Created by mugekurtipek on 07/12/15.
 */
public class Group_Detail {
    private long id;

    private String group_photo;

    public String getGroup_photo() {
        return group_photo;
    }

    public void setGroup_photo(String group_photo) {
        this.group_photo = group_photo;
    }

    private String group_name;
    private String group_description;
    private User owner;
    private ArrayList<String> tags= new ArrayList<>();
    public  ArrayList<User> group_members = new ArrayList<>() ;
    public  ArrayList<PostDetail> group_posts = new ArrayList<>();
String visibleTo;
    public Group_Detail() {
    }
    public Group_Detail(Group group) {
        this.id = group.getId();
        this.group_description= group.group_description;
        this.group_name= group.group_name;
        this.setGroup_photo(group.getGroup_photo());
    }


    public ArrayList<Event> getGroup_events() {

        return group_events;
    }

    public void setGroup_events(ArrayList<Event> group_events) {
        this.group_events = group_events;
    }

    public ArrayList<PostDetail> getGroup_posts() {
        return group_posts;
    }

    public void setGroup_posts(ArrayList<PostDetail> group_posts) {
        this.group_posts = group_posts;
    }

    public ArrayList<User> getGroup_members() {
        return group_members;
    }

    public void setGroup_members(ArrayList<User> group_members) {
        this.group_members = group_members;
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

    public String getGroup_description() {
        return group_description;
    }

    public void setGroup_description(String group_description) {
        this.group_description = group_description;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public  ArrayList<Event> group_events = new ArrayList<>() ;
    public String getVisibleTo() {
        return visibleTo;
    }

    public void setVisibleTo(String visibleTo) {
        this.visibleTo = visibleTo;
    }


}
