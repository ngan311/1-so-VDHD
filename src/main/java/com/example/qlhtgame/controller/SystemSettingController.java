package com.example.qlhtgame.controller;

import com.example.qlhtgame.entity.SystemSetting;
import com.example.qlhtgame.service.SystemSettingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/settings")
public class SystemSettingController {

    private final SystemSettingService service;

    public SystemSettingController(SystemSettingService service) {
        this.service = service;
    }

    @GetMapping
    public List<SystemSetting> all() {
        return service.getAll();
    }

    @PostMapping
    public SystemSetting save(@RequestParam String key, @RequestParam String value) {
        return service.save(key, value);
    }
}
