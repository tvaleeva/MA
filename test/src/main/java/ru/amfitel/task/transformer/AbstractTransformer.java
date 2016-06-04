package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.AbstractDTO;
import ru.amfitel.task.entity.AbstractEntity;

/**
 * Created by Bublik on 27.03.2016.
 */
public abstract class AbstractTransformer<O extends AbstractEntity, D extends AbstractDTO> {

    public D transform (O object) {
        D dto = create();
        dto.setId(object.getId());
        return dto;

    }

    public void updateEntity (D dto, O entity ){

    }

    abstract D create ();

}
