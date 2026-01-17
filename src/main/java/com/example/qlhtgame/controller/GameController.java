package com.example.qlhtgame.controller;

import com.example.qlhtgame.entity.Game;
import com.example.qlhtgame.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public List<Game> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Game detail(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Game create(@RequestBody Game game) {
        return service.create(game);
    }

    @PutMapping("/{id}")
    public Game update(@PathVariable Long id, @RequestBody Game game) {
        return service.update(id, game);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/hide")
    public void hide(@PathVariable Long id) {
        service.hide(id);
    }

    @PutMapping("/{id}/unhide")
    public void unhide(@PathVariable Long id) {
        service.unhide(id);
    }
}
