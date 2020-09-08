package com.dusinski.holidaycalendar.controller;


import com.dusinski.holidaycalendar.serializer.CalendarEventSerializer;
import com.dusinski.holidaycalendar.services.EventService;
import com.dusinski.holidaycalendar.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;


@Controller
@RequestMapping
public class MainController {



    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @GetMapping(path = "/")
    public String calendarForm(Model model) throws JsonProcessingException {

//        LocalDateTime testTime=  LocalDateTime.of(2020,8,21,0,0);

        LocalDate testTime = LocalDate.parse("2020-01-04");


        ObjectMapper objectMapperCalendar = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(new CalendarEventSerializer());
        objectMapperCalendar.registerModule(simpleModule);

        model.addAttribute("eventList",
//                objectMapperCalendar.writeValueAsString(calendarEventRepository.findByEventUser(userService.returnUserById(1))));

        objectMapperCalendar.writeValueAsString(eventService.findAll()));

        System.out.println(eventService.findAll());


        return "index";
    }

    @GetMapping(path = "/login")
    public String loginForm(Model model) {

        model.addAttribute("usersList", userService.findAllUsers());

        return "security/login";
    }

    @GetMapping(path = "/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/accessDeny")
    public String accessDeny(Model model) {
        return "accessError";
    }

}
