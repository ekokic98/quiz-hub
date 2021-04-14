package com.quizhub.person.service;

import com.google.protobuf.Timestamp;
import com.quizhub.person.event.EventRequest;
import com.quizhub.person.event.EventResponse;
import com.quizhub.person.event.EventServiceGrpc;
import com.quizhub.person.exception.BadRequestException;
import com.quizhub.person.exception.ConflictException;
import com.quizhub.person.model.Person;
import com.quizhub.person.repository.PersonRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private static String grpcUrl;
    private static int grpcPort;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        PersonService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        PersonService.grpcPort = grpcPort;
    }

    public List<Person> getAllPersons() {
        registerEvent(EventRequest.actionType.GET, "/api/person-ms/persons/all", "200");
        return personRepository.findAll();
    }

    public Person addPerson(Person person) {
        try {
            if (personRepository.existsByUsernameIgnoreCase(person.getUsername())) {
                throw new ConflictException("Username already taken.");
            }
            if (personRepository.existsByEmail(person.getUsername())) {
                throw new ConflictException("Email already taken.");
            }
            registerEvent(EventRequest.actionType.CREATE, "/api/person-ms/persons", "200");
            return personRepository.save(person);
        } catch (ConflictException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/person-ms/persons", "409");
            throw exception;
        } catch (BadRequestException exception) {
            registerEvent(EventRequest.actionType.CREATE, "/api/person-ms/persons", "400");
            throw exception;
        }
    }

    public Person getPersonById(UUID id) {
        registerEvent(EventRequest.actionType.GET, "/api/person-ms/persons", "200");
        return personRepository.findById(id)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/person-ms/persons", "400");
                    return new BadRequestException("Person with id " + id.toString() + " does not exist.");
                });
    }

    public Person getPersonByUsername(String username) {
        registerEvent(EventRequest.actionType.GET, "/api/person-ms/persons/username", "200");
        return personRepository.findByUsername(username)
                .orElseThrow(() -> {
                    registerEvent(EventRequest.actionType.GET, "/api/person-ms/persons/username", "400");
                    return new BadRequestException("Person with username " + username + " does not exist.");
                });
    }

    private void registerEvent(EventRequest.actionType actionType, String resource, String status) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcUrl, grpcPort)
                .usePlaintext()
                .build();

        EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel);

        Instant time = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();

        EventResponse eventResponse = stub.log(EventRequest.newBuilder()
                .setDate(timestamp)
                .setMicroservice("Person service")
                .setUser("Unknown")
                .setAction(actionType)
                .setResource(resource)
                .setStatus(status)
                .build());

        System.out.println(eventResponse.getMessage());
        channel.shutdown();
    }
}
