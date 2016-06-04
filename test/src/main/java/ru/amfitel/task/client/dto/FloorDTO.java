package ru.amfitel.task.client.dto;

import ru.amfitel.task.client.dictionary.FloorType;
import ru.amfitel.task.client.dictionary.ObjectType;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */

public class FloorDTO extends AbstractDTO {

    @NotNull(message = "Поле \"№ этажа\" не заполнено")
    private Integer number;

    @NotNull(message = "Поле \"Кол-во кабинетов\" не заполнено")
    private Integer countCabinet;

    private Float square;

    private Long idBuild;

    private FloorType type;

    private List<CabinetDTO> cabinets;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCountCabinet() {
        return countCabinet;
    }

    public void setCountCabinet(Integer countCabinet) {
        this.countCabinet = countCabinet;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public Long getIdBuild() {
        return idBuild;
    }

    public void setIdBuild(Long idBuild) {
        this.idBuild = idBuild;
    }

    public FloorType getType() {
        return type;
    }

    public void setType(FloorType type) {
        this.type = type;
    }

    public List<CabinetDTO> getCabinets() {
        return cabinets;
    }

    public void setCabinets(List<CabinetDTO> cabinets) {
        this.cabinets = cabinets;
    }

    @Override
    public String toString() {
        return  "№ этажа:"+number+"/ Кол-во каб.: " +countCabinet+"/ Тип этажа: " + (type == null ? null : type.getName());
    }

    @Override
    public ObjectType getObjectType() {
        return ObjectType.FLOOR;
    }
}
