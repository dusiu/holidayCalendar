package com.dusinski.holidaycalendar.entities;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Date;
import java.util.UUID;

@Entity
public class EventConfirmationToken {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenId;

    @Column(name="confirmation_token")
    private String eventConfirmationToken;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = CalendarEvent.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="event_id")
    private CalendarEvent calendarEvent;




    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public String getEventConfirmationToken() {
        return eventConfirmationToken;
    }

    public void setEventConfirmationToken(String eventConfirmationToken) {
        this.eventConfirmationToken = eventConfirmationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public CalendarEvent getCalendarEvent() {
        return calendarEvent;
    }

    public void setCalendarEvent(CalendarEvent calendarEvent) {
        this.calendarEvent = calendarEvent;
    }


//    public EventConfirmationToken() {
//    }


public EventConfirmationToken() {

}

    public  EventConfirmationToken(CalendarEvent calendarEvent){
        this.calendarEvent = calendarEvent;
        createdDate= new Date();
        eventConfirmationToken = UUID.randomUUID().toString();

    }

}
