package ru.amfitel.task.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author tvaleeva
 * @since 28.03.2016
 */
@MappedSuperclass
public class AbstractReferenceBook extends AbstractEntity {


    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
