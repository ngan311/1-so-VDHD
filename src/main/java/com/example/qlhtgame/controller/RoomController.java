package com.example.qlhtgame.controller;

import com.example.qlhtgame.entity.Room;
import com.example.qlhtgame.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public List<Room> all() {
        return service.getAll();
    }

    @PostMapping
    public Room create(@RequestBody Room room) {
        return service.create(room);
    }

    @PutMapping("/{id}")
    public Room update(@PathVariable Long id, @RequestBody Room room) {
        return service.update(id, room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}/status")
    public void changeStatus(@PathVariable Long id, @RequestParam String status) {
        service.changeStatus(id, status);
    }
}
