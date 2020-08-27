package com.dusinski.holidaycalendar.customvalidator;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.customvalidator.CheckStartEndDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StartEndDateValidator implements ConstraintValidator<CheckStartEndDate, CalendarEvent> {

    @Override
    public void initialize(CheckStartEndDate constraint) {
    }

    @Override
    public boolean isValid(CalendarEvent calendarEvent, ConstraintValidatorContext context) {


        if (calendarEvent.getStart() == null || calendarEvent.getEnd()==null) {
            return false;
        }

    return (calendarEvent.getEnd().isAfter(calendarEvent.getStart()));
    }
}