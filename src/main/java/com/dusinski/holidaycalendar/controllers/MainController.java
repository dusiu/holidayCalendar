
package com.dusinski.holidaycalendar.controllers;

import com.dusinski.holidaycalendar.entities.User;
import com.dusinski.holidaycalendar.service.CalendarEventRepository;
import com.dusinski.holidaycalendar.service.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Autowired
    private ObjectMapper objectMapperCalendar = new ObjectMapper();

    @RequestMapping(path = "/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser(@RequestParam("name") String name
            , @RequestParam("email") String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User(name, email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(path = "/test")
    public @ResponseBody
    String testResponse() {

        return "Test String";
    }


    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("user", new User());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
    model.addAttribute("user", user);
        userRepository.save(user);
    return "result";
    }

    @GetMapping(path="/")
    public String calendarForm( Model model) throws JsonProcessingException {

        LocalDateTime testTime=  LocalDateTime.of(2020,8,21,0,0);

        model.addAttribute("eventList",
                objectMapperCalendar.writeValueAsString(calendarEventRepository.findByStart(testTime)));

        return "full-height";

    }
}
