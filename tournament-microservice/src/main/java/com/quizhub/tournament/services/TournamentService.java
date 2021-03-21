package com.quizhub.tournament.services;

import com.quizhub.tournament.exceptions.BadRequestException;
import com.quizhub.tournament.exceptions.ConflictException;
import com.quizhub.tournament.model.Person;
import com.quizhub.tournament.model.Quiz;
import com.quizhub.tournament.model.Tournament;
import com.quizhub.tournament.repositories.PersonRepository;
import com.quizhub.tournament.repositories.QuizRepository;
import com.quizhub.tournament.repositories.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final QuizRepository quizRepository;
    private final PersonRepository personRepository;

    public TournamentService(TournamentRepository tournamentRepository, QuizRepository quizRepository, PersonRepository personRepository) {
        this.tournamentRepository = tournamentRepository;
        this.quizRepository = quizRepository;
        this.personRepository = personRepository;
    }

    public Tournament add(Tournament tournament) {
        if (tournamentRepository.existsByName(tournament.getName()))
            throw new ConflictException("Name already in use");
        if (tournament.getDateStart().isAfter(tournament.getDateEnd()))
            throw new BadRequestException("Tournament start date can't be after the end date");
        return tournamentRepository.save(tournament);
    }

    public Tournament update(Tournament tournament) {
        if (tournament.getId() == null)
            throw new BadRequestException("Tournament id can't be null");
        if (tournament.getDateStart().isAfter(tournament.getDateEnd()))
            throw new BadRequestException("Tournament start date can't be after the end date");
        Tournament existingTournament = tournamentRepository.findById(tournament.getId())
                .orElseThrow(() -> new BadRequestException("Wrong tournament id"));
        existingTournament.setName(tournament.getName());
        existingTournament.setDateEnd(tournament.getDateEnd());
        return tournamentRepository.save(existingTournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournament(UUID id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Wrong tournament id"));
    }

    public List<Quiz> getQuizzesForTournament(UUID id) {
        if (!tournamentRepository.existsById(id)) {
            throw new BadRequestException("Wrong tournament id");
        }
        return quizRepository.findAllByTournamentId(id);
    }

    public List<Person> getLeaderboardForTournament(UUID id) {
        if (!tournamentRepository.existsById(id)) {
            throw new BadRequestException("Wrong tournament id");
        }
        return personRepository.getLeaderboardForTournament(id.toString());
    }

}
