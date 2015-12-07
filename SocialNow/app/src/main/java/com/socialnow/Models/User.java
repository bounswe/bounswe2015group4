package com.socialnow.Models;

import java.io.Serializable;

/**
 * Created by mugekurtipek on 24/11/15.
 */
public class User implements Serializable {

    String type;

    private long id;

    String user_interest_groups;

    String user_followers;

    String user_followings;

    private String email;

    private String password;

    private String name;

    public int numberOfFollowers;

    public int numberOfFollowings;

    String user_tags;

    private String surname;

    String user_photo;

    private String role;

    private String user_token;
    private String photo;

    private String user_participating_events ;

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

    public String getUser_interest_groups() {

        return user_interest_groups;
    }

    public void setUser_interest_groups(String user_interest_groups) {
        this.user_interest_groups = user_interest_groups;
    }



    public String getUser_tags() {
        return user_tags;
    }

    public void setUser_tags(String user_tags) {
        this.user_tags = user_tags;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }



    public String getUser_participating_events() {
        return user_participating_events;
    }

    public void setUser_participating_events(String user_participating_events) {
        this.user_participating_events = user_participating_events;
    }


//  private List<Event> user_participating_events

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public User(){
    }

    public User(long id) {
        this.id = id;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
        this.user_participating_events = "";
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String value) {
        this.photo = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String value) {
        this.surname = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_token() {
        return user_token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                ", user_token='" + user_token + '\'' +
                ", user_participating_events=" + user_participating_events +
                '}';
    }

    public void setUser_token(String token) {
        this.user_token = token;
    }




    public String getUser_followers() {
        return user_followers;
    }

    public void setUser_followers(String user_followers) {
        this.user_followers = user_followers;
    }

    public String getUser_followings() {
        return user_followings;
    }

    public void setUser_followings(String user_followings) {
        this.user_followings = user_followings;
    }

    public String getType() {
        return type;
    }
}
