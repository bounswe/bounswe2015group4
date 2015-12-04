package socialnow.model;

import socialnow.Utils.Error_JSON;
import socialnow.forms.Event.Event_Form;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mertcan on 22.11.2015.
 */
@Entity
@Table(name = "events")
public class Event implements SearchReturn {
    // ------------------------
    // PRIVATE FIELDS

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String title;

    @NotNull
    @Column(length = 2000)
    private String event_description;

    @NotNull
    private Date event_date;

    @NotNull
    private String event_location;

    @NotNull
    private String event_host_token;

    @Column(name = "event_participants", length = 2000)
    public  String event_participants ;


    @Column(name = "tags", length = 5000)
    private String tags = "";



    @Column(name = "event_posts", length = 5000)
    private String event_posts = "";


    @Column(name = "event_comments")
    private String event_comments;

    @Column(name = "event_photo")
    private String event_photo;


    public String getEvent_posts() {
        return event_posts;
    }

    public void setEvent_posts(String event_posts) {
        this.event_posts = event_posts;
    }
    public Event(Event_Form e_f) {
        title = e_f.getTitle();
        event_description = e_f.getEvent_description();
        event_date = e_f.getEvent_date();
        event_location = e_f.getEvent_location();
        event_host_token = e_f.getEvent_host_token();
        event_participants = e_f.getEvent_participants();
        event_comments = e_f.getEvent_comments();
        event_photo = e_f.getEvent_photo();
        if(e_f.getTags().startsWith(",")){
            tags= e_f.getTags();
        } else{
            tags = ","+e_f.getTags();
        }
        event_participants = "";
        event_posts = "";


    }
    public  Event(){

    }

    public String getType() {
        return type;
    }

    @Transient
    String type;


    public Event(Error_JSON e) {
        id = -1;
        title = e.toString();
    }

    public List<User> getEvent_participant_users() {
        return event_participant_users;
    }

    public void setEvent_participant_users(List<User> users) {
        this.event_participant_users = users;
    }


    @Transient
    List<User> event_participant_users = new ArrayList<User>();
    public void  fillUsers(List<User> temp){
        event_participant_users.clear();
         event_participant_users.addAll(temp);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", event_description='" + event_description + '\'' +
                ", event_date=" + event_date +
                ", event_location='" + event_location + '\'' +
                ", event_host_token='" + event_host_token + '\'' +
                ", event_participants='" + event_participants + '\'' +
                ", event_comments='" + event_comments + '\'' +
                ", event_photo='" + event_photo + '\'' +
                '}';
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_host_token() {
        return event_host_token;
    }

    public void setEvent_host_token(String event_host_token) {
        this.event_host_token = event_host_token;
    }

    public String getEvent_comments() {
        return event_comments;
    }

    public void setEvent_comments(String event_comments) {
        this.event_comments = event_comments;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    // ------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public void setType(String type) {
        this.type=type;
    }
}
