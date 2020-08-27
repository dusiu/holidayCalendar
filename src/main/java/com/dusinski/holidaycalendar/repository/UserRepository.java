package com.dusinski.holidaycalendar.repository;

import com.dusinski.holidaycalendar.model.User;
import org.springframework.data.repository.CrudRepository;


public  interface UserRepository extends CrudRepository<User, Long> {
//    List<User> findByName(String name);
    User findById(long id);
}
