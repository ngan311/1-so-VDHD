package com.example.qlhtgame.controllerview;

import com.example.qlhtgame.entity.Game;
import com.example.qlhtgame.entity.Room;
import com.example.qlhtgame.service.GameService;
import com.example.qlhtgame.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomViewController {

    private final RoomService roomService;
    private final GameService gameService;

    public RoomViewController(RoomService roomService, GameService gameService) {
        this.roomService = roomService;
        this.gameService = gameService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("room", new Room());
        model.addAttribute("games", gameService.getAll());
        return "room/list";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Room room, @RequestParam Long gameId) {
        Game g = new Game();
        g.setId(gameId);
        room.setGame(g);
        roomService.create(room);
        return "redirect:/rooms";
    }

    @GetMapping("/status/{id}")
    public String changeStatus(@PathVariable Long id, @RequestParam String status) {
        roomService.changeStatus(id, status);
        return "redirect:/rooms";
    }
}
