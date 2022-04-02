package com.codecool.leaguestatistics.model;

public class Striker extends Player implements Comparable<Striker>{
    private final Position position = Position.STRIKER;
    private int attackSkill;
    private int oneOnOne;

    public Striker(int attackSkill, int aggression, int injuryPotential, int oneOnOne) {
        super(aggression, injuryPotential);
        this.oneOnOne = oneOnOne;
        this.attackSkill = attackSkill;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public int compareTo(Striker o) {
        return o.oneOnOne+o.attackSkill - this.oneOnOne + this.attackSkill;
    }

    public int getAttackSkill() {
        return attackSkill;
    }

    public int getOneOnOne() {
        return oneOnOne;
    }

    public void setOneOnOne(int oneOnOne) {
        this.oneOnOne = oneOnOne;
    }

    public void setAttackSkill(int attackSkill) {
        this.attackSkill = attackSkill;
    }
}
