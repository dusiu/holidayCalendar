//entites

package com.dusinski.holidaycalendar.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CalendarEvent {


    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @JsonProperty("title")
    private String eventName;
    private LocalDateTime start;
    private LocalDateTime end;

    public CalendarEvent() {
//        this.email="test_name";
//        this.email="test_email";
    }

    public CalendarEvent(String eventName, LocalDateTime start, LocalDateTime end) {
        this.eventName = eventName;
        this.start = start;
        this.end = end;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }


}
