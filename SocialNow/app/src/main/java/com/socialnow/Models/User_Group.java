package com.socialnow.Models;

/**
 * Created by mertcan on 6.1.2016.
 */
public class User_Group {

    User user;
    Group group;

    public User_Group(Group group, User user) {
        this.group = group;
        this.user = user;
    }
    /**
     *Constructor
     */
    public User_Group() {

    }
    /**
     *
     *@return current user
     */
    public User getUser() {

        return user;
    }
    /**
     *
     * @param user user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     *
     *@return current group
     */
    public Group getGroup() {
        return group;
    }
    /**
     *
     * @param group group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }
}
