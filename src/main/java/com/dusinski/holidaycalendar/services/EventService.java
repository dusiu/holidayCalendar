package com.dusinski.holidaycalendar.services;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.EventConfirmationToken;
import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.repository.EventConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {
    private static final String EMAIL_FROM = "confirmationholiday@gmail.com";
    @Autowired
    private CalendarEventRepository calendarEventRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventConfirmationTokenRepository eventConfirmationTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    public boolean sendEmail(String confirmationToken, String emailAddress){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailAddress);
        mailMessage.setSubject("Complete Registration of the Holiday Event");
        mailMessage.setFrom(EMAIL_FROM);
        mailMessage.setText("To confirm your event, please click here : "
                + "http://localhost:8090/event/confirm-event?token="+confirmationToken
        );
//        emailSenderService.sendEmail(mailMessage);
        return true;
    }

    public List<CalendarEvent> findEventsByUser(User user){
//        return calendarEventRepository.findByEventUser(user);
        return null;
    }

    public List<CalendarEvent> findEventsByCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return calendarEventRepository.findByEventUser(userService.returnUserByEmail(auth.getName()));
        return null;
    }

    public List<CalendarEvent>findAll(){
        return calendarEventRepository.findAll();
    }

    @Transactional
    public void deleteEventById(long eventId){
        eventConfirmationTokenRepository.deleteByCalendarEvent(calendarEventRepository.findById(eventId));
        calendarEventRepository.deleteById(eventId);
    }

    public void saveCalendarEvent(CalendarEvent calendarEvent){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (calendarEvent.getEventUser() == null)
//        {
//            calendarEvent.setEventUser(userService.returnUserByEmail(auth.getName()));
//        }
        calendarEventRepository.save(calendarEvent);

        EventConfirmationToken eventConfirmationToken = new EventConfirmationToken(calendarEvent);
        eventConfirmationTokenRepository.save(eventConfirmationToken);

        sendEmail(eventConfirmationToken.getEventConfirmationToken(),auth.getName());
    }

    public  CalendarEvent findEventById(long eventId){return calendarEventRepository.findById(eventId);   }


    public boolean tokenExist(String confirmationToken){
        EventConfirmationToken token = eventConfirmationTokenRepository.findByEventConfirmationToken(confirmationToken);
        return token!=null;
    }

    public void activateEvent(String confirmationToken){

        EventConfirmationToken token = eventConfirmationTokenRepository.findByEventConfirmationToken(confirmationToken);
        CalendarEvent calendarEvent = token.getCalendarEvent();
        calendarEvent.setEnabled(true);
        calendarEventRepository.save(calendarEvent);
    }

}



