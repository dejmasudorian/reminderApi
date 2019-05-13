package org.fasttrackit.reminderApi.transfer.Event;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CreateEventRequest {

    private String title;

    private Date eventDate;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateEventRequest{" +
                "title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", description='" + description + '\'' +
                '}';
    }
}
