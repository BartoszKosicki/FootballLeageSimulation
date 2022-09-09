package com.codecool.leaguestatistics.footballLeague;

import com.codecool.leaguestatistics.RoundPair;
import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.matchMechanic.MatchMechanic;
import com.codecool.leaguestatistics.matchMechanic.MatchMechanicImpl;
import com.codecool.leaguestatistics.model.players.PlayerService;
import com.codecool.leaguestatistics.model.players.PlayerServiceImpl;
import com.codecool.leaguestatistics.model.team.Team;
import com.codecool.leaguestatistics.model.team.TeamService;
import com.codecool.leaguestatistics.model.team.TeamServiceImpl;

import java.util.Collections;
import java.util.List;

public class LeagueServiceImpl implements LeagueService {

    private final Utils utils;
    private final TeamService teamService = new TeamServiceImpl();
    private final PlayerService playerService = new PlayerServiceImpl();
    private final MatchMechanic matchMechanic;

    public LeagueServiceImpl() {
        utils = new Utils();
        matchMechanic = new MatchMechanicImpl(teamService);
    }

    @Override
    public void playNewLeagueSeason(League league) {
        List<Team> teamList = league.teams();
        resetLeagueStatistic(league);
        Collections.shuffle(teamList);
        playAllMatches(league);
    }

    private void playAllMatches(League league) {
        List<RoundPair> gameSchedule = utils.generateTimeTable();
        for (RoundPair round: gameSchedule) {
            matchMechanic.playMatch(league.teams().get(round.team1()), league.teams().get(round.team2()));
        }
    }

    /**
     * refresh matches history in league and statistics for each team and players before the start of the new season
     * @param league list of all teams in league
     */
    private void resetLeagueStatistic(League league){
        league.resetMatchesHistory();
        List<Team> teams = league.getTeams();
        teams.forEach(team -> {
            teamService.resetStatistics(team);
            team.getAllPlayers().forEach(playerService::resetPlayerStatistics);
        });
    }
}
