package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.Game;
import com.example.qlhtgame.entity.Match;
import com.example.qlhtgame.entity.Player;
import com.example.qlhtgame.entity.Room;
import com.example.qlhtgame.repository.GameRepository;
import com.example.qlhtgame.repository.MatchRepository;
import com.example.qlhtgame.repository.PlayerRepository;
import com.example.qlhtgame.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepo;
    private final PlayerRepository playerRepo;
    private final GameRepository gameRepo;
    private final RoomRepository roomRepo;
    private final AchievementService achievementService;

    public MatchService(MatchRepository matchRepo,
                        PlayerRepository playerRepo,
                        GameRepository gameRepo,
                        RoomRepository roomRepo,
                        AchievementService achievementService) {
        this.matchRepo = matchRepo;
        this.playerRepo = playerRepo;
        this.gameRepo = gameRepo;
        this.roomRepo = roomRepo;
        this.achievementService = achievementService;
    }

    // 1. Tạo match
    public Match create(Long playerId, Long gameId, Long roomId) {

        Player player = playerRepo.findById(playerId).orElseThrow();
        Game game = gameRepo.findById(gameId).orElseThrow();

        Room room = null;
        if (roomId != null) {
            room = roomRepo.findById(roomId).orElseThrow();
        }

        Match m = new Match();
        m.setPlayer(player);
        m.setGame(game);
        m.setRoom(room);
        m.setStatus("CREATED");
        m.setScoreEarned(0);

        return matchRepo.save(m);
    }

    // 2. Danh sách match
    public List<Match> getAll() {
        return matchRepo.findAll();
    }

    // 3. Chi tiết match
    public Match getById(Long id) {
        return matchRepo.findById(id).orElseThrow();
    }

    // 4. Lọc match
    public List<Match> filter(Long playerId, Long gameId) {
        if (playerId != null && gameId != null) {
            return matchRepo.findByPlayer_IdAndGame_Id(playerId, gameId);
        }
        if (playerId != null) {
            return matchRepo.findByPlayer_Id(playerId);
        }
        if (gameId != null) {
            return matchRepo.findByGame_Id(gameId);
        }
        return matchRepo.findAll();
    }

    // 5. Nộp kết quả
    public Match submitResult(Long matchId, String result, int kills, int assists) {

        Match match = matchRepo.findById(matchId).orElseThrow();

        if ("FINISHED".equals(match.getStatus())) {
            throw new RuntimeException("Match đã kết thúc");
        }

        Player player = match.getPlayer();

        // cập nhật player
        player.setTotalMatch(player.getTotalMatch() + 1);

        int score = kills * 10 + assists * 5;
        if ("WIN".equals(result)) {
            player.setWinCount(player.getWinCount() + 1);
            score += 50;
        }
        player.setScore(player.getScore() + score);

        playerRepo.save(player);

        // cập nhật match
        match.setResult(result);
        match.setKills(kills);
        match.setAssists(assists);
        match.setScoreEarned(score);
        match.setStatus("FINISHED");
        match.setPlayedAt(LocalDateTime.now());

        return matchRepo.save(match);
    }
}
