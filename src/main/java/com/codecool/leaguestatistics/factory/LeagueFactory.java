package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.footballLeague.League;
import com.codecool.leaguestatistics.model.*;
import com.codecool.leaguestatistics.model.players.*;
import com.codecool.leaguestatistics.model.players.model.*;
import com.codecool.leaguestatistics.model.team.Team;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Provides full set of teams with players
 */
public class LeagueFactory {

    /**
     * For each division, creates given amount of teams. Each team gets a newly created collection of players.
     * The amount of players should be taken from Utils.TEAM_SIZE
     *
     * @param teamsInDivision Indicates number of teams are in division
     * @return Full set of teams with players
     */
    public League createLeague(int teamsInDivision, Division division) throws IllegalArgumentException{
        if (teamsInDivision > 0 && teamsInDivision < 30) {
            List<Team> teamList = new ArrayList<>();
            createTeam(teamsInDivision, division, teamList);
            Collections.shuffle(teamList);
            return new League(teamList, division);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns a collection with a given amount of newly created players
     */
    private void createTeam(int teamsInDivision, Division division, List<Team> teamList) {
        for (int i = 0; i < teamsInDivision; i++) {
            String name = NamesGenerator.getTeamName();
            List<Player> players = createPlayers(3, 6, 6, 3, name);
            teamList.add(new Team(players, name, division));
        }
    }


    /**
     * set team squad
     *
     * @param gkCount  count of goalkeepers;
     * @param defCount count of defenders;
     * @param mfdCount count of midfielders;
     * @param strCount count of strikers;
     * @param teamName team name;
     * @return all players in team;
     */
    private List<Player> createPlayers(int gkCount, int defCount, int mfdCount, int strCount, String teamName) {
        List<Player> teamPlayers = new ArrayList<>();
        for (int i = 0; i < gkCount; i++) {
            teamPlayers.add(createPlayer(Position.GOALKEEPER, teamName));
        }
        for (int i = 0; i < defCount; i++) {
            teamPlayers.add(createPlayer(Position.DEFENDER, teamName));
        }
        for (int i = 0; i < mfdCount; i++) {
            teamPlayers.add(createPlayer(Position.MIDFIELDER, teamName));
        }
        for (int i = 0; i < strCount; i++) {
            teamPlayers.add(createPlayer(Position.STRIKER, teamName));
        }
        return teamPlayers;
    }


    private Player createPlayer(Position position, String teamName) {
        switch (position) {
            case STRIKER -> {
                return new Striker(Utils.getRandomValue(60, 100), Utils.getRandomValue(1, 5), Utils.getRandomValue(0, 20), Utils.getRandomValue(1, 80), teamName, Position.STRIKER);
            }
            case MIDFIELDER -> {
                return new Midfielder(Utils.getRandomValue(30, 50), Utils.getRandomValue(30, 50), Utils.getRandomValue(1, 5), Utils.getRandomValue(1, 20), Utils.getRandomValue(1, 50), teamName, Position.MIDFIELDER);
            }
            case DEFENDER -> {
                return new Defender(Utils.getRandomValue(60, 80), Utils.getRandomValue(1, 5), Utils.getRandomValue(1, 20), teamName, Position.DEFENDER);
            }
            default -> {
                return new Goalkeeper(Utils.getRandomValue(20, 40), Utils.getRandomValue(1, 5), Utils.getRandomValue(1, 10), Utils.getRandomValue(1, 60), Utils.getRandomValue(10, 60), teamName, Position.GOALKEEPER);
            }
        }
    }
}