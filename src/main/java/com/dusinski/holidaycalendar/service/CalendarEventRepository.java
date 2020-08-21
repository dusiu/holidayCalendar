package com.dusinski.holidaycalendar.service;

import com.dusinski.holidaycalendar.entities.CalendarEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public  interface CalendarEventRepository extends CrudRepository<CalendarEvent, Integer> {
    List<CalendarEvent> findByStart(String start);
    CalendarEvent findByGroupId(Integer groupId);
}
