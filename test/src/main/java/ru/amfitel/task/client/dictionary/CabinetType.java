package ru.amfitel.task.client.dictionary;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * @author tvaleeva
 * @since 31.03.2016
 */
public enum CabinetType implements Serializable,IsSerializable {
    KITCHEN("кухня"), BOOKKEEPING("бухгалтерия");
    private String name;

    CabinetType(String name) {
        this.name= name;
    }

    public String getName() {
        return name;
    }

    public static CabinetType getByName(String name) {
        for (CabinetType val : values()) {
            if (val.getName().equals(name))
                return val;
        }
        return null;
    }

}
