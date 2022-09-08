package com.codecool.leaguestatistics.model.team;

public class TeamServiceImpl implements TeamService{
    @Override
    public void resetStatistics(Team team) {
        team.setWins(0);
        team.setDraws(0);
        team.setLoses(0);
        team.setPlayerOff(0);
    }
}
