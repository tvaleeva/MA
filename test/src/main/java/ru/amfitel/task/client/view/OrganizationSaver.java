package ru.amfitel.task.client.view;

import ru.amfitel.task.client.callback.AsyncCallback;
import ru.amfitel.task.client.dto.OrganizationDto;

/**
 * @author alexanderdemin.
 */
public interface OrganizationSaver {

    void save(OrganizationDto organizationDto, AsyncCallback<Void> callback);

}
