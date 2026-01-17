package com.example.qlhtgame.controller;

import com.example.qlhtgame.entity.Match;
import com.example.qlhtgame.service.MatchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchConntroller {

    private final MatchService service;

    public MatchConntroller(MatchService service) {
        this.service = service;
    }


    @PostMapping
    public Match create(@RequestParam Long playerId,
                        @RequestParam Long gameId,
                        @RequestParam(required = false) Long roomId) {
        return service.create(playerId, gameId, roomId);
    }

    @GetMapping
    public List<Match> all(@RequestParam(required = false) Long playerId,
                           @RequestParam(required = false) Long gameId) {
        return service.filter(playerId, gameId);
    }


    @GetMapping("/{id}")
    public Match detail(@PathVariable Long id) {
        return service.getById(id);
    }


    @PostMapping("/{id}/result")
    public Match submitResult(@PathVariable Long id,
                              @RequestParam String result,
                              @RequestParam int kills,
                              @RequestParam int assists) {
        return service.submitResult(id, result, kills, assists);
    }
}
