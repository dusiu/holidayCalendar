package com.dusinski.holidaycalendar.controller;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.EventConfirmationToken;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.repository.EventConfirmationTokenRepository;
import com.dusinski.holidaycalendar.services.EmailSenderService;
import com.dusinski.holidaycalendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path="/event")
public class EventController {

    @Autowired
    private CalendarEventRepository calendarEventRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventConfirmationTokenRepository eventConfirmationTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping(path="/show")
    public String addEventForm(Model model){
        model.addAttribute("eventListByUser", calendarEventRepository.findByEventUser(userService.returnUserById(1)));
        return "/event/showEvents";
    }

    @RequestMapping(value="/delete/{eventId}")
    public String deleteCalendarEvent(@PathVariable long eventId){
        calendarEventRepository.deleteById(eventId);
        return "redirect:/event/show";
    }

    @GetMapping(path="/addEvent")
    public String showAddEventForm(CalendarEvent calendarEvent){
        return "/event/addEventForm";
    }

    @PostMapping(path="/addEvent")
    public String checkEvent(@Valid CalendarEvent calendarEvent, BindingResult result){
        if (result.hasErrors()){
            return "/event/addEventForm";
        }
        calendarEvent.setEventUser(userService.returnUserById(1));

        calendarEventRepository.save(calendarEvent);

        EventConfirmationToken eventConfirmationToken = new EventConfirmationToken(calendarEvent);
        eventConfirmationTokenRepository.save(eventConfirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("dominik.dusinski@gmail.com");
        mailMessage.setSubject("Complete Registration of the Holiday Event");
        mailMessage.setFrom("confirmationholiday@gmail.com");
        mailMessage.setText("To confirm your event, please click here : "
                + "http://localhost:8090/event/confirm-event?token="+eventConfirmationToken.getEventConfirmationToken()
        );

        emailSenderService.sendEmail(mailMessage);

        return "/event/eventWaitForVerification";
    }
    @RequestMapping(value="/confirm-event", method={RequestMethod.GET, RequestMethod.POST})
    public String confirmEvent(Model model, @RequestParam("token") String confirmationToken){
        EventConfirmationToken token = eventConfirmationTokenRepository.findByEventConfirmationToken(confirmationToken);

        if (token!= null)
        {
            CalendarEvent calendarEvent = calendarEventRepository.findById(token.getCalendarEvent().getEventId());
            calendarEvent.setEnabled(true);
            calendarEventRepository.save(calendarEvent);
            return "/event/eventVerified";

        }
        else
        {
            return "/event/eventVerificationError";

        }
    }
}
