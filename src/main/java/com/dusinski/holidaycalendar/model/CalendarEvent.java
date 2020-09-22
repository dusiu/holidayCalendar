//entites

package com.dusinski.holidaycalendar.model;

import com.dusinski.holidaycalendar.customvalidator.CheckStartEndDate;
import com.dusinski.holidaycalendar.enums.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@CheckStartEndDate
public class CalendarEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @NotBlank(message = "Event title may not be null")
    private String title;

    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "May not be null")
    private LocalDate start;

    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "May not be null")
    private LocalDate end;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User eventUser;

    private Boolean isEnabled;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

//    public User getEventUser() {
//        return eventUser;
//    }

//    public void setEventUser(User eventUser) {
//        this.eventUser = eventUser;
//    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
