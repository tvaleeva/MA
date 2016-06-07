package ru.amfitel.task.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.amfitel.task.entity.Organization;

import java.util.List;

/**
 * @author alexanderdemin.
 */
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

    @Query("select o from Organization o where o.name like :name")
    List<Organization> findAllByName(@Param("name") String name);

}
