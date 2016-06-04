package ru.amfitel.task.client.dto;

import ru.amfitel.task.client.dictionary.CabinetType;
import ru.amfitel.task.client.dictionary.ObjectType;
import ru.amfitel.task.entity.Floor;

import javax.validation.constraints.NotNull;

/**
 * Created by Bublik on 27.03.2016.
 */

public class CabinetDTO extends AbstractDTO {

    @NotNull(message = "Поле \"№ кабинета\" не заполнено")
    private Integer number;

    @NotNull(message = "Поле \"Площадь\" не заполнено")
    private Double square;

    private Long idFloor;

    private CabinetType type;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getSquare() {
        return square;
    }

    public void setSquare(Double square) {
        this.square = square;
    }

    public Long getIdFloor() {
        return idFloor;
    }

    public void setIdFloor(Long idFloor) {
        this.idFloor = idFloor;
    }

    public CabinetType getType() {
        return type;
    }

    public void setType(CabinetType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "№ каб. :"+number+"/ Площадь:" + square+"/ Тип:" + (type == null ? null : type.getName());
    }


    @Override
    public ObjectType getObjectType() {
        return ObjectType.CABINET;
    }
}
