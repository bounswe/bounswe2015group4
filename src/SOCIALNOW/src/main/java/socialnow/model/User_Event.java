package socialnow.model;

/**
 * Created by Erdem on 12/15/2015.
 */
public class User_Event {
    User user;
    Event event;

    public User_Event(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User_Event() {

    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
