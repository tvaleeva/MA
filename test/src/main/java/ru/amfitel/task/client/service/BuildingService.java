package ru.amfitel.task.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.hibernate.validator.engine.ValidationSupport;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
@RemoteServiceRelativePath("springGwtServlet/buildingService")
public interface BuildingService extends RemoteService {

    List<BuildDTO> loadBuildings();
    BuildDTO saveBuildDTO(BuildDTO b) throws ConstraintViolationException;
    FloorDTO saveFloorDTO(FloorDTO f) throws ConstraintViolationException;
    CabinetDTO saveCabinetDTO(CabinetDTO c) throws ConstraintViolationException;
    CabinetDTO deleteCabinet(Long id);
    BuildDTO deleteBuild(Long id);
    FloorDTO deleteFloor(Long id);
    ValidationSupport dummy();
}
