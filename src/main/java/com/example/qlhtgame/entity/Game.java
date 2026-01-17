package com.example.qlhtgame.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private boolean online;

    private boolean active;
    private String version;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isActive() {
        return active;
    }

    public String getVersion() {
        return version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
