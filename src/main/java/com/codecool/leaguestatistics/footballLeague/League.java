package com.codecool.leaguestatistics.footballLeague;

import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.team.Team;
import lombok.Getter;

import java.util.List;

@Getter
public record League(List<Team> teams, Division division, String filePath) {


}
