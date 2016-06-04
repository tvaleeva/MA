package ru.amfitel.task.server;

import org.hibernate.validator.engine.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;
import ru.amfitel.task.entity.Build;
import ru.amfitel.task.entity.Cabinet;
import ru.amfitel.task.entity.Floor;
import ru.amfitel.task.repository.BuildRepository;
import ru.amfitel.task.repository.CabinetRepository;
import ru.amfitel.task.repository.FloorRepository;
import ru.amfitel.task.transformer.BuildTransformer;
import ru.amfitel.task.transformer.CabinetTransformer;
import ru.amfitel.task.transformer.FloorTransformer;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Bublik on 27.03.2016.
 */
@Service("buildingService")
public class BuildingService implements ru.amfitel.task.client.service.BuildingService {

    @Autowired
    private BuildRepository buildRepository;

    @Autowired
    private FloorRepository floorRepository;

    @Autowired
    private CabinetRepository cabinetRepository;

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();



    public List<BuildDTO> loadBuildings() {
        Iterable<Build> iterable = buildRepository.findAll();
        Stream<Build> stream = StreamSupport.stream(iterable.spliterator(), false);
        BuildTransformer transformer = new BuildTransformer();
        List<BuildDTO> result = stream.map(transformer::transform).collect(Collectors.toList());
        return result;
    }

    @Override
    public BuildDTO saveBuildDTO(BuildDTO b) throws ConstraintViolationException {

        Set<ConstraintViolation<BuildDTO>> violations = validator.validate(b,
                Default.class);
        if (!violations.isEmpty()) {
            Set<ConstraintViolation<?>> temp = new HashSet<ConstraintViolation<?>>(
                    violations);
            throw new ConstraintViolationException(temp);
        }
        Build build;
        if (b.getId() == null) {
            build = new Build();
        } else {
            build = buildRepository.findOne(b.getId());
        }
        BuildTransformer transformer = new BuildTransformer();
        transformer.updateEntity(b, build);
        build= buildRepository.save(build);
        return transformer.transform(build);


    }

    @Override
    public CabinetDTO saveCabinetDTO(CabinetDTO c) throws ConstraintViolationException {
        Set<ConstraintViolation<CabinetDTO>> violations = validator.validate(c,
                Default.class);
        if (!violations.isEmpty()) {
            Set<ConstraintViolation<?>> temp = new HashSet<ConstraintViolation<?>>(
                    violations);
            throw new ConstraintViolationException(temp);
        }
        Cabinet cabinet;

        if(c.getId()==null){
            cabinet = new Cabinet();
        } else
        {
            cabinet = cabinetRepository.findOne(c.getId());
        }

        CabinetTransformer transformer = new CabinetTransformer();
        cabinet.setFloorId(floorRepository.findOne(c.getIdFloor()));
        transformer.updateEntity(c,cabinet);
        cabinet = cabinetRepository.save(cabinet);
        return transformer.transform(cabinet);
    }


    @Override
    public FloorDTO saveFloorDTO(FloorDTO f) throws ConstraintViolationException{
        Set<ConstraintViolation<FloorDTO>> violations = validator.validate(f,
                Default.class);
        if (!violations.isEmpty()) {
            Set<ConstraintViolation<?>> temp = new HashSet<ConstraintViolation<?>>(
                    violations);
            throw new ConstraintViolationException(temp);
        }
        Floor floor;
        if (f.getId() == null) {
            floor = new Floor();
        } else {
            floor = floorRepository.findOne(f.getId());
        }
        FloorTransformer transformer = new FloorTransformer();
        floor.setBuildId(buildRepository.findOne(f.getIdBuild()));
        transformer.updateEntity(f,floor);
        floor = floorRepository.save(floor);
        return transformer.transform(floor);
    }


    @Override
    public CabinetDTO deleteCabinet(Long id) {
        cabinetRepository.delete(id);
        CabinetDTO cabinetDTO = new CabinetDTO();
        cabinetDTO.setId(id);
        return cabinetDTO;
    }

    @Override
    public BuildDTO deleteBuild(Long id) {
        buildRepository.delete(id);
        BuildDTO buildDTO = new BuildDTO();
        buildDTO.setId(id);
        return buildDTO;
    }

    @Override
    public FloorDTO deleteFloor(Long id) {
        floorRepository.delete(id);
        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setId(id);
        return floorDTO;
    }

    @Override
    public ValidationSupport dummy() {
        return null;
    }


}
