package socialnow.model;

import socialnow.forms.Event.Instant_Event_Form;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

/**
 * Created by Erdem on 12/12/2015.
 */
@Entity
@Table(name = "instant_events")
public class Instant_Event {

    @NotNull
    Calendar date;
    @NotNull
    int duration_in_minutes;
    @NotNull
    String instant_event_description;
    @NotNull
    String title;
    @NotNull
    String instant_event_location;
    @NotNull
    String instant_event_owner;

    public String getInstant_event_location() {
        return instant_event_location;
    }

    public void setInstant_event_location(String instant_event_location) {
        this.instant_event_location = instant_event_location;
    }

    public String getInstant_event_owner() {
        return instant_event_owner;
    }

    public void setInstant_event_owner(String instant_event_owner) {
        this.instant_event_owner = instant_event_owner;
    }

    public Instant_Event(Instant_Event_Form form) {
        date = Calendar.getInstance();
        duration_in_minutes = form.getDuration_in_minutes();
        title= form.getTitle();
        instant_event_description =form.getInstant_event_description();
    }

    public Instant_Event(Calendar date, int duration_in_minutes, String instant_event_description, String title) {
        this.date = date;
        this.duration_in_minutes = duration_in_minutes;
        this.instant_event_description = instant_event_description;
        this.title = title;

    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstant_event_description() {
        return instant_event_description;
    }

    public void setInstant_event_description(String instant_event_description) {
        this.instant_event_description = instant_event_description;
    }

    public int getDuration_in_minutes() {
        return duration_in_minutes;
    }

    public void setDuration_in_minutes(int duration_in_minutes) {
        this.duration_in_minutes = duration_in_minutes;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }




}
/*  int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
    int day = now.get(Calendar.DAY_OF_MONTH);
    int hour = now.get(Calendar.HOUR_OF_DAY);
    int minute = now.get(Calendar.MINUTE);
    int second = now.get(Calendar.SECOND);
    int millis = now.get(Calendar.MILLISECOND);*/