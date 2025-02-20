package com.booleanuk.simpleapi.players;

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

@RestController
@RequestMapping("players")
@CrossOrigin
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player newPlayer) {
        return playerRepository.save(newPlayer);
    }

    @PutMapping("{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player updatedPlayer) {
        Player playerToUpdate = playerRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find player with this id")
        );
        playerToUpdate.setFirstName(updatedPlayer.getFirstName());
        playerToUpdate.setLastName(updatedPlayer.getLastName());
        playerToUpdate.setHandicap(updatedPlayer.getHandicap());

        return playerRepository.save(playerToUpdate);
    }

    @DeleteMapping("{id}")
    public Player deletePlayer(@PathVariable int id) {
        Player playerToDelete = playerRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find player with this id")
        );
        playerRepository.delete(playerToDelete);
        return playerToDelete;
    }
}
