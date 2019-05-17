package org.fasttrackit.reminderApi;

import org.fasttrackit.reminderApi.domain.DatetoString;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.EventService;

import org.fasttrackit.reminderApi.steps.EventSteps;
import org.fasttrackit.reminderApi.transfer.Event.EventResponse;
import org.fasttrackit.reminderApi.transfer.Event.GetEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.UpdateEventRequest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServicesIntegrationTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventSteps eventSteps;

    private DatetoString datetoString;

    @Test
    public void testCreateEvent_whenValidRequest_thenReturnEventWithId() throws ParseException {
        Event event = eventSteps.createEvent();

        assertThat(event, CoreMatchers.notNullValue());
        assertThat(event.getId(), greaterThan(0L));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetEvent_whenEventNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {
        eventService.getEvent(0);
    }

    @Test
    public void testGetEvent_whenExistingId_thenReturnMatchingEvent() throws ResourceNotFoundException, ParseException {
        Event event = eventSteps.createEvent();

        Event retrievedEvent = eventService.getEvent(event.getId());

        assertThat(retrievedEvent.getId(), is(event.getId()));
        assertThat(retrievedEvent.getTitle(), is(event.getTitle()));
    }

    @Test
    public void testUpdateEvent_whenValidRequestWithAllFields_thenReturnUpdatedEvent() throws ResourceNotFoundException, ParseException {
        Event createdEvent = eventSteps.createEvent();

        UpdateEventRequest request = new UpdateEventRequest();
        request.setTitle(createdEvent.getTitle() + "_Edited");
        request.setDescription(createdEvent.getDescription() + "_Edited");
        request.setEventDate(createdEvent.getEventDate());

        Event updatedEvent =
                eventService.updateEvent(createdEvent.getId(), request);

        assertThat(updatedEvent.getTitle(), is(request.getTitle()));
        assertThat(updatedEvent.getTitle(), not(is(createdEvent.getTitle())));

        assertThat(updatedEvent.getDescription(), is(request.getDescription()));
        assertThat(updatedEvent.getEventDate(), is(request.getEventDate()));

        assertThat(updatedEvent.getId(), is(createdEvent.getId()));
    }

    // todo: Implement negative tests for update and tests for update with some fields only

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteEvent_whenExistingId_thenEventIsDeleted() throws ResourceNotFoundException, ParseException {
        Event createdEvent = eventSteps.createEvent();

        eventService.deleteEvent(createdEvent.getId());

        eventService.getEvent(createdEvent.getId());
    }

   @Test
    public void testGetEvent_whenAllCriteriaProvidedAndMatching_thenReturnFilteredResults() throws ParseException {
        Event createdEvent = eventSteps.createEvent();

        //Date date1 = datetoString.converter("16-02-2019");
        SimpleDateFormat index = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = index.parse("16-02-2019");

        GetEventRequest request = new GetEventRequest();
        request.setEventDate(date1);

        request.setSearchTitle("Family Gathering");
        eventService.designateDaysLeftOrOverdue(request,request.getEventDate());

        Page<EventResponse> events =
                eventService.getEvent(request, PageRequest.of(0, 31));

        assertThat(events.getTotalElements(), greaterThanOrEqualTo(1L));

        // todo: for each product from the response assert that all criteria are matched
    }
}
