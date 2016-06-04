package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Cabinet;
import ru.amfitel.task.entity.Floor;

/**
 * Created by Bublik on 27.03.2016.
 */
public class CabinetTransformer extends AbstractTransformer <Cabinet,CabinetDTO>{


    CabinetDTO create() {
        return new CabinetDTO();
    }

    @Override
    public CabinetDTO transform(Cabinet object) {
        CabinetDTO c = super.transform(object);
        c.setNumber(object.getNumber());
        c.setSquare(object.getSquare());
        c.setType(object.getType());
        c.setIdFloor(object.getFloorId().getId());
        return c;
    }

    @Override
    public void updateEntity(CabinetDTO dto, Cabinet entity) {
        super.updateEntity(dto, entity);
        entity.setNumber(dto.getNumber());
        entity.setSquare(dto.getSquare());
        entity.setType(dto.getType());
    }
}
