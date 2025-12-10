package com.jack.bullpenbook.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate gameDate;
    private Integer homeTeamScore;
    private Integer awayTeamScore;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    public Game() {}

    public Game(Long id,
                LocalDate gameDate,
                Integer homeTeamScore,
                Integer awayTeamScore,
                Team homeTeam,
                Team awayTeam) {
        this.id = id;
        this.gameDate = gameDate;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public Long getId() {
        return id;
    }

    public Game setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public Game setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
        return this;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public Game setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
        return this;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public Game setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
        return this;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Game setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
        return this;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Game setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
        return this;
    }
}
