package com.quizhub.tournament.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizhub.tournament.dto.LeaderboardInfo;
import com.quizhub.tournament.exceptions.BadRequestException;
import com.quizhub.tournament.model.Tournament;
import com.quizhub.tournament.repositories.PersonRepository;
import com.quizhub.tournament.repositories.QuizRepository;
import com.quizhub.tournament.repositories.ScoreRepository;
import com.quizhub.tournament.repositories.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RabbitMQReceiver {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private final PersonRepository personRepository;
    private final QuizRepository quizRepository;
    private final ScoreRepository scoreRepository;
    private final TournamentRepository tournamentRepository;
    private final RabbitMQSender rabbitMQSender;

    public RabbitMQReceiver(PersonRepository personRepository, QuizRepository quizRepository, ScoreRepository scoreRepository, TournamentRepository tournamentRepository, RabbitMQSender rabbitMQSender) {
        this.personRepository = personRepository;
        this.quizRepository = quizRepository;
        this.scoreRepository = scoreRepository;
        this.tournamentRepository = tournamentRepository;
        this.rabbitMQSender = rabbitMQSender;
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
            LeaderboardInfo leaderboardInfo = objectMapper.readValue(message, LeaderboardInfo.class);
            try {
                UUID tournamentId = leaderboardInfo.getQuiz().getTournament().getId();
                Tournament tournament = tournamentRepository.findById(tournamentId)
                        .orElseThrow(() -> new BadRequestException("Tournament with id " + tournamentId + " doesn't exist"));
                if (tournament.getDateEnd().isBefore(LocalDateTime.now())) {
                    rabbitMQSender.send();
                    return;
                }
                personRepository.save(leaderboardInfo.getPerson());
                quizRepository.save(leaderboardInfo.getQuiz().withTournament(tournament));
                scoreRepository.save(leaderboardInfo.getScore());
                rabbitMQSender.send();
            } catch (Exception ignored) {
                rabbitMQSender.send(leaderboardInfo.getScore().getId().toString());
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
