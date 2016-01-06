package socialnow.model;

import java.util.ArrayList;

/**
 * Created by Erdem on 12/6/2015.
 */
public class RecommendReturn {

    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Interest_Group> groups = new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Interest_Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Interest_Group> groups) {
        this.groups = groups;
    }

    ArrayList<User> users = new ArrayList<>();

    public RecommendReturn(ArrayList<Event> events, ArrayList<Interest_Group> group) {
        this.events = events;
        this.groups = group;
    }

    public ArrayList<Event> getEvents() {

        return events;
    }

    public RecommendReturn() {
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }


}
