package com.codecool.leaguestatistics;

import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.season.Season;
import com.codecool.leaguestatistics.controller.UserInputHandler;
import com.codecool.leaguestatistics.view.GameMenu;
import com.codecool.leaguestatistics.view.texts.menuTexts.MenuTexts;

public class GameHandler {
    private boolean exit = false;
    private final MenuTexts texts = new MenuTexts();
    private final GameMenu menu;
    private final UserInputHandler inputHandler;
    private final LeagueFactory leagueFactory = new LeagueFactory();

    public GameHandler() {
        this.menu = new GameMenu(texts);
        this.inputHandler = new UserInputHandler(texts);
    }

    void startGame(){
        do {
            menu.displayMainMenu();
            int menuInput = inputHandler.getMenuInput("[1-2]");
            startNewGame(menuInput);
        } while (!exit);
    }

    private void startNewGame(int menuInput) {
        switch (menuInput) {
            case 1 -> {
                menu.displayNewGameMenu();
                int newGameMenuInput = inputHandler.getMenuInput("[1-4]");
                newGameMenu(newGameMenuInput);
                }
            case 2 -> {
                this.exit = true;
            }

        }
    }

    private void newGameMenu(int menuInput) {
        switch (menuInput) {
            case 1 -> {
                Season season = setupNewSeason();
            }
            case 2 -> {
                this.exit = true;
            }
            case 3 -> {
                this.exit = true;
            }
        }
    }

    private Season setupNewSeason() {
        return null;
    //        return new Season();
    }
}

