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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    String from_user;

    @NotNull
    String to_user;

    @NotNull
    long  event;

    public Notification(String fromUser, String toUser, long event) {
        this.from_user = fromUser;
        this.to_user = toUser;
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

    public String getToUser() {
        return to_user;
    }

    public void setToUser(String toUser) {
        this.to_user = toUser;
    }

    public String getFromUser() {
        return from_user;
    }

    public void setFromUser(String fromUser) {
        this.from_user = fromUser;
    }


}
