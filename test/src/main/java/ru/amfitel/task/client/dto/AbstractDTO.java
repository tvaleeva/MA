package ru.amfitel.task.client.dto;

import com.google.gwt.user.client.rpc.IsSerializable;
import ru.amfitel.task.client.dictionary.ObjectType;

import java.io.Serializable;

/**
 * Created by Bublik on 27.03.2016.
 */
public abstract class AbstractDTO  implements Serializable, IsSerializable{

    public  Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract ObjectType getObjectType();
}
