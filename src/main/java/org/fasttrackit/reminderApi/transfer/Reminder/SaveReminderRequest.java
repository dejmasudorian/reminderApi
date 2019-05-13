package org.fasttrackit.reminderApi.transfer.Reminder;

import java.util.Set;

public class SaveReminderRequest {

    private Set<Long> eventIds;

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(Set<Long> eventIds) {
        this.eventIds = eventIds;
    }

    @Override
    public String toString() {
        return "SaveReminderRequest{" +
                "eventIds=" + eventIds +
                '}';
    }
}
