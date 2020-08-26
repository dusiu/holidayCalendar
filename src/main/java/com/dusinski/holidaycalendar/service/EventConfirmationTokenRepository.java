package com.dusinski.holidaycalendar.service;

import com.dusinski.holidaycalendar.entities.EventConfirmationToken;
import org.springframework.data.repository.CrudRepository;

public interface EventConfirmationTokenRepository extends CrudRepository<EventConfirmationToken, String> {

EventConfirmationToken findByEventConfirmationToken(String eventConfirmationToken);
}
