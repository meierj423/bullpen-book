package com.jack.bullpenbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private int wins = 0;
    private int losses = 0;
    private int gamesPlayed = 0;

    public Team() {}

    public Team(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Team setCity(String city) {
        this.city = city;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public Team setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public int getLosses() {
        return losses;
    }

    public Team setLosses(int losses) {
        this.losses = losses;
        return this;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public Team setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        return this;
    }
}
