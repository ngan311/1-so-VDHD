package com.example.qlhtgame.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int score;
    private int winCount;
    private int totalMatch;
    private boolean active = true;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getTotalMatch() {
        return totalMatch;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public void setTotalMatch(int totalMatch) {
        this.totalMatch = totalMatch;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
