package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.OrganizationDto;
import ru.amfitel.task.entity.Organization;

/**
 * @author alexanderdemin.
 */
public class OrganizationTransformer extends AbstractTransformer<Organization, OrganizationDto> {

    private static OrganizationTransformer instance;

    public static synchronized OrganizationTransformer getInstance( ) {
        if (instance == null)
            instance = new OrganizationTransformer();
        return instance;
    }

    private OrganizationTransformer() {
        //prevent new instance creation
    }


    @Override
    OrganizationDto create() {
        return new OrganizationDto();
    }

    @Override
    public OrganizationDto transform(Organization object) {
        OrganizationDto organizationDto = super.transform(object);
        organizationDto.setInn(object.getInn());
        organizationDto.setName(object.getName());
        return organizationDto;
    }

    @Override
    public void updateEntity(OrganizationDto dto, Organization entity) {
        super.updateEntity(dto, entity);
        entity.setName(dto.getName());
        entity.setInn(dto.getInn());
    }
}
