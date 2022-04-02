package com.codecool.leaguestatistics.model;

public class Goalkeeper extends Player implements Comparable<Goalkeeper>{
    private Position position = Position.GOALKEEPER;
    private int defenceSkill;
    private int oneOnOne;

    public Goalkeeper(int defenceSkill, int aggression, int injuryPotential, int oneOnOne) {
        super(aggression, injuryPotential);
        this.oneOnOne = oneOnOne;
        this.defenceSkill = defenceSkill;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getDefenceSkill() {
        return defenceSkill;
    }

    public void setDefenceSkill(int defenceSkill) {
        this.defenceSkill = defenceSkill;
    }

    public int getOneOnOne() {
        return oneOnOne;
    }

    public void setOneOnOne(int oneOnOne) {
        this.oneOnOne = oneOnOne;
    }

    @Override
    public int compareTo(Goalkeeper o) {
        return (o.defenceSkill+o.oneOnOne) - (this.defenceSkill+this.oneOnOne);
    }
}
