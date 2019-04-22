package org.fasttrackit.reminderApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.persistence.ReminderRepository;
import org.fasttrackit.reminderApi.transfer.Reminder.CreateReminderRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.GetReminderRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.UpdateReminderRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ReminderServices {


    private final ObjectMapper objectMapper;
    private final ReminderRepository reminderRepository;


    @Autowired
    public ReminderServices(ObjectMapper objectMapper, ReminderRepository reminderRepository) {
        this.objectMapper = objectMapper;
        this.reminderRepository = reminderRepository;
    }

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReminderServices.class);


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
/*
    public List<Reminder> getAllReminders(){
        LOGGER.info("Retrieving reminder: ");
        return reminderRepository.findAll();
    }
*/
    public Page<Reminder> getReminder(GetReminderRequest request, Pageable pageable) {
        LOGGER.info("Retrieving reminder >> {}", request);

        // set conditions for overdue dates
        // convert Date type to int

        return reminderRepository.findAll(pageable);
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
        LOGGER.info("Deleted product {}", id);
    }

}