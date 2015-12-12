package socialnow.forms.Event;

/**
 * Created by Erdem on 12/12/2015.
 */
public class Instant_Event_Form {
    int duration_in_minutes;
    String instant_event_description;
    String title;
    String instant_event_location;
    String instant_event_owner;
    public String getInstant_event_owner() {
        return instant_event_owner;
    }

    public void setInstant_event_owner(String instant_event_owner) {
        this.instant_event_owner = instant_event_owner;
    }

    public String getInstant_event_location() {
        return instant_event_location;
    }

    public void setInstant_event_location(String instant_event_location) {
        this.instant_event_location = instant_event_location;
    }

    public int getDuration_in_minutes() {
        return duration_in_minutes;
    }

    public void setDuration_in_minutes(int duration_in_minutes) {
        this.duration_in_minutes = duration_in_minutes;
    }

    public String getInstant_event_description() {
        return instant_event_description;
    }

    public void setInstant_event_description(String instant_event_description) {
        this.instant_event_description = instant_event_description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
