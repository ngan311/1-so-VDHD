package com.example.qlhtgame.repository;

import com.example.qlhtgame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT COUNT(p) FROM Player p WHERE p.active = true")
    long countActivePlayers();
}

