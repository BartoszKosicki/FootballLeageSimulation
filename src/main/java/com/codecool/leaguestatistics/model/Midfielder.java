package com.codecool.leaguestatistics.model;

public class Midfielder extends Player implements Comparable<Midfielder>{
    private final Position position = Position.MIDFIELDER;
    private int attackSkill;
    private int defenceSkill;


    public Midfielder(int attackSkill, int defenceSkill, int aggression, int injuryPotential) {
        super(aggression, injuryPotential);
        this.attackSkill = attackSkill;
        this.defenceSkill = defenceSkill;
    }

    public Position getPosition() {
        return position;
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

    public void setAttackSkill(int attackSkill) {
        this.attackSkill = attackSkill;
    }

    public void setDefenceSkill(int defenceSkill) {
        this.defenceSkill = defenceSkill;
    }
}
