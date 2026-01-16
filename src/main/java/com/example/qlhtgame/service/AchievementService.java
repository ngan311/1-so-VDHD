package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.Achievement;
import com.example.qlhtgame.entity.Player;
import com.example.qlhtgame.entity.PlayerAchievement;
import com.example.qlhtgame.repository.AchievementRepository;
import com.example.qlhtgame.repository.PlayerAchievementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepo;
    private final PlayerAchievementRepository playerAchievementRepo;

    public AchievementService(AchievementRepository achievementRepo,
                              PlayerAchievementRepository playerAchievementRepo) {
        this.achievementRepo = achievementRepo;
        this.playerAchievementRepo = playerAchievementRepo;
    }

    public void checkAndGrant(Player player) {

        List<Achievement> achievements = achievementRepo.findAll();

        for (int i = 0; i < achievements.size(); i++) {
            Achievement a = achievements.get(i);

            boolean achieved = false;

            if ("WIN_COUNT".equals(a.getType())) {
                achieved = player.getWinCount() >= a.getThreshold();
            }

            if ("TOTAL_MATCH".equals(a.getType())) {
                achieved = player.getTotalMatch() >= a.getThreshold();
            }

            if ("SCORE".equals(a.getType())) {
                achieved = player.getScore() >= a.getThreshold();
            }

            if (achieved) {
                boolean existed = playerAchievementRepo
                        .existsByPlayer_IdAndAchievement_Id(player.getId(), a.getId());

                if (!existed) {
                    PlayerAchievement pa = new PlayerAchievement();
                    pa.setPlayer(player);
                    pa.setAchievement(a);
                    pa.setAchievedAt(LocalDateTime.now());
                    playerAchievementRepo.save(pa);
                }
            }
        }
    }
}
