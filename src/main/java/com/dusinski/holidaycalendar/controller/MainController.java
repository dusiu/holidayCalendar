
package com.dusinski.holidaycalendar.controller;

import com.dusinski.holidaycalendar.model.CalendarEvent;
import com.dusinski.holidaycalendar.model.EventConfirmationToken;
import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.services.EmailSenderService;
import com.dusinski.holidaycalendar.repository.EventConfirmationTokenRepository;
import com.dusinski.holidaycalendar.repository.UserRepository;
import com.dusinski.holidaycalendar.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;


@Controller
@RequestMapping
public class MainController {



    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Autowired
    private ObjectMapper objectMapperCalendar = new ObjectMapper();

    @Autowired
    private EventConfirmationTokenRepository eventConfirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserService userService;

//    @RequestMapping(path = "/add") // Map ONLY POST Requests
//    public @ResponseBody
//    String addNewUser(@RequestParam("name") String name
//            , @RequestParam("email") String email) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        User n = new User(name, email);
//        userRepository.save(n);
//        return "Saved";
//    }

//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
//
//        return userRepository.findAll();
//    }

//    @GetMapping(path = "/test")
//    public @ResponseBody
//    String testResponse() {
//
//        return "Test String";
//    }
//
//
//    @GetMapping("/greeting")
//    public String greetingForm(Model model) {
//        model.addAttribute("user", new User());
//        return "greeting";
//    }
//
//    @PostMapping("/greeting")
//    public String greetingSubmit(@ModelAttribute User user, Model model) {
//    model.addAttribute("user", user);
//        userRepository.save(user);
//    return "result";
//    }

    @GetMapping(path="/")
    public String calendarForm( Model model) throws JsonProcessingException {

//        LocalDateTime testTime=  LocalDateTime.of(2020,8,21,0,0);

        LocalDate testTime = LocalDate.parse("2020-01-04");
        model.addAttribute("eventList",
                objectMapperCalendar.writeValueAsString(calendarEventRepository.findByEventUser(userService.returnUserById(1))));

        return "index";

    }

    @GetMapping(path="/login")
    public String loginForm(Model model){
        return "security/login";
    }

//    @PostMapping(path="/login")
//    public String signIn(Model model)
//    {
//        return "redirect:/";
//    }

    @GetMapping(path="/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping(path="/show")
    public String addEventForm(Model model){
            model.addAttribute("eventListByUser", calendarEventRepository.findByEventUser(userService.returnUserById(1)));
        return "showEvents";
    }

    @RequestMapping(value="/delete/{eventId}")
    public String deleteCalendarEvent(@PathVariable long eventId){
        calendarEventRepository.deleteById(eventId);
        return "redirect:/show";
    }

    @GetMapping(path="/addEvent")
    public String showAddEventForm(CalendarEvent calendarEvent){
        return "addEventForm";
    }

    @PostMapping(path="/addEvent")
    public String checkEvent(@Valid CalendarEvent calendarEvent, BindingResult result){
        if (result.hasErrors()){
            return "addEventForm";
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
        + "http://localhost:8090/confirm-account?token="+eventConfirmationToken.getEventConfirmationToken()
        );

        emailSenderService.sendEmail(mailMessage);

        //model.addAttribute("calendarEvent", calendarEventRepository.findByUserId(1));
        return "redirect:/show";
    }


    @RequestMapping(value="/confirm-account", method={RequestMethod.GET, RequestMethod.POST})
    public String confirmEvent(Model model, @RequestParam("token") String confirmationToken){
        EventConfirmationToken token = eventConfirmationTokenRepository.findByEventConfirmationToken(confirmationToken);

        if (token!= null)
        {
            CalendarEvent calendarEvent = calendarEventRepository.findById(token.getCalendarEvent().getEventId());
            System.out.println("Calendar event Id:"+calendarEvent.getEventId());
            System.out.println("before token:"+calendarEvent.isEnabled());
            calendarEvent.setEnabled(true);
            System.out.println("after token:"+calendarEvent.isEnabled());
            calendarEventRepository.save(calendarEvent);
            return "accountVerified";

        }
        else
        {
            return "verificationError";

        }

    }

}
