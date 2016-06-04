package ru.amfitel.task.repository;

import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.amfitel.task.entity.LoginAttempt;
import ru.amfitel.task.entity.User;

import java.lang.annotation.Native;
import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
public interface LoginAttemptRepository  extends CrudRepository<LoginAttempt,Long>{


    @Query("select count(u.Id) from LoginAttempt u where u.time > :time and u.id_user.Id =:id")
    Integer countFailAttempt(@Param("id") Long id, @Param("time") Date time);

    @Query("delete from LoginAttempt u where u.id_user.id = :id")
    @Modifying
    @Transactional
    void deleteUserAttempt(@Param("id") Long id);


}
