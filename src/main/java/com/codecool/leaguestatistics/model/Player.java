package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.NamesGenerator;
import com.codecool.leaguestatistics.factory.PlayerNames;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents player
 */
@Getter
@Setter
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

    public void setGoals() {
        goals ++;
    }

}
