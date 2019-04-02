package org.fasttrackit.reminderApi.transfer;

import java.util.Date;

public class CreateReminderRequest {

    private String title;
    private Date remindDate;

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
}
