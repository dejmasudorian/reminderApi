package org.fasttrackit.reminderApi.service;

import org.fasttrackit.reminderApi.domain.Reminder;

import java.util.List;

public interface ReminderServicesRequest {

    List<Reminder> getAllReminders();
    Reminder getReminder(long id);
    Reminder saveReminder(Reminder reminder);
    void removeReminder(Reminder reminder);
}
