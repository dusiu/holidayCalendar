package com.dusinski.holidaycalendar.services;

import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.CalendarEventRepository;
import com.dusinski.holidaycalendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUserbyID(long userID){
        calendarEventRepository.deleteByEventUser(returnUserById(userID));
        userRepository.deleteById(userID);
    }

    public User returnUserById(long userId){
        return userRepository.findById(userId);
    }

    public void addUser( User user){
        userRepository.save(user);
    }
}
