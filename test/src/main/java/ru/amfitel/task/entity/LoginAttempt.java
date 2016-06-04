package ru.amfitel.task.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
@Entity
@Table(name = "user_log")
@SequenceGenerator(name = "default_gen", sequenceName = "id_user_log_seq", allocationSize = 1)
public class LoginAttempt extends AbstractEntity  {

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User id_user;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
