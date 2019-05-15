package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.domain.Notice;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.persistence.EventRepository;
import org.fasttrackit.reminderApi.persistence.ReminderRepository;
import org.fasttrackit.reminderApi.transfer.Reminder.SaveReminderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReminderServices {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReminderServices.class);

    private final ObjectMapper objectMapper;
    private final EventService eventService;
    private final ReminderRepository reminderRepository;
    private final NoticeService noticeService;

    @Autowired
    public ReminderServices(ObjectMapper objectMapper, EventRepository eventRepository, EventService eventService, ReminderRepository reminderRepository, NoticeService noticeService) {
        this.objectMapper = objectMapper;
        this.eventService = eventService;
        this.reminderRepository = reminderRepository;
        this.noticeService = noticeService;
    }

    @Transactional
    public Reminder addEventToReminder(SaveReminderRequest request) throws ResourceNotFoundException {
        LOGGER.info("Adding events to reminder: {}", request);

        Notice notice =
                noticeService.getNotice(request.getNoticeId());

        Reminder reminder = new Reminder();
        reminder.setNotice(notice);

        for (Long id : request.getEventIds()) {
            Event event = eventService.getEvent(id);
            reminder.addEvent(event);
        }

        return reminderRepository.save(reminder);
    }




//for (Direction dir : Direction.values())

}