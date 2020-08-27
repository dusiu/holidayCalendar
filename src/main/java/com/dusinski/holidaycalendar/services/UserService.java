package com.dusinski.holidaycalendar.services;

import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers(){

        return userRepository.findAll();

    }
}
