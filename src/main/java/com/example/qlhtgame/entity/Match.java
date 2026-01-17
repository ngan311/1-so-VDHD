package com.example.qlhtgame.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String result;
    private int scoreEarned;

    private String status;

    private Integer kills;
    private Integer assists;

    @Column(name = "played_at")
    private LocalDateTime playedAt;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;



    public Long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public int getScoreEarned() {
        return scoreEarned;
    }

    public String getStatus() {
        return status;
    }

    public Integer getKills() {
        return kills;
    }

    public Integer getAssists() {
        return assists;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public Room getRoom() {
        return room;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setScoreEarned(int scoreEarned) {
        this.scoreEarned = scoreEarned;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
