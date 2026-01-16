package com.example.qlhtgame.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;        // FIRST_WIN, TOTAL_10_MATCH
    private String name;
    private String description;

    private String type;        // WIN_COUNT, TOTAL_MATCH, SCORE
    private int threshold;      // điều kiện đạt

    // ===== getter & setter =====

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
