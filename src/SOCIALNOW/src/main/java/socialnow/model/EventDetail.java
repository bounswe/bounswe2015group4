package socialnow.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Erdem on 12/4/2015.
 */
public class EventDetail {


    ArrayList<String>  tags;
    ArrayList<PostDetail> event_posts;
    ArrayList<User> event_participants ;

    public User getEvent_host() {
        return event_host;
    }

    public void setEvent_host(User event_host) {
        this.event_host = event_host;
    }

    private User event_host;

    private long id;
    private String title;
    private String event_description;
    private Calendar event_date;
    private String event_location;

    private String event_comments;
    private String event_photo;


    public EventDetail() {
    }

    public EventDetail(Event event) {
        this.id= event.getId();
        this.title=event.getTitle();
        this.event_description = event.getEvent_description();
        this.event_date = event.getEvent_date();
        this.event_location = event.getEvent_location();
        this.event_photo = event.getEvent_photo();
        this.event_comments = event.getEvent_comments();
    }



    public ArrayList<PostDetail> getEvent_posts() {

        return event_posts;
    }

    public void setEvent_posts(ArrayList<PostDetail> event_posts) {
        this.event_posts = event_posts;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<User> getEvent_participants() {
        return event_participants;
    }

    public void setEvent_participants(ArrayList<User> event_participants) {
        this.event_participants = event_participants;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    public String getEvent_comments() {
        return event_comments;
    }

    public void setEvent_comments(String event_comments) {
        this.event_comments = event_comments;
    }



    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public Calendar getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Calendar event_date) {
        this.event_date = event_date;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
