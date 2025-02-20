package com.booleanuk.simpleapi.tournaments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Integer>{
    
}
