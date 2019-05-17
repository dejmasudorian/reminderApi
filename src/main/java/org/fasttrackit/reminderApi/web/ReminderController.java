package org.fasttrackit.reminderApi.web;

import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Reminder.SaveReminderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/reminders")
@CrossOrigin
public class ReminderController {

    private final ReminderServices reminderServices;

    @Autowired
    public ReminderController(ReminderServices reminderServices) {
        this.reminderServices = reminderServices;
    }

    @PostMapping
    public ResponseEntity addEventsToReminder(
            @RequestBody SaveReminderRequest request) throws ResourceNotFoundException {
        reminderServices.addEventToReminder(request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
