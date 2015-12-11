package socialnow.model;

import socialnow.forms.Notification.Notification_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Erdem on 12/11/2015.
 */

@Entity
@Table(name = "notifications")
public class Notification {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    String from_user;

    @NotNull
    String to_user;

    @NotNull
    long  event;

    public Notification(String from_user, String to_user, long event) {
        this.from_user = from_user;
        this.to_user = to_user;
        this.event = event;
    }

    public Notification(Notification_Form form) {

        this.from_user = form.getFrom_user_token();
        this.to_user = form.getTo_user_token();
        this.event = form.getEvent_id();

    }

    public Notification() {

    }

    public long getEvent() {

        return event;
    }

    public void setEvent(long event) {
        this.event = event;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String toUser) {
        this.to_user = toUser;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String fromUser) {
        this.from_user = fromUser;
    }


}
