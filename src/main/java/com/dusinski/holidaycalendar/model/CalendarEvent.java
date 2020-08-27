//entites

package com.dusinski.holidaycalendar.model;

import com.dusinski.holidaycalendar.customvalidator.CheckStartEndDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Entity
@CheckStartEndDate
public class CalendarEvent {


    @Id
    @JsonProperty("event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;


    @JsonProperty("title")
    @NotBlank(message="Event title may not be null")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "May not be null")
    private LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "May not be null")
    private LocalDate  end;

    private long userId;

    private boolean isEnabled;

//    public CalendarEvent() {
//        this.email="test_name";
//        this.email="test_email";
//    }

//    @ConsistentDateParameters
//    public CalendarEvent(String title, LocalDate start, LocalDate end) {
//        this.title = title;
//        this.start = start;
//        this.end = end;
//    }

    public long getEventId() {
        return this.eventId;
    }

    public void setEventId(long id) {
        this.eventId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String eventName) {
        this.title = eventName;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public boolean isStartBeforeEnd(){

        return start.isBefore(end);
    }

}
