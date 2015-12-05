package socialnow.model;

import java.util.ArrayList;

/**
 * Created by Erdem on 12/6/2015.
 */
public class RecommendReturn {

    ArrayList<Event> events = new ArrayList<>();
    ArrayList<Interest_Group> group = new ArrayList<>();

    public RecommendReturn(ArrayList<Event> events, ArrayList<Interest_Group> group) {
        this.events = events;
        this.group = group;
    }

    public ArrayList<Event> getEvents() {

        return events;
    }

    public RecommendReturn() {
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public ArrayList<Interest_Group> getGroup() {
        return group;
    }

    public void setGroup(ArrayList<Interest_Group> group) {
        this.group = group;
    }
}
