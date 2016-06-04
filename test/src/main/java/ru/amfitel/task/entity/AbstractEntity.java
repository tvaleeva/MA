package ru.amfitel.task.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Bublik on 27.03.2016.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    @Column(name = "id")
    public Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

