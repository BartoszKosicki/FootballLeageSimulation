package com.codecool.leaguestatistics;

import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.season.Season;
import com.codecool.leaguestatistics.controller.UserInputHandler;
import com.codecool.leaguestatistics.view.GameMenuTextDisplay;
import com.codecool.leaguestatistics.view.texts.menuTexts.MenuTexts;


public class MenuHandler {
    private boolean exit = false;
    private final MenuTexts texts = new MenuTexts();
    private final GameMenuTextDisplay textDisplay;
    private final UserInputHandler inputHandler;
    private final LeagueFactory leagueFactory = new LeagueFactory();

    public MenuHandler() {
        this.textDisplay = new GameMenuTextDisplay(texts);
        this.inputHandler = new UserInputHandler(texts);
    }

    int startProgram(){
        do {
            textDisplay.displayMainMenu();
            int x = getUserInput();
            if (x != 0) return x;
        } while (true);
    }

    private int getUserInput() {
        if (inputHandler.getMenuInput("[1-2]")==1) {
            int newGameInput = newGameMenu();
            if(newGameInput >= 1 && newGameInput <= 2 ){
                return 1;
            }
        }
        return 0;
    }

    private int newGameMenu() {
        textDisplay.displayNewGameMenu();
        return inputHandler.getMenuInput("[1-3]");
    }

}

