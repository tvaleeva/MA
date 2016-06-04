package ru.amfitel.task.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bublik on 31.03.2016.
 */
@Entity
@Table(name = "users")
@SequenceGenerator(name = "default_gen", sequenceName = "users_id_seq", allocationSize = 1)
public class User extends AbstractEntity {

    @Column(name = "name")
    private  String name;

    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private Boolean blocked;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLog> userLogs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Deprecated
    public Boolean getNonBlocked() {
        if (blocked == null) {
            return null;
        }
        return !blocked;
    }

    @Deprecated
    public void setNonBlocked(Boolean nonBlocked) {
        if (nonBlocked == null) {
            this.blocked = null;
        } else {
            this.blocked = !nonBlocked;
        }
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public List<UserLog> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(List<UserLog> userLogs) {
        this.userLogs = userLogs;
    }
}
