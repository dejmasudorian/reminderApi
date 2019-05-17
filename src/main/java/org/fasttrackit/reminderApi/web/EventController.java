package org.fasttrackit.reminderApi.web;

import org.fasttrackit.reminderApi.domain.Event;
import org.fasttrackit.reminderApi.exception.ResourceNotFoundException;
import org.fasttrackit.reminderApi.service.EventService;
import org.fasttrackit.reminderApi.service.ReminderServices;
import org.fasttrackit.reminderApi.transfer.Event.CreateEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.EventResponse;
import org.fasttrackit.reminderApi.transfer.Event.GetEventRequest;
import org.fasttrackit.reminderApi.transfer.Event.UpdateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/events")
public class EventController {


    private final EventService eventService;


    @Autowired
    public EventController(EventService eventService) {
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

    @GetMapping
    public ResponseEntity<Page<EventResponse>> getProducts(
            @Valid GetEventRequest request, Pageable pageable) {
        Page<EventResponse> response = eventService.getEvent(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);}

}
