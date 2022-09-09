package com.codecool.leaguestatistics.model.players.model;

import com.codecool.leaguestatistics.model.players.Position;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Goalkeeper extends Player implements Comparable<Goalkeeper>, DefenderPotential{
    private final int defenceSkill;
    private final int oneOnOne;
    private final int defLongShots;
    @Builder
    public Goalkeeper(int defenceSkill, int aggression, int injuryPotential, int oneOnOne, int defLongShots, String teamName, Position position) {
        super(aggression, injuryPotential, teamName, position);
        this.oneOnOne = oneOnOne;
        this.defenceSkill = defenceSkill;
        this.defLongShots =defLongShots;
    }

    @Override
    public int compareTo(Goalkeeper o) {
        return (o.defenceSkill+o.oneOnOne) - (this.defenceSkill+this.oneOnOne);
    }

    @Override
    public int getDefSkill() {
        return defenceSkill;
    }

    @Override
    public int getTotalSkill() {
        return defenceSkill+oneOnOne;
    }
}
