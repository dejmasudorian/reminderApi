package org.fasttrackit.reminderApi.steps;

import org.fasttrackit.reminderApi.domain.DatetoString;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.service.EventService;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;

import java.text.ParseException;
import java.util.Date;

public class EventSteps {


    private EventService eventService;
    private DatetoString datetoString;

    public Event createEvent() throws ParseException {
        CreateEventRequest request = new CreateEventRequest();
        Date date1 = datetoString.converter("16-02-2019");

        request.setTitle("Family Gathering");
        request.setDescription("Must prepare food.");
        request.setEventDate(date1);
        return eventService.createEvent(request);
    }
}
