package com.codecool.leaguestatistics.footballLeague;

import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.team.Team;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
public final class League {
    private final List<Team> teams;
    private final Division division;
    private final String filePath;
    private List<MatchHistory> matchHistories;

    public League(List<Team> teams, Division division, String filePath) {
        this.teams = teams;
        this.division = division;
        this.filePath = filePath;
        this.matchHistories = new ArrayList<>();
    }

    public List<Team> teams() {
        return teams;
    }

    public Division division() {
        return division;
    }

    public String filePath() {
        return filePath;
    }
    public void resetMatchesHistory(){
        matchHistories = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (League) obj;
        return Objects.equals(this.teams, that.teams) &&
                Objects.equals(this.division, that.division) &&
                Objects.equals(this.filePath, that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teams, division, filePath);
    }

    @Override
    public String toString() {
        return "League[" +
                "teams=" + teams + ", " +
                "division=" + division + ", " +
                "filePath=" + filePath + ']';
    }


}
