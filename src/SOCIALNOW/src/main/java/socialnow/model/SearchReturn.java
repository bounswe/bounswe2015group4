package socialnow.model;

import java.util.ArrayList;

/**
 * Created by Erdem on 12/6/2015.
 */
public class SearchReturn {

    ArrayList<Event> events = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Interest_Group> groups  = new ArrayList<>();

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

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
}
