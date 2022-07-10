package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.Utils;

/**
 * Provides random names for Players and Teams
 */
public class NamesGenerator {
    /**
     * Generate the name of a player from the pool;
     * @return player name;
     */
    public static String getPlayerName(){
        return PlayerAndTeamNamesPool.getPlayerNames()[Utils.getRandomValue(0, PlayerAndTeamNamesPool.getPlayerNames().length-1)];
    }

    /**
     * Generate team name from the pool;
     * @return team name;
     */
    public static String getTeamName(){
        return PlayerAndTeamNamesPool.getTeamNames()[Utils.getRandomValue(0, PlayerAndTeamNamesPool.getTeamNames().length-1)] + " " +
                PlayerAndTeamNamesPool.getCityNames()[Utils.getRandomValue(0, PlayerAndTeamNamesPool.getCityNames().length-1)];
    }

}
