package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.model.Team;
import com.codecool.leaguestatistics.model.players.Player;
import com.codecool.leaguestatistics.view.PrintLeagueStatistics;
import com.codecool.leaguestatistics.view.PrintScoresTable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class SaveToFile {
    public static void SaveToFile(String data, String pathFile) {
        try {
            FileWriter fw = new FileWriter(pathFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(data);
            bw.newLine();
            bw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void saveSeasonDataToFile(List<Team> Division, String filePath) {
        List<Team> allDivisionTeams = PrintLeagueStatistics.getAllTeamsSorted(Division);
        String dataToSave = String.valueOf(PrintScoresTable.printTable(allDivisionTeams));
        SaveToFile(dataToSave, filePath);
        saveMostTalentedPlayer(Division, filePath);
        saveBestScorers(Division, filePath);
        saveBestScorersInTeam(Division, filePath);
    }

    public static void saveBestScorers(List<Team> League, String filePath) {
        List<Player> bestScorersList = PrintLeagueStatistics.getPlayersWithAtLeastXGoals(League, 15);
        StringBuilder bestScorers = new StringBuilder("    BEST LEAGUE SCORERS   \n");
        for (int i = 0; i < bestScorersList.size(); i++) {
            Player players = bestScorersList.get(i);
            bestScorers.append(i+1).append(". ").append(players.getName()).append(" ").append(players.getPosition()).append(" from team ").append(players.getTeam()).append(" scores ").append(players.getGoals()).append("\n");
        }
        SaveToFile(String.valueOf(bestScorers), filePath);
    }

    public static void saveBestScorersInTeam(List<Team> League, String filePath) {
        List<Player> bestScorersInTeam = PrintLeagueStatistics.getTopScorersFromEachTeam(League);
        StringBuilder bestScorers = new StringBuilder("\n    BEST TEAM SCORERS   \n");
        for (Player players: bestScorersInTeam) {
            bestScorers.append(players.getName()).append(" ").append(players.getPosition()).append(" from team ").append(players.getTeam()).append(" scores ").append(players.getGoals()).append("\n");
        }
        SaveToFile(String.valueOf(bestScorers), filePath);
    }

    public static void saveMostTalentedPlayer(List<Team> League, String filePath) {
        List<Player> allPlayers = PrintLeagueStatistics.getAllPlayers(League);
        List<Player> mostTalentedPlayersInDivision = PrintLeagueStatistics.getMostTalentedPlayersInDivision(allPlayers, 20);
        StringBuilder players = new StringBuilder("\n   MOST TALENTED PLAYERS   \n");
        for (int i = 0, mostTalentedPlayersInDivisionSize = mostTalentedPlayersInDivision.size(); i < mostTalentedPlayersInDivisionSize; i++) {
            Player player = mostTalentedPlayersInDivision.get(i);
            players.append(i + 1).append(". ").append(player.getName()).append(" from ").append(player.getTeam()).append(" with ").append(player.getTotalSkill()).append(" skill points. \n");
        }
        SaveToFile(String.valueOf(players), filePath);
    }
}

