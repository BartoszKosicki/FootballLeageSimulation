package com.codecool.leaguestatistics.footballLeague;

import com.codecool.leaguestatistics.RoundPair;
import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.model.players.PlayerService;
import com.codecool.leaguestatistics.model.players.PlayerServiceImpl;
import com.codecool.leaguestatistics.model.players.model.Player;
import com.codecool.leaguestatistics.model.team.Team;
import com.codecool.leaguestatistics.model.team.TeamService;
import com.codecool.leaguestatistics.model.team.TeamServiceImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LeagueServiceImpl implements LeagueService {

    private final Utils utils;
    private final TeamService teamService = new TeamServiceImpl();
    private final PlayerService playerService = new PlayerServiceImpl();

    public LeagueServiceImpl() {
        utils = new Utils();
    }

    @Override
    public void playLeagueSeason(League league) {
        List<Team> teamList = league.teams();
        Collections.shuffle(teamList);
        resetTeamStatistic(teamList);
        List<RoundPair> gameSchedule = utils.generateTimeTable();

    }


    /**
     * refresh statistics each team and players before the start of the new season
     * @param League list of all teams in league
     */
    private void resetTeamStatistic(List<Team> League){
        League.forEach(team -> {
            teamService.resetStatistics(team);
            team.getPlayers().forEach(playerService::resetPlayerStatistics);
        });
    }
}
