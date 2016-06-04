package ru.amfitel.task.transformer;

import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.entity.Floor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
public class BuildTransformer extends AbstractTransformer<Build, BuildDTO> {


    BuildDTO create() {
        return new BuildDTO();
    }

    @Override
    public BuildDTO transform(Build object) {
        BuildDTO buildDTO = super.transform(object);
        buildDTO.setName(object.getName());
        buildDTO.setAddress(object.getAddress());
        buildDTO.setCountFloor(object.getCountFloor());
        buildDTO.setDate(object.getDate());
        List<FloorDTO> floors = new ArrayList<>();
        for (Floor f : object.getFloors()) {
            floors.add(new FloorTransformer().transform(f));
        }
        buildDTO.setFloors(floors);
        buildDTO.setMaterial(object.getMaterial());
        return buildDTO;
    }

    @Override
    public void updateEntity(BuildDTO dto, Build entity) {
        super.updateEntity(dto, entity);
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setAddress(dto.getAddress());
        entity.setCountFloor(dto.getCountFloor());
        entity.setMaterial(dto.getMaterial());
    }
}
