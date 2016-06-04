package ru.amfitel.task.entity;

import ru.amfitel.task.client.dictionary.Material;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bublik on 27.03.2016.
 */
@Entity
@Table(name = "build")
@SequenceGenerator(name = "default_gen", sequenceName = "id_build_seq", allocationSize = 1)
public class Build extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    private Date date;

    @Column(name = "material")
    @Enumerated
    private Material material;

    @Column(name = "count_floor")
    private Integer countFloor;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "buildId", cascade = CascadeType.ALL)
    private List<Floor> floors;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Integer getCountFloor() {
        return countFloor;
    }

    public void setCountFloor(Integer countFloor) {
        this.countFloor = countFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Floor> getFloors() {
        if (floors == null) {
            floors = new ArrayList<>();
        }
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}
