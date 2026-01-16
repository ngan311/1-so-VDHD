package com.example.qlhtgame.controllerview;

import com.example.qlhtgame.entity.Player;
import com.example.qlhtgame.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/players")
public class PlayerViewController {

    private final PlayerService playerService;

    public PlayerViewController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public String listPlayers(Model model) {
        model.addAttribute("players", playerService.getAll());
        return "player/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("player", new Player());
        return "player/add";
    }

    @PostMapping("/add")
    public String addPlayer(@ModelAttribute Player player) {
        playerService.create(player);
        return "redirect:/players";
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        model.addAttribute("players", playerService.rankingByScore());
        return "player/ranking";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("player", playerService.getById(id));
        model.addAttribute("matches", playerService.getMatchHistory(id));
        return "player/detail";
    }

    @GetMapping("/lock/{id}")
    public String lockPlayer(@PathVariable Long id) {
        playerService.lock(id);
        return "redirect:/players";
    }

    @GetMapping("/unlock/{id}")
    public String unlockPlayer(@PathVariable Long id) {
        playerService.unlock(id);
        return "redirect:/players";
    }
}
