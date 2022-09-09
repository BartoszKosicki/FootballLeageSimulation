package com.codecool.leaguestatistics.season;

import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.footballLeague.League;
import com.codecool.leaguestatistics.footballLeague.LeagueService;
import com.codecool.leaguestatistics.footballLeague.LeagueServiceImpl;
import com.codecool.leaguestatistics.model.Division;

public class SeasonServiceImpl implements SeasonService {
    private final LeagueFactory leagueFactory;
    private final LeagueService leagueService;

    public SeasonServiceImpl() {
        this.leagueFactory = new LeagueFactory();
        this.leagueService = new LeagueServiceImpl();

    }

    @Override
    public Season setupNewSeason() {
        /**
         * If you want to change the number of teams in league, you should also change the generateTimeTable function in Utils.class.
         */
        int TEAMS_IN_LEAGUE = 12;
        League east = leagueFactory.createLeague(TEAMS_IN_LEAGUE, Division.East, "WestLeague.txt");
        League west = leagueFactory.createLeague(TEAMS_IN_LEAGUE, Division.West, "CentralLeague.txt");
        League central = leagueFactory.createLeague(TEAMS_IN_LEAGUE, Division.Central, "EastLeague.txt");

        return Season.builder()
                .CENTRAL(central)
                .EAST(east)
                .WEST(west)
                .build();
    }

    @Override
    public void playLeaguesRound(Season season) {
        leagueService.playNewLeagueSeason(season.getCENTRAL());
        leagueService.playNewLeagueSeason(season.getEAST());
        leagueService.playNewLeagueSeason(season.getEAST());
    }

}
