package com.booleanuk.simpleapi.tournaments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.booleanuk.simpleapi.players.Player;
import com.booleanuk.simpleapi.players.PlayerRepository;

@RestController
@RequestMapping("tournaments")
@CrossOrigin
public class TournamentController {
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament newTournament) {
        return tournamentRepository.save(newTournament);
    }

    @PostMapping("{id}/add/{playerId}")
    public Tournament addPlayerToTournament(@PathVariable int id, @PathVariable int playerId) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find tournament with this id")
        );
        Player player = playerRepository.findById(playerId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find player with this id")
        );
        tournament.addPlayer(player);
        playerRepository.save(player);
        return tournamentRepository.save(tournament);
    }

    @PutMapping("{id}")
    public Tournament updateTournament(@PathVariable int id, @RequestBody Tournament updatedTournament) {
        Tournament tournamentToUpdate = tournamentRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find tournament with this id")
        );
        tournamentToUpdate.setTitle(updatedTournament.getTitle());
        tournamentToUpdate.setCourseName(updatedTournament.getCourseName());

        return tournamentRepository.save(tournamentToUpdate);
    }

    @DeleteMapping("{id}")
    public Tournament deleteTournament(@PathVariable int id) {
        Tournament tournamentToDelete = tournamentRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find tournament with this id")
        );
        tournamentRepository.delete(tournamentToDelete);
        return tournamentToDelete;
    }
}
