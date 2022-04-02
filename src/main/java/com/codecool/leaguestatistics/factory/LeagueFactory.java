package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.model.*;


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
     * @param teamsInDivision Indicates number of teams are in division
     * @return Full set of teams with players
     */
    public static List<Team> createLeague(int teamsInDivision) {
        List<Integer> teamNumbers = createTeamNumber(teamsInDivision);
        List<Team> teamList = new ArrayList<>();
        for (int i = 0; i < teamsInDivision; i++) {
            List<Player> players = getPlayers(3,7,7,4);
            teamList.add(new Team());
        }
        return teamList;
    }

    /**
     * Returns a collection with a given amount of newly created players
     */

    private static Player createPlayer(Position position){
        switch (position) {
            case STRIKER -> {
                return new Striker(Utils.getRandomValue(50, 80), Utils.getRandomValue(1, 5), Utils.getRandomValue(0, 20), Utils.getRandomValue(1, 80));
            }
            case MIDFIELDER -> {
                return new Midfielder(Utils.getRandomValue(30, 50), Utils.getRandomValue(30, 50), Utils.getRandomValue(1, 5), Utils.getRandomValue(1, 20));
            }
            case DEFENDER -> {
                return new Defender(Utils.getRandomValue(50, 80), Utils.getRandomValue(1, 5), Utils.getRandomValue(1, 20));
            }
            default -> {
                return new Goalkeeper(Utils.getRandomValue(50, 80), Utils.getRandomValue(1, 5), Utils.getRandomValue(1, 10), Utils.getRandomValue(1, 80));
            }
        }
    }

    private static List<Player> getPlayers(int gkCount, int defCount, int mfdCound, int strCount) {
        List<Player> teamPlayers = new ArrayList<>();
        for (int i = 0; i < gkCount; i++) {
                teamPlayers.add(createPlayer(Position.GOALKEEPER));
            }
        for (int i = 0; i < defCount; i++) {
            teamPlayers.add(createPlayer(Position.DEFENDER));
        }
        for (int i = 0; i < mfdCound; i++) {
            teamPlayers.add(createPlayer(Position.MIDFIELDER));
        }
        for (int i = 0; i < strCount; i++) {
            teamPlayers.add(createPlayer(Position.STRIKER));
        } return teamPlayers;
    }

    private static List<Integer> createTeamNumber(int teamInLeague){
        List<Integer> teamNumbers = new ArrayList<>();
        for (int i = 0; i < teamInLeague; i++) {
            teamNumbers.add(i);
        }
        Collections.shuffle(teamNumbers);
        return teamNumbers;
    }
}
