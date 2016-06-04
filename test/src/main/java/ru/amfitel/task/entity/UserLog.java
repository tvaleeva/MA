package ru.amfitel.task.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Bublik on 01.04.2016.
 */
@Entity
@Table(name = "user_log")
@SequenceGenerator(name = "default_gen", sequenceName = "user_log_id_seq", allocationSize = 1)
@org.hibernate.annotations.NamedQueries(value = {
        @NamedQuery(name = "getCount",query = "select count(1) from UserLog")
})
public class UserLog extends AbstractEntity  {

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
