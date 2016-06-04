package ru.amfitel.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.amfitel.task.entity.Build;

/**
 * Created by Bublik on 27.03.2016.
 */
public interface BuildRepository extends CrudRepository<Build,Long> {
}
