package com.codecool.leaguestatistics;

public class RoundPair {
    private final int team1;
    private final int team2;

    public RoundPair(int team1, int team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public int getTeam1() {
        return team1;
    }

    public int getTeam2() {
        return team2;
    }
}
