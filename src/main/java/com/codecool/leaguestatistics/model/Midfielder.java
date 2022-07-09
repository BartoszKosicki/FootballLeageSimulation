package com.codecool.leaguestatistics.model;

import lombok.Getter;

@Getter
public class Midfielder extends Player implements Comparable<Midfielder>, Attacker, DefenderPotential{
    private final int attackSkill;
    private final int defenceSkill;
    private final int longShot;


    public Midfielder(int attackSkill, int defenceSkill, int aggression, int injuryPotential, int distanceShot, String teamName, Position position) {
        super(aggression, injuryPotential, teamName, position);
        this.attackSkill = attackSkill;
        this.defenceSkill = defenceSkill;
        this.longShot = distanceShot;
    }

    @Override
    public int compareTo(Midfielder o) {
        return (o.defenceSkill+o.attackSkill) - (this.defenceSkill + this.attackSkill);
    }

    @Override
    public int getAttackPotential() {
        return this.attackSkill;
    }

    @Override
    public int getDefSkill() {
        return defenceSkill;
    }

    @Override
    public int getTotalSkill() {
        return attackSkill + defenceSkill;
    }
}
