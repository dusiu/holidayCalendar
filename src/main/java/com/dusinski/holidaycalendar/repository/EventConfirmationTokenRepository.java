package com.dusinski.holidaycalendar.repository;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.EventConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface EventConfirmationTokenRepository extends CrudRepository<EventConfirmationToken, String> {

EventConfirmationToken findByEventConfirmationToken(String eventConfirmationToken);

void deleteByCalendarEvent(CalendarEvent calendarEvent);

}
