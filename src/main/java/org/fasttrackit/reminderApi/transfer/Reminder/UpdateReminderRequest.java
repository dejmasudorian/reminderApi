package org.fasttrackit.reminderApi.transfer.Reminder;

import org.fasttrackit.reminderApi.domain.LevelOfImportance;

import java.util.Date;

public class UpdateReminderRequest {

    private String title;

    private LevelOfImportance levelOfImportance;

    private String details;

    private Date remindDate;

    private Date reminderCreatedDate;

    private String createdBy;

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

    @Override
    public String toString() {
        return "CreateReminderRequest{" +
                "title='" + title + '\'' +
                ", levelOfImportance=" + levelOfImportance +
                ", details='" + details + '\'' +
                ", remindDate=" + remindDate +
                ", reminderCreatedDate=" + reminderCreatedDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
