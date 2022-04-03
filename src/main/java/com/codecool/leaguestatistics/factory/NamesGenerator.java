package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.Utils;

/**
 * Provides random names for Players and Teams
 */
public class NamesGenerator {

    public static String getPlayerName(){
        return PlayerNames.getPlayerNames()[Utils.getRandomValue(0,PlayerNames.getPlayerNames().length-1)];
    }

    public static String getTeamName(){
        return PlayerNames.getTeamNames()[Utils.getRandomValue(0, PlayerNames.getTeamNames().length-1)] + " " +
                PlayerNames.getCityNames()[Utils.getRandomValue(0,PlayerNames.getCityNames().length-1)];
    }

}
