package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.Match;
import com.example.qlhtgame.entity.Player;
import com.example.qlhtgame.repository.MatchRepository;
import com.example.qlhtgame.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepo;
    private final MatchRepository matchRepo;

    public PlayerService(PlayerRepository playerRepo, MatchRepository matchRepo) {
        this.playerRepo = playerRepo;
        this.matchRepo = matchRepo;
    }

    // ===== CRUD cơ bản =====

    public List<Player> getAll() {
        return playerRepo.findAll();
    }

    public Player getById(Long id) {
        return playerRepo.findById(id).orElseThrow();
    }

    public Player create(Player player) {
        player.setScore(0);
        player.setWinCount(0);
        player.setTotalMatch(0);
        player.setActive(true);
        return playerRepo.save(player);
    }

    public Player update(Long id, Player data) {
        Player p = playerRepo.findById(id).orElseThrow();
        p.setName(data.getName());
        return playerRepo.save(p);
    }

    // ===== Lock / Unlock =====

    public void lock(Long id) {
        Player p = playerRepo.findById(id).orElseThrow();
        p.setActive(false);
        playerRepo.save(p);
    }

    public void unlock(Long id) {
        Player p = playerRepo.findById(id).orElseThrow();
        p.setActive(true);
        playerRepo.save(p);
    }

    // ===== Ranking =====

    // ranking theo score (giữ đúng cách sort thủ công)
    public List<Player> rankingByScore() {
        List<Player> list = playerRepo.findAll();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getScore() > list.get(i).getScore()) {
                    Player temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

    // ranking theo win
    public List<Player> rankingByWin() {
        List<Player> list = playerRepo.findAll();

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getWinCount() > list.get(i).getWinCount()) {
                    Player temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        return list;
    }

    // ===== Lịch sử trận đấu =====

    public List<Match> getMatchHistory(Long playerId) {
        return matchRepo.findByPlayer_Id(playerId);
    }
}
