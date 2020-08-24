package com.dusinski.holidaycalendar.service;

import com.dusinski.holidaycalendar.entities.CalendarEvent;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;


public  interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
    List<CalendarEvent> findByStart(LocalDateTime start);
    List<CalendarEvent> findByUserId(long userId);
    CalendarEvent findById(long groupId);

}
