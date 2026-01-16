package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.Game;
import com.example.qlhtgame.entity.Room;
import com.example.qlhtgame.repository.GameRepository;
import com.example.qlhtgame.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepo;
    private final GameRepository gameRepo;

    public RoomService(RoomRepository roomRepo, GameRepository gameRepo) {
        this.roomRepo = roomRepo;
        this.gameRepo = gameRepo;
    }

    public List<Room> getAll() {
        return roomRepo.findAll();
    }

    public Room create(Room data) {
        // set default
        if (data.getStatus() == null || data.getStatus().trim().isEmpty()) {
            data.setStatus("ONLINE");
        }

        // nếu client gửi game:{id:...} thì load game từ DB cho chắc
        if (data.getGame() != null && data.getGame().getId() != null) {
            Game g = gameRepo.findById(data.getGame().getId()).orElseThrow();
            data.setGame(g);
        }

        return roomRepo.save(data);
    }

    public Room update(Long id, Room data) {
        Room r = roomRepo.findById(id).orElseThrow();

        r.setName(data.getName());
        r.setCapacity(data.getCapacity());
        r.setStatus(data.getStatus());

        if (data.getGame() != null && data.getGame().getId() != null) {
            Game g = gameRepo.findById(data.getGame().getId()).orElseThrow();
            r.setGame(g);
        }

        return roomRepo.save(r);
    }

    public void delete(Long id) {
        roomRepo.deleteById(id);
    }

    public void changeStatus(Long id, String status) {
        Room r = roomRepo.findById(id).orElseThrow();
        r.setStatus(status);
        roomRepo.save(r);
    }
}
