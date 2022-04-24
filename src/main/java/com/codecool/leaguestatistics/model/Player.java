package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.NamesGenerator;
import com.codecool.leaguestatistics.factory.PlayerNames;

/**
 * Represents player
 */
public abstract class Player{
    protected final Position position;
    protected String name;
    protected String team;
    protected int aggression;
    protected int injuryPotential;
    protected Playable timeCantPlay = new Playable(0);
    protected boolean canPlay;
    protected int goals;

    public Player(int aggression, int injuryPotential, String team, Position position) {
        this.name = NamesGenerator.getPlayerName();
        this.aggression = aggression;
        this.injuryPotential = injuryPotential;
        this.goals = 0;
        this.canPlay = true;
        this.team = team;
        this.position = position;
    }

    public abstract int getTotalSkill();

    public void startNewSeason(){
    goals = 0;
    canPlay = true;
    timeCantPlay = new Playable(0);
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

    public void setGoals() {
        goals ++;
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

    public String getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }
}
