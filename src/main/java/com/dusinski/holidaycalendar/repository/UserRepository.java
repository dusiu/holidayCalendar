package com.dusinski.holidaycalendar.repository;

import com.dusinski.holidaycalendar.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public  interface UserRepository extends CrudRepository<User, Long> {
//    List<User> findByName(String name);
    User findById(long id);
    User findByEmail(String email);
    List<User> findUserByIsAdmin(boolean isAdmin);

}
