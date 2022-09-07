package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.view.texts.menuTexts.MenuTexts;

public class GameMenu {
    private final MenuTexts menuTexts;

    public GameMenu(MenuTexts menuTexts) {
        this.menuTexts = menuTexts;
    }

    public void displayMainMenu(){
        System.out.println(menuTexts.mainMenu);
    }
    public void displayNewGameMenu(){
        System.out.println(menuTexts.newGameMenu);

    }
}
