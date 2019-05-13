package org.fasttrackit.reminderApi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private LevelOfImportance levelOfImportance;

    private String details;

    private Date remindDate;

    private Date reminderCreatedDate;

    private String createdBy;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "reminder_event",
            joinColumns = @JoinColumn(name = "reminder_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events = new HashSet<>();

    public void addEvent(Event event) {
        events.add(event);
        event.getReminders().add(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.getReminders().remove(this);
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

    public LevelOfImportance getLevelOfImportance() {
        return levelOfImportance;
    }

    public void setLevelOfImportance(LevelOfImportance levelOfImportance) {
        this.levelOfImportance = levelOfImportance;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public Date getReminderCreatedDate() {
        return reminderCreatedDate;
    }

    public void setReminderCreatedDate(Date reminderCreatedDate) {
        this.reminderCreatedDate = reminderCreatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder reminder = (Reminder) o;
        return id == reminder.id &&
                Objects.equals(events, reminder.events);
    }

    @Override
    public int hashCode() {
            int result = (int) (id ^ (id >>> 32));
            result = 31 * result + (events != null ? events.hashCode() : 0);
            return result;
        }
    }
