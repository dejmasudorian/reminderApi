package org.fasttrackit.reminderApi.transfer.Event;


import java.util.Date;

public class GetEventRequest {

    private int daysOverdue;
    private int daysLeft;
    private String searchTitle;
    private Date eventDate;


    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(int daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
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
