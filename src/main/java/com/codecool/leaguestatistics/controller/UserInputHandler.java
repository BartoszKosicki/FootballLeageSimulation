package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.view.texts.menuTexts.MenuTexts;

import java.util.Scanner;

public class UserInputHandler {
    private MenuTexts text;
    Scanner scanner = new Scanner(System.in);

    public UserInputHandler(MenuTexts text) {
        this.text = text;
    }

    public int getMenuInput(String menuOption) {
        while (true) {
            String menuChoice = scanner.nextLine().trim().toUpperCase();
            if (!menuChoice.matches(menuOption)) {
                System.out.println(text.invalidInput);
            } else {
                return Integer.parseInt(menuChoice);
            }
        }
    }
}
