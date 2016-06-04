package ru.amfitel.task.repository;

import org.springframework.data.repository.CrudRepository;
import ru.amfitel.task.entity.Organization;

/**
 * @author alexanderdemin.
 */
public interface OrganizationRepository extends CrudRepository<Organization, Long> {
}
