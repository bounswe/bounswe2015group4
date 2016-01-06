package com.socialnow.Models;

import java.util.Calendar;

/**
 * Created by mugekurtipek on 06/01/16.
 */
public class Instant_Event {


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }


        private long id;

        Calendar date;

        public Instant_Event() {
        }



        int duration_in_minutes;

        String instant_event_description;

        String title;

        String instant_event_location;

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
