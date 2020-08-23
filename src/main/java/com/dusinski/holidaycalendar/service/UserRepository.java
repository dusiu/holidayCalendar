package com.dusinski.holidaycalendar.service;

import com.dusinski.holidaycalendar.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public  interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    User findById(long id);
}
