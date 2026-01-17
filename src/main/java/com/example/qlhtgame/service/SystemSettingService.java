package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.SystemSetting;
import com.example.qlhtgame.repository.SystemSettingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemSettingService {

    private final SystemSettingRepository repo;

    public SystemSettingService(SystemSettingRepository repo) {
        this.repo = repo;
    }

    public List<SystemSetting> getAll() {
        return repo.findAll();
    }

    public SystemSetting save(String key, String value) {
        SystemSetting s = repo.findBySettingKey(key).orElse(null);

        if (s == null) {
            s = new SystemSetting();
            s.setSettingKey(key);
        }

        s.setSettingValue(value);
        return repo.save(s);
    }
}
