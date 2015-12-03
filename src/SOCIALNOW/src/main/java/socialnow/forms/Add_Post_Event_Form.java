package socialnow.forms;

/**
 * Created by Erdem on 12/3/2015.
 */
public class Add_Post_Event_Form {

    String event_id;
    String post_id;

    public Add_Post_Event_Form() {
    }

    public Add_Post_Event_Form(String event_id, String post_id) {

        this.event_id = event_id;
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "Add_Post_Event_Form{" +
                "event_id='" + event_id + '\'' +
                ", post_id='" + post_id + '\'' +
                '}';
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
}
