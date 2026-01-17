package com.example.qlhtgame.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "player_achievements")
public class PlayerAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    private LocalDateTime achievedAt;



    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public LocalDateTime getAchievedAt() {
        return achievedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public void setAchievedAt(LocalDateTime achievedAt) {
        this.achievedAt = achievedAt;
    }
}
