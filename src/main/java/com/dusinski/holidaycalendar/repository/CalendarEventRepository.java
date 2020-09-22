package com.dusinski.holidaycalendar.repository;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public  interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
    List<CalendarEvent> findByStart(LocalDate start);
//    List<CalendarEvent> findByEventUser(User user);
    CalendarEvent findById(long groupId);
    void deleteById(long id);
//    void deleteByEventUser(User user);

    List<CalendarEvent> findAll();

}
