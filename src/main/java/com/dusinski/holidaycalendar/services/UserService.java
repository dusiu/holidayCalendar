package com.dusinski.holidaycalendar.services;

import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserbyID(long userID) {
        User tempUser = returnUserById(userID);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (tempUser.getEmail()==auth.getName()) {
        System.out.println("Prevent against deleting by yourself");
        }

//        calendarEventRepository.deleteByEventUser(tempUser);
        userRepository.deleteById(userID);
    }

    public User returnUserById(long userId) {
        return userRepository.findById(userId);
    }

    public User returnUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public Iterable<User> findAllManagers() {
        return userRepository.findUserByIsAdmin(true);
    }
}
