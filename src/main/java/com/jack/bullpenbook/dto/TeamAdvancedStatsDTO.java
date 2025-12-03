package com.jack.bullpenbook.dto;

public class TeamAdvancedStatsDTO {

    private int games;
    private long runsScored;
    private long runsAllowed;
    private long runDifferential;
    private double expectedWinPct;

    public TeamAdvancedStatsDTO() {
    }

    public int getGames() {
        return games;
    }

    public TeamAdvancedStatsDTO setGames(int games) {
        this.games = games;
        return this;
    }

    public long getRunsScored() {
        return runsScored;
    }

    public TeamAdvancedStatsDTO setRunsScored(long runsScored) {
        this.runsScored = runsScored;
        return this;
    }

    public long getRunsAllowed() {
        return runsAllowed;
    }

    public TeamAdvancedStatsDTO setRunsAllowed(long runsAllowed) {
        this.runsAllowed = runsAllowed;
        return this;
    }

    public long getRunDifferential() {
        return runDifferential;
    }

    public TeamAdvancedStatsDTO setRunDifferential(long runDifferential) {
        this.runDifferential = runDifferential;
        return this;
    }

    public double getExpectedWinPct() {
        return expectedWinPct;
    }

    public TeamAdvancedStatsDTO setExpectedWinPct(double expectedWinPct) {
        this.expectedWinPct = expectedWinPct;
        return this;
    }
}