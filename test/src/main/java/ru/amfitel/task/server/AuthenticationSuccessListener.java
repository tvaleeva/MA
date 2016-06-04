package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.UserLogRepository;
import ru.amfitel.task.repository.UserRepository;

/**
 * Created by Bublik on 01.04.2016.
 */
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLogRepository loginAttempt;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        String username = authenticationSuccessEvent.getAuthentication().getName();
        User user = userRepository.findByName(username);
        loginAttempt.deleteUserAttempt(user.getId());
    }
}
