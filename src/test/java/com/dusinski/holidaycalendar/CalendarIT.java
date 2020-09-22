package com.dusinski.holidaycalendar;

import com.dusinski.holidaycalendar.controller.EventController;
import com.dusinski.holidaycalendar.enums.EventType;
import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CalendarIT {

    @Autowired
    private EventController eventController;

    @Test
    public void testWholeApplicationFlow() throws JsonProcessingException {
        testAddEvent();
    }

    private void testAddEvent() throws JsonProcessingException {
        //dodawanie eventu
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setStart(LocalDate.now());
        calendarEvent.setEventType(EventType.HOLIDAY);
        calendarEvent.setEnd(LocalDate.now().plusDays(1));
        String title = "test title";
        calendarEvent.setTitle(title);
        TestRestTemplate template = new TestRestTemplate();
        String s = template.postForObject("http://localhost:8090/event/addEvent", calendarEvent, String.class);
        CalendarEvent[] events = template.getForObject("http://localhost:8090/event/getEvents", CalendarEvent[].class);

        assertThat(events).hasSize(1);
        assertThat(events[0].getTitle()).isEqualTo(title);
    }
}
