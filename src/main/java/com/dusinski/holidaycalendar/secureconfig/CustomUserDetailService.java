package com.dusinski.holidaycalendar.secureconfig;


import com.dusinski.holidaycalendar.model.User;
import com.dusinski.holidaycalendar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);


        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        if (user.isAdmin()) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword()).authorities("ADMIN").build();
            return userDetails;
        } else {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword()).authorities("USER").build();

            return userDetails;
        }




    }

}
