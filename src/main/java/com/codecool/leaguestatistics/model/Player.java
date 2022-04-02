package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.NamesGenerator;

/**
 * Represents player
 */
public abstract class Player{

    protected String name;
    protected int aggression;
    protected int injuryPotential;
    protected Playable timeCantPlay;
    protected boolean canPlay;
    protected int goals;

    public Player(int aggression, int injuryPotential) {
        this.name = "Player " + Utils.getRandomValue(0,5000);
        this.aggression = aggression;
        this.injuryPotential = injuryPotential;
        this.goals = 0;
        this.canPlay =false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAggression() {
        return aggression;
    }

    public void setAggression(int aggression) {
        this.aggression = aggression;
    }

    public int getInjuryPotential() {
        return injuryPotential;
    }

    public void setInjuryPotential(int injuryPotential) {
        this.injuryPotential = injuryPotential;
    }

    public Playable getTimeCantPlay() {
        return timeCantPlay;
    }

    public void setTimeCantPlay(Playable timeCantPlay) {
        this.timeCantPlay = timeCantPlay;
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }
}
