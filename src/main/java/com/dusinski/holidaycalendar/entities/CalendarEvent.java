//entites

package com.dusinski.holidaycalendar.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CalendarEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer groupId;
    @JsonProperty("title")
    private String eventName;
    private String start;
    private String end;

    public CalendarEvent() {
//        this.email="test_name";
//        this.email="test_email";
    }

    public CalendarEvent(String eventName, String start, String end) {
        this.eventName = eventName;
        this.start = start;
        this.end = end;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "groupId=" + groupId +
                ", eventName='" + eventName + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

}
