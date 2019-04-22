package org.fasttrackit.reminderApi.transfer.Reminder;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CreateReminderRequest {

    @NotBlank
    private String title;
    private Date remindDate;
    @NotBlank
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    @Override
    public String toString() {
        return "CreateReminderRequest{" +
                "title='" + title + '\'' +
                ", remindDate=" + remindDate +
                ", description='" + description + '\'' +
                '}';
    }
}
