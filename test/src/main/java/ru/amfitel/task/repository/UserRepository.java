package ru.amfitel.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.amfitel.task.entity.User;

/**
 * Created by Bublik on 31.03.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name=:name")
    User findByName(@Param("name") String name);

}
