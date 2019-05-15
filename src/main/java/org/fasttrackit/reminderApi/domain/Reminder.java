package org.fasttrackit.reminderApi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Notice notice;

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

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
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

        if (id != reminder.id) return false;
        if (notice != null ? !notice.equals(reminder.notice) : reminder.notice != null) return false;
        return events != null ? events.equals(reminder.events) : reminder.events == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (notice != null ? notice.hashCode() : 0);
        result = 31 * result + (notice != null ? events.hashCode() : 0);
        return result;
    }
}