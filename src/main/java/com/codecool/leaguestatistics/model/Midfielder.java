package com.codecool.leaguestatistics.model;

public class Midfielder extends Player implements Comparable<Midfielder>, Attacker, DefenderPotential{
    private final int attackSkill;
    private final int defenceSkill;
    private final int longShot;


    public Midfielder(int attackSkill, int defenceSkill, int aggression, int injuryPotential, int distanceShot) {
        super(aggression, injuryPotential);
        this.attackSkill = attackSkill;
        this.defenceSkill = defenceSkill;
        this.longShot = distanceShot;
    }



    @Override
    public int compareTo(Midfielder o) {
        return (o.defenceSkill+o.attackSkill) - (this.defenceSkill + this.attackSkill);
    }

    public int getAttackSkill() {
        return attackSkill;
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }


    @Override
    public int getAttackPotential() {
        return this.attackSkill;
    }

    @Override
    public int getDefSkill() {
        return defenceSkill;
    }

    public int getLongShot() {
        return longShot;
    }
}
