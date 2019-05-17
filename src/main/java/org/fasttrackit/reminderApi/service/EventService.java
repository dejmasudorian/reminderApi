package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.persistence.EventRepository;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.EventResponse;
import org.fasttrackit.reminderApi.transfer.Event.GetEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.UpdateEventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
public class EventService {

    private final ObjectMapper objectMapper;
    private final EventRepository eventRepository;


    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReminderServices.class);

    @Autowired
    public EventService(ObjectMapper objectMapper, EventRepository eventRepository) {
        this.objectMapper = objectMapper;
        this.eventRepository = eventRepository;
    }


    public Event createEvent(CreateEventRequest request) {
        LOGGER.info("Creating event {}", request);
        Event event = objectMapper.convertValue(request, Event.class);
        return eventRepository.save(event);
    }

    public Event getEvent(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving event {}", id);
        return eventRepository.findById(id)
                // Optional and lambda expression
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Event " + id + " not found"));
    }

    public Iterable<Event> getAllEvents(){
        return eventRepository.findAll();}

    public static <Event> Set<Event> toSet(final Iterable<Event> iterable) {
        final Set<Event> set = new TreeSet<Event>();
        for (Iterator<Event> i = iterable.iterator(); i.hasNext();) {
            set.add(i.next());
        }
        return set;
    }


    public int compareDays(Date getTimeIndex) {
        long diffInMillies = Math.abs(getTimeIndex.getTime() - System.currentTimeMillis());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        int converter = (int) diff;
        return converter;
    }

    public void designateDaysLeftOrOverdue(GetEventRequest request, Date getTimeIndex){
        if(compareDays(getTimeIndex)>0)
            request.setDaysLeft(compareDays(getTimeIndex));
        else if (compareDays(getTimeIndex)<0)
            request.setDaysOverdue(compareDays(getTimeIndex));
    }

    public Page<EventResponse> getEvent(GetEventRequest request, Pageable pageable) {
        LOGGER.info("Retrieving event >> {}", request);

        Page<Event> events;

        // set conditions for overdue dates

        if (request.getSearchTitle() != null &&
                request.getDaysOverdue()>0) {
            events = eventRepository.findEventByTitleAndAndEventDateGreaterThan(
                    request.getSearchTitle(), request.getDaysOverdue(), pageable);}
        else if (request.getSearchTitle() != null &&
                request.getDaysLeft()<0) {
            events = eventRepository.findEventByTitleAndAndEventDateLessThan(
                    request.getSearchTitle(), request.getDaysLeft(), pageable);}
        else { events = eventRepository.findAll(pageable);}

        List<EventResponse> eventResponses = new ArrayList<>();

        for (Event event : events.getContent()) {
            EventResponse eventResponse = new EventResponse();
            eventResponse.setId(event.getId());
            eventResponse.setTitle(event.getTitle());
            eventResponse.setDescription(event.getDescription());

            eventResponses.add(eventResponse);
        }

        return new PageImpl<>(
                eventResponses, pageable, events.getTotalElements());
    }

    public Event updateEvent(long id, UpdateEventRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating event {}, {}", id, request);
        Event event = getEvent(id);

        BeanUtils.copyProperties(request, event);

        return eventRepository.save(event);
    }

    public void deleteEvent(long id) {
        LOGGER.info("Deleting event {}", id);
        eventRepository.deleteById(id);
        LOGGER.info("Deleted event {}", id);
    }
}
