package ru.amfitel.task.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.amfitel.task.entity.UserLog;
import ru.amfitel.task.entity.User;

import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
public interface UserLogRepository extends CrudRepository<UserLog,Long>{


    @Query("select count(ul.id) from UserLog ul where ul.time > :time and ul.user.id =:id")
    Integer countFailAttempt(@Param("id") Long id, @Param("time") Date time);

    @Query("delete from UserLog ul where ul.user.id = :id")
    @Modifying
    @Transactional
    void deleteUserAttempt(@Param("id") Long user);


}
