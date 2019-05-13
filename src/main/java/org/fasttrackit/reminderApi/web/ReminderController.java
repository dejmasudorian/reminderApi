package org.fasttrackit.reminderApi.web;

import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.EventService;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.UpdateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/reminder")
public class ReminderController {


    private final EventService eventService;

    public ReminderController(EventService eventService) {
        this.eventService = eventService;
    }

    //    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    // same as the annotation above
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable("id") long id) throws ResourceNotFoundException {
        Event response = eventService.getEvent(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid CreateEventRequest request) {
        Event response = eventService.createEvent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateReminder(
            @PathVariable("id") long id,
            @RequestBody @Valid UpdateEventRequest request) throws ResourceNotFoundException {
        eventService.updateEvent(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReminder(@PathVariable("id") long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
