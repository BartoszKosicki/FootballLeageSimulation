package com.codecool.leaguestatistics.model.players.model;

import com.codecool.leaguestatistics.factory.NamesGenerator;
import com.codecool.leaguestatistics.model.players.Playable;
import com.codecool.leaguestatistics.model.players.Position;
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
    private Playable timeCantPlay = new Playable();
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

    /**
     * reset player stats at the beginning of a new season;
     */
    public void startNewSeason(){
    goals = 0;
    canPlay = true;
    timeCantPlay = new Playable();
    }

    public void setGoals() {
        goals ++;
    }

}
