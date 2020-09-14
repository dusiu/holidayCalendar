package com.dusinski.holidaycalendar.controller;

import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/show")
    public String showUsers(Model model) {
        model.addAttribute("userList", userService.findAllUsers());
        return "user/showUsers";
    }

    @RequestMapping(value = "/delete/{userId}")
    public String deleteUser(@PathVariable long userId) {
        userService.deleteUserbyID(userId);
        return "redirect:/user/show";
    }

    @GetMapping(path = "/add")
    public String addUserForm(User user, Model model) {
        model.addAttribute("adminList", userService.findAllManagers());

        return "user/addUser";
    }

    @PostMapping(path = "add")
    public String addUserForm(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "user/addUser";
        }

        System.out.println("User's Manager: "+user.getUsersManager().getEmail());
        userService.addUser(user);

        return "redirect:/user/show";
    }


}
