package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.UserLogRepository;
import ru.amfitel.task.repository.UserRepository;

import java.util.Collections;

/**
 * Created by Bublik on 31.03.2016.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserLogRepository loginAttempt;



    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(), true, true, true, Boolean.FALSE.equals(user.getBlocked()), Collections.emptySet());
    }


}
