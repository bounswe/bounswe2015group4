package socialnow.forms.Notification;

/**
 * Created by Erdem on 12/11/2015.
 */
public class Notification_Form {
    public Notification_Form(String from_user_token, String to_user_token, long event_id) {
        this.from_user_token = from_user_token;
        this.to_user_token = to_user_token;
        this.event_id = event_id;
    }

    public Notification_Form() {

    }

    public String getFrom_user_token() {

        return from_user_token;
    }

    public void setFrom_user_token(String from_user_token) {
        this.from_user_token = from_user_token;
    }

    public String getTo_user_token() {
        return to_user_token;
    }

    public void setTo_user_token(String to_user_token) {
        this.to_user_token = to_user_token;
    }

    public long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(long event_id) {
        this.event_id = event_id;
    }

    String from_user_token;
    String to_user_token;
    long event_id;



}
