package com.codecool.leaguestatistics.model;

public class Defender extends Player implements Comparable<Defender>, DefenderPotential{
    private final int defenceSkill;

    public Defender(int defenceSkill, int aggression, int injuryPotential) {
        super(aggression, injuryPotential);
        this.defenceSkill = defenceSkill;
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }

    @Override
    public int compareTo(Defender o) {
        return o.defenceSkill - this.defenceSkill;
    }

    @Override
    public int getDefSkill() {
        return defenceSkill;
    }
}

