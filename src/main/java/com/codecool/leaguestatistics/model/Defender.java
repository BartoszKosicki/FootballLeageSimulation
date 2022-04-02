package com.codecool.leaguestatistics.model;

public class Defender extends Player implements Comparable<Defender>{
    private final Position position = Position.DEFENDER;
    private int defenceSkill;
    public Defender(int defenceSkill, int aggression, int injuryPotential) {
        super(aggression, injuryPotential);
        this.defenceSkill = defenceSkill;
    }

    public Position getPosition() {
        return position;
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }

    public void setDefenceSkill(int defenceSkill) {
        this.defenceSkill = defenceSkill;
    }

    @Override
    public int compareTo(Defender o) {
        return o.defenceSkill - this.defenceSkill;
    }
}

