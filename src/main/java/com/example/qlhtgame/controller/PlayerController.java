package com.example.qlhtgame.controller;

import com.example.qlhtgame.entity.Match;
import com.example.qlhtgame.entity.Player;
import com.example.qlhtgame.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> all() {
        return service.getAll();
    }

    @PostMapping
    public Player create(@RequestBody Player player) {
        return service.create(player);
    }

    @PutMapping("/{id}")
    public Player update(@PathVariable Long id, @RequestBody Player player) {
        return service.update(id, player);
    }

    // chi tiết player
    @GetMapping("/{id}")
    public Player detail(@PathVariable Long id) {
        return service.getById(id);
    }

    // lock
    @PutMapping("/{id}/lock")
    public void lock(@PathVariable Long id) {
        service.lock(id);
    }

    // unlock
    @PutMapping("/{id}/unlock")
    public void unlock(@PathVariable Long id) {
        service.unlock(id);
    }

    // ranking theo score
    @GetMapping("/ranking-score")
    public List<Player> rankingScore() {
        return service.rankingByScore();
    }

    // ranking theo win
    @GetMapping("/ranking-win")
    public List<Player> rankingWin() {
        return service.rankingByWin();
    }

    // lịch sử match của player
    @GetMapping("/{id}/matches")
    public List<Match> matchHistory(@PathVariable Long id) {
        return service.getMatchHistory(id);
    }
}
