package com.example.qlhtgame.controllerview;

import com.example.qlhtgame.service.GameService;
import com.example.qlhtgame.service.MatchService;
import com.example.qlhtgame.service.PlayerService;
import com.example.qlhtgame.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matches")
public class MatchViewController {

    private final MatchService matchService;
    private final PlayerService playerService;
    private final GameService gameService;
    private final RoomService roomService;

    public MatchViewController(MatchService matchService,
                               PlayerService playerService,
                               GameService gameService,
                               RoomService roomService) {
        this.matchService = matchService;
        this.playerService = playerService;
        this.gameService = gameService;
        this.roomService = roomService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("matches", matchService.getAll());
        model.addAttribute("players", playerService.getAll());
        model.addAttribute("games", gameService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        return "match/list";
    }

    @PostMapping("/create")
    public String create(@RequestParam Long playerId,
                         @RequestParam Long gameId,
                         @RequestParam(required = false) Long roomId) {
        matchService.create(playerId, gameId, roomId);
        return "redirect:/matches";
    }

    @PostMapping("/{id}/result")
    public String submitResult(@PathVariable Long id,
                               @RequestParam String result,
                               @RequestParam int kills,
                               @RequestParam int assists) {
        matchService.submitResult(id, result, kills, assists);
        return "redirect:/matches";
    }
}
