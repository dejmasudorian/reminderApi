package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.persistence.EventRepository;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.GetEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.UpdateEventRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.Date;
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


    public int compareDays(GetEventRequest request) {
        Date today = new Date();
        long diff = request.getEventDate().getTime() - today.getTime();
        long numberOfDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int converter= Math.toIntExact(numberOfDays);
        return converter;
    }

    public Page<Event> getEvent(GetEventRequest request, Pageable pageable) {
        LOGGER.info("Retrieving event >> {}", request);

        // set conditions for overdue dates

        if (request.getSearchTitle() != null &&
                compareDays(request)>0) {
            return eventRepository.findEventByTitleAndAndEventDateGreaterThan(
                    request.getSearchTitle(), request.getDaysOverdue(), pageable);}
        else if (request.getSearchTitle() != null &&
                compareDays(request)<0) {
            return eventRepository.findEventByTitleAndAndEventDateLessThan(
                    request.getSearchTitle(), request.getDaysLeft(), pageable);}
        return eventRepository.findAll(pageable);
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
