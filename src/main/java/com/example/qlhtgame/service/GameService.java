package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.Game;
import com.example.qlhtgame.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repo;

    public GameService(GameRepository repo) {
        this.repo = repo;
    }

    public List<Game> getAll() {
        return repo.findAll();
    }

    public Game getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Game create(Game g) {
        if (g.getVersion() == null || g.getVersion().trim().isEmpty()) {
            g.setVersion("1.0");
        }
        g.setActive(true);
        return repo.save(g);
    }

    public Game update(Long id, Game data) {
        Game g = repo.findById(id).orElseThrow();
        g.setName(data.getName());
        g.setCategory(data.getCategory());
        g.setOnline(data.isOnline());
        g.setVersion(data.getVersion());
        return repo.save(g);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void hide(Long id) {
        Game g = repo.findById(id).orElseThrow();
        g.setActive(false);
        repo.save(g);
    }

    public void unhide(Long id) {
        Game g = repo.findById(id).orElseThrow();
        g.setActive(true);
        repo.save(g);
    }
}
