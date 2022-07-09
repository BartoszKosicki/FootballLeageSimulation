package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.players.Player;
import com.codecool.leaguestatistics.model.Team;

import java.util.List;

public class PrintScoresTable {

    public static StringBuilder printTable(List<Team>teams){
        Team longestNameTeam = PrintLeagueStatistics.getTeamWithTheLongestName(teams);
        String teamHeaderName = "       TEAM NAME" + " ".repeat(longestNameTeam.getName().length());
        int nameHeaderLength = teamHeaderName.length();
        String teamHeader = teamHeaderName + " PTS " + "  W " + "   D " + "   L ";
        System.out.println("\n"+"*".repeat(teamHeader.length()));
        StringBuilder results = new StringBuilder("\n" + "*".repeat(teamHeader.length())+"\n");
        System.out.println(teamHeader);
        results.append(teamHeader).append("\n");
        for (int i = 0, teamsSize = teams.size(); i < teamsSize; i++) {
            Team team = teams.get(i);
            String points, wins, draws, lose, iterator;
            iterator = formatTeamPosition(i);
            String name = team.getName() + " ".repeat(nameHeaderLength - team.getName().length() + 1-iterator.length());
            points = getNumbersToTable(team.getCurrentPoints());
            wins = getNumbersToTable(team.getWins());
            draws = getNumbersToTable(team.getDraws());
            lose = getNumbersToTable(team.getLoses());
            System.out.println(iterator + name + points + wins + draws + lose);
            results.append(iterator).append(name).append(points).append(wins).append(draws).append(lose).append("\n");
        }
        System.out.println("*".repeat(teamHeader.length())+"\n");
        return results.append("*".repeat(teamHeader.length())).append("\n");
    }
    private static String formatTeamPosition(int number){
        if (number + 1 < 10)
        return (number + 1) + ".  ";
        else return (number + 1) + ". ";
    }

    private static String getNumbersToTable(int number){
        if (number >= 10){
            return number + "   ";
        } else if (number >= 100) {
            return number + "";
        } else {
            return " " + number + "   ";
        }
    }

    public static void MostSuspendedTeam(Team team){
        System.out.println("In team " + team.getName() + " player was unable to play " + team.getPlayerOff() + "times.");
    }

    public  static void MostTalentedPlayers(List<Player> players, Division division){
        System.out.println("\nMost Talented players in " + division);
        for (int i = 0, playersSize = players.size(); i < playersSize; i++) {
            Player player = players.get(i);
            System.out.println((i+1)+ ". " + player.getName() + " " + player.getPosition() + " from " + player.getTeam() + " " + player.getTotalSkill() + " skill points.");
        }
    }

    public  static void MostTalentedPlayer(Player player, Division division){
        System.out.println("\nMost Talented player in " + division);
        System.out.println(player.getName().toUpperCase() + " " + player.getPosition() + " from " + player.getTeam() + " " + player.getTotalSkill() + " skill points.");
    }
    public static void printMatchResult(Team team1, Team team2, int[] score) {
        System.out.println("Match is over. " +  team1.getName() + " " + score[0] +" : " + score[1] + " " + team2.getName()+"\n*******************************\n");
        System.out.println(" ");
    }
    public static void printMatchFact(Team team1, Team team2, int[] score) {
        System.out.println(team1.getName() + " " + score[0] + " : " + score[1] + " " + team2.getName() + "\n");
    }
    public static void playerCantPlayNextMatches(Player player, int offForXTurn){
        System.out.println("Player " + player.getName() + " from " + player.getTeam() + " out for " + offForXTurn + "match(es)");
    }
}
