package ru.amfitel.task.client.dictionary;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * @author tvaleeva
 * @since 30.03.2016
 */

public enum Material implements  Serializable, IsSerializable {
    BRICK("кирпич"),
    LOG("бревно");

    private String name;

    Material(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Material getByName(String name) {
        for (Material val : values()) {
            if (val.getName().equals(name))
                return val;
        }
        return null;
    }


}
