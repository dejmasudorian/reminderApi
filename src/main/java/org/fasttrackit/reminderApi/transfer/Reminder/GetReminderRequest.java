package org.fasttrackit.reminderApi.transfer.Reminder;

import java.util.Date;

public class GetReminderRequest {

    private Date dateReminderOverdue;
    private String reminderDescriptionOverdue;
    private String reminderTitleOverdue;
    private int daysLeftReminder;

    public Date getDateReminderOverdue() {
        return dateReminderOverdue;
    }

    public void setDateReminderOverdue(Date dateReminderOverdue) {
        this.dateReminderOverdue = dateReminderOverdue;
    }

    public String getReminderDescriptionOverdue() {
        return reminderDescriptionOverdue;
    }

    public void setReminderDescriptionOverdue(String reminderDescriptionOverdue) {
        this.reminderDescriptionOverdue = reminderDescriptionOverdue;
    }

    public String getReminderTitleOverdue() {
        return reminderTitleOverdue;
    }

    public void setReminderTitleOverdue(String reminderTitleOverdue) {
        this.reminderTitleOverdue = reminderTitleOverdue;
    }

    public int getDaysLeftReminder() {
        return daysLeftReminder;
    }

    public void setDaysLeftReminder(int daysLeftReminder) {
        this.daysLeftReminder = daysLeftReminder;
    }

    @Override
    public String toString() {
        return "GetReminderRequest{" +
                "dateReminderOverdue=" + dateReminderOverdue +
                ", reminderDescriptionOverdue='" + reminderDescriptionOverdue + '\'' +
                ", reminderTitleOverdue='" + reminderTitleOverdue + '\'' +
                ", daysLeftReminder=" + daysLeftReminder +
                '}';
    }
}
