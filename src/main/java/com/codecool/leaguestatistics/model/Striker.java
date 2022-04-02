package com.codecool.leaguestatistics.model;

public class Striker extends Player implements Comparable<Striker>, Attacker{
    private final int attackSkill;
    private final int oneOnOne;

    public Striker(int attackSkill, int aggression, int injuryPotential, int oneOnOne) {
        super(aggression, injuryPotential);
        this.oneOnOne = oneOnOne;
        this.attackSkill = attackSkill;
    }

    @Override
    public int compareTo(Striker o) {
        return o.oneOnOne+o.attackSkill - this.oneOnOne + this.attackSkill;
    }

    public int getOneOnOne() {
        return oneOnOne;
    }

    @Override
    public int getAttackPotential() {
        return this.attackSkill;
    }
}
