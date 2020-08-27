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
    public String addEventForm(Model model){
        model.addAttribute("userList", userService.findAllUsers());
        return "user/showUsers";
    }

}
