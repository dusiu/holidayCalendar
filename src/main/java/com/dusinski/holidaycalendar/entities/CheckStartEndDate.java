package com.dusinski.holidaycalendar.entities;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = StartEndDateValidator.class)
@Documented
public @interface CheckStartEndDate {
    String message() default "End date must be after begin date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[]payload() default {};


}
