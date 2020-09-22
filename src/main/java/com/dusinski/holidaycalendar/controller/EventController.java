package com.dusinski.holidaycalendar.controller;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.services.EventService;
import com.dusinski.holidaycalendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/show","/show/{userId}"})
    public String reloadShowEventsForm(@PathVariable(required = false) Long userId, Model model)
    {
        if (userId==null)
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userId=userService.returnUserByEmail(auth.getName()).getId();
        }
        model.addAttribute("userName",userService.returnUserById(userId));
        model.addAttribute("eventListByUser", eventService.findEventsByUser(userService.returnUserById(userId)));
        model.addAttribute("userList",userService.findAllUsers());
        return "/event/showEvents";

    }

    @RequestMapping(value = "/delete/{eventId}")
    public String deleteCalendarEvent(@PathVariable long eventId) {
        eventService.deleteEventById(eventId);
        return "redirect:/event/show";
    }

    @GetMapping(path = "/addEvent")
    public String showAddEventForm() {
        return "/event/addEventForm";
    }

    @PostMapping(path = "/addEvent")
    public String addEvent(@RequestBody @Valid CalendarEvent calendarEvent, BindingResult result) {
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

    @GetMapping("/getEvents")
    public List<CalendarEvent> test() {
        return eventService.findAll();
    }
}
