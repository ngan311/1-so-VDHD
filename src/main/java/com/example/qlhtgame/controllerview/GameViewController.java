package com.example.qlhtgame.controllerview;

import com.example.qlhtgame.entity.Game;
import com.example.qlhtgame.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameViewController {

    private final GameService service;

    public GameViewController(GameService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("games", service.getAll());
        model.addAttribute("game", new Game());
        return "game/list";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Game game) {
        service.create(game);
        return "redirect:/games";
    }

    @GetMapping("/hide/{id}")
    public String hide(@PathVariable Long id) {
        service.hide(id);
        return "redirect:/games";
    }

    @GetMapping("/unhide/{id}")
    public String unhide(@PathVariable Long id) {
        service.unhide(id);
        return "redirect:/games";
    }
}
