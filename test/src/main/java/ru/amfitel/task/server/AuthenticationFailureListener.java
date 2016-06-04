package ru.amfitel.task.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.amfitel.task.entity.UserLog;
import ru.amfitel.task.entity.User;
import ru.amfitel.task.repository.UserLogRepository;
import ru.amfitel.task.repository.UserRepository;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLogRepository userLogRepository;

    private Integer maxExemptions;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {

        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getName();

        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("can't find user");
        }
        UserLog userLog = new UserLog();
        userLog.setUser(user);
        userLog.setTime(new Date());
        userLogRepository.save(userLog);


        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);

        Integer countFailAttempt = userLogRepository.countFailAttempt(user.getId(), cal.getTime());
        Boolean blocked = countFailAttempt >= maxExemptions;
        user.setBlocked(blocked);
        //подсчитать кол-во попыток
        //обновить статус user
        userRepository.save(user);


        throw new UsernameNotFoundException("the number of remaining attempts: " + (maxExemptions - countFailAttempt));

    }

    public Integer getMaxExemptions() {
        return maxExemptions;
    }

    public void setMaxExemptions(Integer maxExemptions) {
        this.maxExemptions = maxExemptions;
    }
}
