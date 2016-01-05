package com.socialnow.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mertcan on 7.12.2015.
 */

public class Profile implements Serializable{

    ArrayList<Group> user_interest_groups = new ArrayList<>();
    ArrayList<User> user_followers = new ArrayList<>();
    ArrayList<User> user_following = new ArrayList<>();
    ArrayList<String> user_tags = new ArrayList<>();
    ArrayList<Event> user_participating_events= new ArrayList<>();
    private String email;
    private String name;
    public int numberOfFollowers;
    public int numberOfFollowings;
    private String surname;
    private String role;
    private String user_token;
    private  String user_photo;

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public Profile(User u) {
        this.setName(u.getName());
        this.setRole(u.getRole());
        this.setSurname(u.getSurname());
        this.setEmail(u.getEmail());
        this.setUser_token(u.getUser_token());
        this.setNumberOfFollowers(u.getNumberOfFollowers());
        this.setNumberOfFollowings(u.getNumberOfFollowings());
        this.setUser_photo(u.getUser_photo());
    }

    public Profile(ArrayList<Group> user_interest_groups, ArrayList<User> user_followers, ArrayList<User> user_following, ArrayList<String> user_tags, ArrayList<Event> user_participating_events, String email, String name, int numberOfFollowers, int numberOfFollowings, String surname, String role, String user_token) {

        this.user_interest_groups = user_interest_groups;
        this.user_followers = user_followers;
        this.user_following = user_following;
        this.user_tags = user_tags;
        this.user_participating_events = user_participating_events;
        this.email = email;
        this.name = name;
        this.numberOfFollowers = numberOfFollowers;
        this.numberOfFollowings = numberOfFollowings;
        this.surname = surname;
        this.role = role;
        this.user_token = user_token;
    }



    public ArrayList<Group> getUser_interest_groups() {
        return user_interest_groups;
    }

    public void setUser_interest_groups(ArrayList<Group> user_interest_groups) {
        this.user_interest_groups = user_interest_groups;
    }

    public ArrayList<User> getUser_followers() {
        return user_followers;
    }

    public void setUser_followers(ArrayList<User> user_followers) {
        this.user_followers = user_followers;
    }

    public ArrayList<User> getUser_following() {
        return user_following;
    }

    public void setUser_following(ArrayList<User> user_following) {
        this.user_following = user_following;
    }

    public ArrayList<String> getUser_tags() {
        return user_tags;
    }

    public ArrayList<String> getUser_tagsOnce(){
        ArrayList<String> tmp = new ArrayList<>();
        for(String s: user_tags){
            if(!tmp.contains(s))
                tmp.add(s);
        }
        return tmp;
    }
    public void setUser_tags(ArrayList<String> user_tags) {
        this.user_tags = user_tags;
    }

    public ArrayList<Event> getUser_participating_events() {
        return user_participating_events;
    }

    public void setUser_participating_events(ArrayList<Event> user_participating_events) {
        this.user_participating_events = user_participating_events;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public int getNumberOfFollowings() {
        return numberOfFollowings;
    }

    public void setNumberOfFollowings(int numberOfFollowings) {
        this.numberOfFollowings = numberOfFollowings;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }
}
