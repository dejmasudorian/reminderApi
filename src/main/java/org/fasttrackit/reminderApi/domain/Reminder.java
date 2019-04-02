package org.fasttrackit.reminderApi.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "remind_table")
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "remind_year", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date remindDate;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reminder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remind_Date) {
        this.remindDate = remind_Date;

    }

}
