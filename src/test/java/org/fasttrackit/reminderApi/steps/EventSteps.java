package org.fasttrackit.reminderApi.steps;

import org.fasttrackit.reminderApi.domain.DatetoString;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.service.EventService;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EventSteps {

    @Autowired
    private EventService eventService;
    private DatetoString datetoString;

    public Event createEvent() throws ParseException {
        CreateEventRequest request = new CreateEventRequest();
        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = index.parse("16-02-2019");

        request.setTitle("Family Gathering");
        request.setDescription("Must prepare food.");
        request.setEventDate(date1);
        return eventService.createEvent(request);
    }
}
