package ru.amfitel.task.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
@Entity
@Table(name = "user_log")
@SequenceGenerator(name = "default_gen", sequenceName = "user_log_id_seq", allocationSize = 1)
public class LoginAttempt extends AbstractEntity  {

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User userId;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
