//entites

package com.dusinski.holidaycalendar.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@CheckStartEndDate
public class CalendarEvent {


    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


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

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isStartBeforeEnd(){

        return start.isBefore(end);
    }

}
