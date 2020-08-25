package com.dusinski.holidaycalendar.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;

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