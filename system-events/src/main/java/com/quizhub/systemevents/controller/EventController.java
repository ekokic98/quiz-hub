package com.quizhub.systemevents.controller;

import com.quizhub.systemevents.model.Event;
import com.quizhub.systemevents.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/event-ms")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> add(@RequestBody @Valid Event event) {
        return ResponseEntity.ok(eventService.add(event));
    }
}
