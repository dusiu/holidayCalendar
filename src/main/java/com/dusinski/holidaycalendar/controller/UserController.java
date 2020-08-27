package com.dusinski.holidaycalendar.controller;

import com.dusinski.holidaycalendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path="/show")
    public String addUserForm(Model model){
        model.addAttribute("userList", userService.findAllUsers());
        return "user/showUsers";
    }

    @RequestMapping(value="/delete/{userId}")
    public String deleteUser(@PathVariable long userId){
        userService.deleteUserbyID(userId);
        return "redirect:/user/show";
    }



//    @RequestMapping(value="/delete/{eventId}")
//    public String deleteCalendarEvent(@PathVariable long eventId){
//        calendarEventRepository.deleteById(eventId);
//        return "redirect:/show";
//    }





}
