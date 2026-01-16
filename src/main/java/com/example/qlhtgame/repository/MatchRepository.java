package com.example.qlhtgame.repository;

import com.example.qlhtgame.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByPlayer_Id(Long playerId);

    List<Match> findByGame_Id(Long gameId);

    List<Match> findByPlayer_IdAndGame_Id(Long playerId, Long gameId);
}
