package com.quizhub.systemevents.service;

import com.quizhub.systemevents.event.EventRequest;
import com.quizhub.systemevents.event.EventResponse;
import com.quizhub.systemevents.event.EventServiceGrpc;
import com.quizhub.systemevents.model.Event;
import com.quizhub.systemevents.model.enums.ActionType;
import com.quizhub.systemevents.repository.EventRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class EventService extends EventServiceGrpc.EventServiceImplBase {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event add(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void log(EventRequest request, StreamObserver<EventResponse> responseObserver) {
        Event event = eventRepository.save(new Event(
                request.getMicroservice(),
                request.getUser(),
                ActionType.valueOf(request.getAction().name()),
                request.getResource(),
                Integer.parseInt(request.getStatus())
        ));

        EventResponse response = EventResponse.newBuilder()
                .setMessage(event.toString())
                .setStatus("1")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
