package com.codecool.leaguestatistics;

import com.codecool.leaguestatistics.season.SeasonService;
import com.codecool.leaguestatistics.season.SeasonServiceImpl;

public class GameHandler {

    private final MenuHandler menuHandler = new MenuHandler();
    private final SeasonService seasonService = new SeasonServiceImpl();

    public void startGame(){
        int userPick = menuHandler.startProgram();

    }
}
