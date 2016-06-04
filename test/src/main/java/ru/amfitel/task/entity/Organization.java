package ru.amfitel.task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author alexanderdemin.
 */
@Entity
@Table(name = "organization")
@SequenceGenerator(name = "default_gen", sequenceName = "organization_id_seq", allocationSize = 1)
public class Organization extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
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
}
