package com.codecool.leaguestatistics.matchMechanic;

import com.codecool.leaguestatistics.footballLeague.MatchHistory;
import com.codecool.leaguestatistics.model.team.Team;

public interface MatchMechanic {
    MatchHistory playMatch(Team home, Team away);
}
