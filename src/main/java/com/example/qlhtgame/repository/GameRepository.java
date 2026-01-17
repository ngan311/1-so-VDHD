package com.example.qlhtgame.repository;

import com.example.qlhtgame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("SELECT COUNT(g) FROM Game g WHERE g.online = true")
    long countOnlineGames();
}

