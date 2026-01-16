package com.example.qlhtgame.repository;

import com.example.qlhtgame.entity.PlayerAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerAchievementRepository extends JpaRepository<PlayerAchievement, Long> {

    boolean existsByPlayer_IdAndAchievement_Id(Long playerId, Long achievementId);
}
