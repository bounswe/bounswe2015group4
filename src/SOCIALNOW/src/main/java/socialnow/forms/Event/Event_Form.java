package socialnow.forms.Event;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by mertcan on 22.11.2015.
 *
 *
 *
 */
public class Event_Form {
    private String title;

    private String visibleTo = "all";
    private String event_description;

    private Date event_start_date;

    private Date event_end_date;

    private String event_location;

    private String event_host_token;

    private String event_participants;

    private String event_comments;

    public Date getEvent_start_date() {
        return event_start_date;
    }

    public void setEvent_start_date(Date event_start_date) {
        this.event_start_date = event_start_date;
    }

    public Date getEvent_end_date() {
        return event_end_date;
    }

    public void setEvent_end_date(Date event_end_date) {
        this.event_end_date = event_end_date;
    }

    private String event_photo;

    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
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



    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_host_token() {
        return event_host_token;
    }

    public Event_Form() {
    }

    public void setEvent_host_token(String event_host_token) {
        this.event_host_token = event_host_token;
    }

    public String getEvent_participants() {
        return event_participants;
    }

    public void setEvent_participants(String event_participants) {
        this.event_participants = event_participants;
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


    public String getVisibleTo() {
        return visibleTo;
    }

    public void setVisibleTo(String visibleTo) {
        this.visibleTo = visibleTo;
    }

}