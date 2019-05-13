package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.persistence.EventRepository;
import org.fasttrackit.reminderApi.persistence.ReminderRepository;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.UpdateEventRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.CreateReminderRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.SaveReminderRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.UpdateReminderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReminderServices {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReminderServices.class);

    private final ObjectMapper objectMapper;
    private final EventService eventService;
    private final ReminderRepository reminderRepository;

    @Autowired
    public ReminderServices(ObjectMapper objectMapper, EventRepository eventRepository, EventService eventService, ReminderRepository reminderRepository) {
        this.objectMapper = objectMapper;
        this.eventService = eventService;
        this.reminderRepository = reminderRepository;
    }

    public Reminder addEventsToReminder(SaveReminderRequest request) throws ResourceNotFoundException {
        LOGGER.info("Adding events to reminder: {}", request);

        Reminder reminder = new Reminder();
        for (Long id:request.getEventIds()){
            Event event = eventService.getEvent(id);
            reminder.addEvent(event);
        }
        return reminderRepository.save(reminder);
    }

    public Reminder createReminder(CreateReminderRequest request) {
        LOGGER.info("Creating reminder {}", request);
        Reminder reminder = objectMapper.convertValue(request, Reminder.class);
        return reminderRepository.save(reminder);
    }

    public Reminder getReminder(long id) throws ResourceNotFoundException {
        LOGGER.info("Retrieving reminder {}", id);
        return reminderRepository.findById(id)
                // Optional and lambda expression
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reminder " + id + " not found"));
    }

    public Reminder updateReminder(long id, UpdateReminderRequest request) throws ResourceNotFoundException {
        LOGGER.info("Updating reminder {}, {}", id, request);
        Reminder reminder = getReminder(id);

        BeanUtils.copyProperties(request, reminder);

        return reminderRepository.save(reminder);
    }

    public void deleteReminder(long id) {
        LOGGER.info("Deleting reminder {}", id);
        reminderRepository.deleteById(id);
        LOGGER.info("Deleted reminder {}", id);
    }



//for (Direction dir : Direction.values())

}