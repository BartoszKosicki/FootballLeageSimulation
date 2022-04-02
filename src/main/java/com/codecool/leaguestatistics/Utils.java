package com.codecool.leaguestatistics;

import com.codecool.leaguestatistics.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Provides repetitive methods or data.
 */
public class Utils {

    public static final int TEAM_SIZE = 11;
    public static int index = 0;
    /**
     * Gets a random value from range
     */
    public static int getRandomValue(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static List<RoundPair> generateTimeTable(){
        List<RoundPair> timeTable = new ArrayList<>();
        Collections.addAll(timeTable, new RoundPair(11,0), new RoundPair(1,10), new RoundPair(2,9), new RoundPair(3,8), new RoundPair(4,7), new RoundPair(5,6));
        Collections.addAll(timeTable, new RoundPair(6,11), new RoundPair(7,5), new RoundPair(8,4), new RoundPair(9,3), new RoundPair(10,2), new RoundPair(0,1));
        Collections.addAll(timeTable, new RoundPair(11,1), new RoundPair(2,0), new RoundPair(3,10), new RoundPair(4,9), new RoundPair(5,8), new RoundPair(6,7));
        Collections.addAll(timeTable, new RoundPair(7,11), new RoundPair(8,6), new RoundPair(9,5), new RoundPair(10,4), new RoundPair(0,3), new RoundPair(1,2));
        Collections.addAll(timeTable, new RoundPair(11,2), new RoundPair(3,1), new RoundPair(4,0), new RoundPair(5,10), new RoundPair(6,9), new RoundPair(7,8));
        Collections.addAll(timeTable, new RoundPair(11,8), new RoundPair(9,7), new RoundPair(10,6), new RoundPair(0,5), new RoundPair(1,4), new RoundPair(2,3));
        Collections.addAll(timeTable, new RoundPair(3,11), new RoundPair(4,2), new RoundPair(5,1), new RoundPair(6,0), new RoundPair(7,10), new RoundPair(8,9));
        Collections.addAll(timeTable, new RoundPair(11,9), new RoundPair(10,8), new RoundPair(0,7), new RoundPair(1,6), new RoundPair(2,5), new RoundPair(3,4));
        Collections.addAll(timeTable, new RoundPair(4,11), new RoundPair(5,3), new RoundPair(6,2), new RoundPair(7,1), new RoundPair(8,0), new RoundPair(9,10));
        Collections.addAll(timeTable, new RoundPair(11,10), new RoundPair(0,9), new RoundPair(1,8), new RoundPair(3,7), new RoundPair(3,6), new RoundPair(4,5));
        Collections.addAll(timeTable, new RoundPair(5,11), new RoundPair(6,4), new RoundPair(7,3), new RoundPair(8,2), new RoundPair(9,1), new RoundPair(10,0));
        return timeTable;
    }

    private static int setPercentageToAction(int chanceToGo, int chanceNotToGo){
        int sum = chanceToGo + chanceNotToGo;
        return chanceToGo * 100 / sum;
    }
    public static boolean isAction(int chanceToSuccess, int chanceToFail){
        int attackPercentage = setPercentageToAction(chanceToSuccess, chanceToFail);
        return getRandomValue(0,100) <= attackPercentage;
    }

}

