package com.dusinski.holidaycalendar.controller;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(path = "/show")
    public String addEventForm(Model model) {

        model.addAttribute("eventListByUser", eventService.findEventsByUser());

        return "/event/showEvents";
    }

    @RequestMapping(value = "/delete/{eventId}")
    public String deleteCalendarEvent(@PathVariable long eventId) {
        eventService.deleteEventById(eventId);
        return "redirect:/event/show";
    }

    @GetMapping(path = "/addEvent")
    public String showAddEventForm(CalendarEvent calendarEvent) {
        return "/event/addEventForm";
    }

    @PostMapping(path = "/addEvent")
    public String checkEvent(@Valid CalendarEvent calendarEvent, BindingResult result) {
        if (result.hasErrors()) {
            return "/event/addEventForm";
        }

        eventService.saveCalendarEvent(calendarEvent);
        return "/event/eventWaitForVerification";
    }

    @RequestMapping(value = "/confirm-event", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmEvent(Model model, @RequestParam("token") String confirmationToken) {

        if (eventService.tokenExist(confirmationToken)) {

            eventService.activateEvent(confirmationToken);

            return "/event/eventVerified";
        } else {
            return "/event/eventVerificationError";
        }
    }
}
