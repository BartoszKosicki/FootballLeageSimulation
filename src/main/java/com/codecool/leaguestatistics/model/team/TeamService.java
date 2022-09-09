package com.codecool.leaguestatistics.model.team;

import com.codecool.leaguestatistics.model.players.model.Player;

public interface TeamService {
    void resetStatistics(Team team);

    Player searchForShotPlayer(Team team);
    void prepareToMatch(Team team);

    boolean checkPlayerStatus(Team team);

    int getTeamDefenderPotential(Team team);
    int getTeamOffensivePotential(Team team);
}
