package com.jack.bullpenbook.model;

import java.time.LocalDate;

public class Game {

    private Long id;
    private LocalDate date;
    private String opponent;
    private boolean home;        // true = home, false = away
    private int teamScore;
    private int opponentScore;

    public Game() {
    }

    public Game(Long id, LocalDate date, String opponent, boolean home,
                int teamScore, int opponentScore) {
        this.id = id;
        this.date = date;
        this.opponent = opponent;
        this.home = home;
        this.teamScore = teamScore;
        this.opponentScore = opponentScore;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getOpponent() { return opponent; }
    public void setOpponent(String opponent) { this.opponent = opponent; }

    public boolean isHome() { return home; }
    public void setHome(boolean home) { this.home = home; }

    public int getTeamScore() { return teamScore; }
    public void setTeamScore(int teamScore) { this.teamScore = teamScore; }

    public int getOpponentScore() { return opponentScore; }
    public void setOpponentScore(int opponentScore) { this.opponentScore = opponentScore; }
}
