package org.fasttrackit.reminderApi.web;

import org.fasttrackit.reminderApi.domain.Reminder;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Reminder.CreateReminderRequest;
import org.fasttrackit.reminderApi.transfer.Reminder.UpdateReminderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reminder")
public class ReminderController {


    private final ReminderServices reminderServices;

    @Autowired
    public ReminderController(ReminderServices reminderServices) {
        this.reminderServices = reminderServices;
    }


    //    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    // same as the annotation above
    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminder(@PathVariable("id") long id) throws ResourceNotFoundException {
        Reminder response = reminderServices.getReminder(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody @Valid CreateReminderRequest request) {
        Reminder response = reminderServices.createReminder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReminder(
            @PathVariable("id") long id,
            @RequestBody @Valid UpdateReminderRequest request) throws ResourceNotFoundException {
        reminderServices.updateReminder(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReminder(@PathVariable("id") long id) {
        reminderServices.deleteReminder(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
