package com.jack.bullpenbook.dto;

public class StandingsDTO {

    private Long teamId;
    private String teamName;
    private String city;
    private int wins;
    private int losses;
    private int gamesPlayed;
    private double winPercentage;

    public StandingsDTO() {
    }

    public Long getTeamId() {
        return teamId;
    }

    public StandingsDTO setTeamId(Long teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public StandingsDTO setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StandingsDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public int getWins() {
        return wins;
    }

    public StandingsDTO setWins(int wins) {
        this.wins = wins;
        return this;
    }

    public int getLosses() {
        return losses;
    }

    public StandingsDTO setLosses(int losses) {
        this.losses = losses;
        return this;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public StandingsDTO setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        return this;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public StandingsDTO setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
        return this;
    }
}