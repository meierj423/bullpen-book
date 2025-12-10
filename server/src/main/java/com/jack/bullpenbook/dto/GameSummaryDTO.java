package com.jack.bullpenbook.dto;

public class GameSummaryDTO {

    private Long gameId;
    private String gameDate;

    private Long teamId;
    private String teamName;

    private Long opponentId;
    private String opponentName;

    private boolean home;
    private int teamScore;
    private int opponentScore;

    public GameSummaryDTO() {
    }

    public Long getGameId() {
        return gameId;
    }

    public GameSummaryDTO setGameId(Long gameId) {
        this.gameId = gameId;
        return this;
    }

    public String getGameDate() {
        return gameDate;
    }

    public GameSummaryDTO setGameDate(String gameDate) {
        this.gameDate = gameDate;
        return this;
    }

    public Long getTeamId() {
        return teamId;
    }

    public GameSummaryDTO setTeamId(Long teamId) {
        this.teamId = teamId;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public GameSummaryDTO setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public Long getOpponentId() {
        return opponentId;
    }

    public GameSummaryDTO setOpponentId(Long opponentId) {
        this.opponentId = opponentId;
        return this;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public GameSummaryDTO setOpponentName(String opponentName) {
        this.opponentName = opponentName;
        return this;
    }

    public boolean isHome() {
        return home;
    }

    public GameSummaryDTO setHome(boolean home) {
        this.home = home;
        return this;
    }

    public int getTeamScore() {
        return teamScore;
    }

    public GameSummaryDTO setTeamScore(int teamScore) {
        this.teamScore = teamScore;
        return this;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public GameSummaryDTO setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
        return this;
    }
}