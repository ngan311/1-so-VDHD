package com.example.qlhtgame.controller;

import com.example.qlhtgame.repository.GameRepository;
import com.example.qlhtgame.repository.MatchRepository;
import com.example.qlhtgame.repository.PlayerRepository;
import com.example.qlhtgame.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final PlayerRepository playerRepo;
    private final GameRepository gameRepo;
    private final RoomRepository roomRepo;
    private final MatchRepository matchRepo;

    public AdminController(PlayerRepository p,
                           GameRepository g,
                           RoomRepository r,
                           MatchRepository m) {
        this.playerRepo = p;
        this.gameRepo = g;
        this.roomRepo = r;
        this.matchRepo = m;
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        Map<String, Object> map = new HashMap<>();

        map.put("activePlayers", playerRepo.countActivePlayers());
        map.put("onlineGames", gameRepo.countOnlineGames());

        map.put("totalPlayers", playerRepo.count());
        map.put("totalGames", gameRepo.count());
        map.put("totalRooms", roomRepo.count());
        map.put("totalMatches", matchRepo.count());

        return map;
    }
}
