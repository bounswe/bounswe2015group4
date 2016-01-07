package com.socialnow.Models;

/**
 * Created by mertcan on 6.1.2016.
 */
public class Basicgroup_event {
    public String photoUrl;
    public String owner;
    public String name;
    public boolean create_join = true;
    public Basicgroup_event(String photoUrl, String owner, String name, boolean create_join){
        this.photoUrl = photoUrl;
        this.owner = owner;
        this.name = name;
        this.create_join = create_join;
    }

}
