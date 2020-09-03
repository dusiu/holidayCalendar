//entites

package com.dusinski.holidaycalendar.model;

import com.dusinski.holidaycalendar.customvalidator.CheckStartEndDate;
import com.dusinski.holidaycalendar.enums.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@CheckStartEndDate
public class CalendarEvent {


    @Id
    @JsonProperty("event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;


    @JsonProperty("title")
    @NotBlank(message = "Event title may not be null")
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "May not be null")
    private LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "May not be null")
    private LocalDate end;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User eventUser;

    private boolean isEnabled;

    @Enumerated(EnumType.STRING)
    private EventType eventType;


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


    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public User getEventUser() {
        return eventUser;
    }

    public void setEventUser(User eventUser) {
        this.eventUser = eventUser;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }


}
