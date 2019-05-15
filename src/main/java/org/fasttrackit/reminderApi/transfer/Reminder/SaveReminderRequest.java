package org.fasttrackit.reminderApi.transfer.Reminder;

import java.util.Set;

public class SaveReminderRequest {

    private long noticeId;
    private Set<Long> eventIds;

    public long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(long noticeId) {
        this.noticeId = noticeId;
    }

    public Set<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(Set<Long> eventIds) {
        this.eventIds = eventIds;
    }

    @Override
    public String toString() {
        return "SaveReminderRequest{" +
                "noticeId=" + noticeId +
                ", eventIds=" + eventIds +
                '}';
    }
}
