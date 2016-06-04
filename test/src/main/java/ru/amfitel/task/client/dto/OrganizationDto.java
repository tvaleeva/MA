package ru.amfitel.task.client.dto;

import ru.amfitel.task.client.dictionary.ObjectType;

public class OrganizationDto extends AbstractDTO {

    private String name;

    private String inn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public ObjectType getObjectType() {
        return null;
    }
}
