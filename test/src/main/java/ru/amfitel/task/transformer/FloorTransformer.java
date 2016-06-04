package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Cabinet;
import ru.amfitel.task.entity.Floor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
public class FloorTransformer extends AbstractTransformer <Floor,FloorDTO>{


    FloorDTO create() {
        return new FloorDTO();
    }

    @Override
    public FloorDTO transform(Floor object) {
        FloorDTO floorDTO = super.transform(object);
        floorDTO.setCountCabinet(object.getCountCabinet());
        floorDTO.setNumber(object.getNumber());
        floorDTO.setSquare(object.getSquare());
        floorDTO.setType(object.getType());
        List<CabinetDTO> cabinets = new ArrayList<>();
        for (Cabinet c : object.getCabinets()) {
            cabinets.add(new CabinetTransformer().transform(c));
        }
        floorDTO.setIdBuild(object.getBuildId().getId());
        floorDTO.setCabinets(cabinets);

        return floorDTO;
    }

    @Override
    public void updateEntity(FloorDTO dto, Floor entity) {
        super.updateEntity(dto, entity);
        entity.setNumber(dto.getNumber());
        entity.setCountCabinet(dto.getCountCabinet());
        entity.setType(dto.getType());
    }
}
