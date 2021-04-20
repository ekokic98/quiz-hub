package com.quizhub.property.services;

import com.google.protobuf.Timestamp;
import com.quizhub.property.event.EventRequest;
import com.quizhub.property.event.EventResponse;
import com.quizhub.property.event.EventServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PropertyService {

    private static String grpcUrl;
    private static int grpcPort;

    @Value("${app.grpc-url}")
    public void setGrpcUrl(String grpcUrl) {
        PropertyService.grpcUrl = grpcUrl;
    }

    @Value("${app.grpc-port}")
    public void setGrpcPort(int grpcPort) {
        PropertyService.grpcPort = grpcPort;
    }

    public static void registerEvent(EventRequest.actionType actionType, String resource, String status) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(grpcUrl, grpcPort)
                .usePlaintext()
                .build();

        EventServiceGrpc.EventServiceBlockingStub stub = EventServiceGrpc.newBlockingStub(channel);

        Instant time = Instant.now();
        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond()).setNanos(time.getNano()).build();

        EventResponse eventResponse = stub.log(EventRequest.newBuilder()
                .setDate(timestamp)
                .setMicroservice("Property service")
                .setUser("Unknown")
                .setAction(actionType)
                .setResource(resource)
                .setStatus(status)
                .build());

        System.out.println(eventResponse.getMessage());
        channel.shutdown();
    }
}
