package ru.amfitel.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.amfitel.task.entity.Floor;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */
public interface FloorRepository extends CrudRepository<Floor,Long> {
}
