package com.codecool.leaguestatistics.model.players;

import com.codecool.leaguestatistics.factory.NamesGenerator;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents player
 */
@Getter
@Setter
public abstract class Player{
    private final Position position;
    private String name;
    private String team;
    private int aggression;
    private int injuryPotential;
    private Playable timeCantPlay = new Playable(0);
    private boolean canPlay;
    private int goals;

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
