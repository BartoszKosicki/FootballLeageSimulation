package com.codecool.leaguestatistics.model;

public class Goalkeeper extends Player implements Comparable<Goalkeeper>, DefenderPotential{
    private final int defenceSkill;
    private final int oneOnOne;
    private final int defLongShots;

    public Goalkeeper(int defenceSkill, int aggression, int injuryPotential, int oneOnOne, int defLongShots) {
        super(aggression, injuryPotential);
        this.oneOnOne = oneOnOne;
        this.defenceSkill = defenceSkill;
        this.defLongShots =defLongShots;
    }

    public int getOneOnOne() {
        return oneOnOne;
    }


    @Override
    public int compareTo(Goalkeeper o) {
        return (o.defenceSkill+o.oneOnOne) - (this.defenceSkill+this.oneOnOne);
    }

    @Override
    public int getDefSkill() {
        return defenceSkill;
    }

    public int getDefLongShots() {
        return defLongShots;
    }
}
