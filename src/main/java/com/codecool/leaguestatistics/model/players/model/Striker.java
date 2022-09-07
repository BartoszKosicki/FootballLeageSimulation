package com.codecool.leaguestatistics.model.players.model;

import com.codecool.leaguestatistics.model.players.Position;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Striker extends Player implements Comparable<Striker>, Attacker{
    private final int attackSkill;
    private final int oneOnOne;
    @Builder
    public Striker(int attackSkill, int aggression, int injuryPotential, int oneOnOne, String teamName, Position position) {
        super(aggression, injuryPotential, teamName,position);
        this.oneOnOne = oneOnOne;
        this.attackSkill = attackSkill;
    }

    @Override
    public int compareTo(Striker o) {
        return o.oneOnOne+o.attackSkill - this.oneOnOne + this.attackSkill;
    }


    @Override
    public int getAttackPotential() {
        return this.attackSkill;
    }

    @Override
    public int getTotalSkill() {
        return this.attackSkill;
    }
}
