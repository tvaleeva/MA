package ru.amfitel.task.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bublik on 31.03.2016.
 */
@Entity
@Table(name = "users")
@SequenceGenerator(name = "default_gen", sequenceName = "id_user_seq", allocationSize = 1)
public class User extends AbstractEntity {

    @Column(name = "name")
    private  String name;

    @Column(name = "password")
    private String password;

    @Column(name = "blocked")
    private Boolean blocked;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id_user", cascade = CascadeType.ALL)
    private List<LoginAttempt> loginAttempts;

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

    public Boolean getNonBlocked() {
        if (blocked == null) {
            return null;
        }
        return !blocked;
    }

    public void setNonBlocked(Boolean nonBlocked) {
        if (nonBlocked == null) {
            this.blocked = null;
        } else {
            this.blocked = !nonBlocked;
        }
    }

    public List<LoginAttempt> getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(List<LoginAttempt> loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
}
