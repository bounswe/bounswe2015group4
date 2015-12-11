package socialnow.model;

/**
 * Created by Erdem on 12/11/2015.
 */
public class Notification_Detail {

    User from_user;

    public Notification_Detail(User from_user, User to_user, Event event) {
        this.from_user = from_user;
        this.to_user = to_user;
        this.event = event;
    }

    public Notification_Detail() {

    }

    public User getFrom_user() {

        return from_user;
    }

    public void setFrom_user(User from_user) {
        this.from_user = from_user;
    }

    public User getTo_user() {
        return to_user;
    }

    public void setTo_user(User to_user) {
        this.to_user = to_user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    User to_user;
    Event  event;



}
