package ru.amfitel.task.client.dictionary;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author tvaleeva
 * @since 31.03.2016
 */
public enum FloorType implements Serializable, IsSerializable {
    LOFT("чердак"),
    TECHNICAL_FLOOR ("технический этаж");

    private String name;

    FloorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static FloorType getByName(String name) {
        for (FloorType val : values()) {
            if (val.getName().equals(name))
                return val;
        }
        return null;
    }


}
