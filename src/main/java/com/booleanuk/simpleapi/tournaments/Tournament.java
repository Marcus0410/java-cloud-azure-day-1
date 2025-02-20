package com.booleanuk.simpleapi.tournaments;

import java.util.ArrayList;
import java.util.List;

import com.booleanuk.simpleapi.players.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String courseName;
    @OneToMany(mappedBy = "tournament")
    private List<Player> players = new ArrayList<>();

    public Tournament(String title, String courseName) {
        this.title = title;
        this.courseName = courseName;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setTournament(this);
    }
}
