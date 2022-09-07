package com.codecool.leaguestatistics.footballLeague;

import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.team.Team;
import lombok.Getter;

import java.util.List;

@Getter
public class League {
    private final List<Team> teams;
    private final Division division;

    public League(List<Team> teams, Division division) {
        this.teams = teams;
        this.division = division;
    }
}
