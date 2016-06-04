package ru.amfitel.task.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import org.hibernate.validator.engine.ValidationSupport;
import ru.amfitel.task.client.dto.BuildDTO;
import ru.amfitel.task.client.dto.CabinetDTO;
import ru.amfitel.task.client.dto.FloorDTO;

import javax.validation.ConstraintViolationException;
import java.util.List;

public interface BuildingServiceAsync {
    void loadBuildings(AsyncCallback<List<BuildDTO>> async);

    void saveFloorDTO(FloorDTO f, AsyncCallback<FloorDTO> async) throws ConstraintViolationException;

    void saveCabinetDTO(CabinetDTO c, AsyncCallback<CabinetDTO> async) throws ConstraintViolationException;

    void deleteCabinet(Long id, AsyncCallback<CabinetDTO> async);

    void deleteBuild(Long id, AsyncCallback<BuildDTO> async);

    void deleteFloor(Long id, AsyncCallback<FloorDTO> async);

    void saveBuildDTO(BuildDTO b, AsyncCallback<BuildDTO> async) throws ConstraintViolationException;

    void dummy(AsyncCallback<ValidationSupport> async);
}
