package org.fasttrackit.reminderApi.transfer.Event;


import java.util.Date;

public class GetEventRequest {

    private long daysOverdue;
    private long daysLeft;
    private String searchTitle;
    private Date eventDate;


    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }


    public long getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public long getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(long daysLeft) {
        this.daysLeft = daysLeft;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "GetEventRequest{" +
                "daysOverdue=" + daysOverdue +
                ", daysLeft=" + daysLeft +
                ", searchTitle='" + searchTitle + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}
