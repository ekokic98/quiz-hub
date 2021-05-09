package com.quizhub.property.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizhub.property.repositories.ScoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQReceiver {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final ScoreRepository scoreRepository;

    public RabbitMQReceiver(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public void receiveMessage(String message) {
        logger.info("Received (String) " + message);
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            RabbitMQStatus rabbitMQStatus = objectMapper.readValue(message, RabbitMQStatus.class);
            if (rabbitMQStatus.getStatus() == 0) {
                scoreRepository.deleteById(UUID.fromString(rabbitMQStatus.getId()));
            } else {
                logger.info("Async communication completed successfully.");
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
