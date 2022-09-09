package com.codecool.leaguestatistics.view;

import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.team.Team;
import com.codecool.leaguestatistics.model.players.model.Player;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides all necessary statistics of played season.
 */
public class PrintLeagueStatistics {

    /**
     * Gets all teams with the highest points order, if points are equal next deciding parameter is sum of goals of the team.
     */
    public static List<Team> getAllTeamsSorted(List<Team> teams) {
        List<Team> finalTable = new ArrayList<>();
        teams.stream()
                .sorted(Comparator.comparing(Team::getCurrentPoints).reversed())
                .forEach(finalTable::add);
        return finalTable;
    }

    /**
     * Gets all players from each team in one collection.
     */
    public static List<Player> getAllPlayers(List<Team> teams) {
        return teams.stream()
                .map(Team::getAllPlayers)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * Gets team with the longest name
     */
    public static Team getTeamWithTheLongestName(List<Team> teams) {
        return teams.stream()
                .max(Comparator.comparingInt(team->team.getName().length())).get();
    }

    /**OPTIONAL
     * Gets top teams with least number of lost matches.
     * If the amount of lost matches is equal, next deciding parameter is team's current points value.
     * @param teamsNumber The number of Teams to select.
     * @return Collection of selected Teams.
     */
    public static List<Team> getTopTeamsWithLeastLoses(List<Team> teams, int teamsNumber) {
        return teams.stream()
                .sorted(Comparator.comparing(Team::getLoses))
                .limit(teamsNumber)
                .toList();
    }

    /**
     * Gets a player with the biggest goals number from each team.
     */
    public static List<Player> getTopScorersFromEachTeam(List<Team> teams) {
        List<Player> theBestPlayers = new ArrayList<>();
                teams.stream()
                        .map(Team::getBestScorer)
                        .sorted(Comparator.comparing(Player::getGoals).reversed())
                        .forEach(theBestPlayers::add);
                theBestPlayers.forEach(player -> System.out.println(player.getName() + " is the best scorer team " +
                        player.getTeam() + ". He scored " + player.getGoals() + " goals"));
                return theBestPlayers;
    }
    /**
     * Gets all teams, where there are players with no scored goals.
     */
    public static List<Team> getTeamsWithPlayersWithoutGoals(List<Team> teams){
        throw new RuntimeException("getTeamsWithPlayersWithoutGoals method not implemented");
    }

    /**
     * Gets players with given or higher number of goals scored.
     * @param goals The minimal number of goals scored.
     * @return Collection of Players with given or higher number of goals scored.
     */
    public static List<Player> getPlayersWithAtLeastXGoals(List<Team> teams, int goals) {
        List<Player> scorers = teams.stream()
                .map(Team::getAllPlayers)
                .flatMap(Collection::stream).toList()
                .stream()
                .sorted(Comparator.comparing(Player::getGoals).reversed())
                .filter(player -> player.getGoals() >= goals).toList();

        scorers.forEach(player -> System.out.println(player.getName() + " " + player.getPosition() + " from team " + player.getTeam() + " scored " + player.getGoals() + " times.\n"));
        return scorers;
    }

    /**
     * Gets the player with the highest skill rate for given Division. For future use.
     */
    public static Player getMostTalentedPlayerInDivision(List<Team> teams) {
        return teams.stream()
                .map(Team::getBestSkillPlayerInTeam).max(Comparator.comparing(Player::getTotalSkill)).get();
    }

    /**
     * Get top players with the highest skill rate
     * @param players list of players from which we select the best players
     * @param numberPlayers number of top players to be displayed
     * @return list of top players
     */
    public static List<Player> getMostTalentedPlayersInDivision(List<Player> players, int numberPlayers) {
        List<Player> topTalentedPlayers = new ArrayList<>();
        players.stream()
                .sorted(Comparator.comparing(Player::getTotalSkill).reversed())
                .limit(numberPlayers)
                .forEach(topTalentedPlayers::add);
        return topTalentedPlayers;
    }

    /**
     * OPTIONAL
     * Returns the division with the greatest amount of points.
     * If there is more than one division with the same amount current points, then check the amounts of wins.
     */
    public static Division getStrongestDivision(List<Team> teams) {
        throw new RuntimeException("getStrongestDivision method not implemented");
    }

    /**
     * prints a list of results in the console
     * @param teams list of teams in a given league
     */
    public static void printTable(List<Team> teams){
        System.out.println("NAME     "+ "PTS" +  " W " + " D " + " L " + "TOTAL");
        teams.forEach(team -> System.out.println(team.getName() + "   " + team.getCurrentPoints() + " " +
                team.getWins() + "  " + team.getDraws() + "  " + team.getLoses()
                +"  " + (team.getLoses()+team.getDraws()+team.getWins())));
    }

    /**
     * selects the player from each team who has missed the most matches
     * @param teams list of teams in a given league
     * @return list players which missed the most matches
     */
    public static Team getTeamWithMostSuspendedPlayers(List<Team> teams){
        return teams.stream()
                .max(Comparator.comparing(Team::getPlayerOff)).get();
    }

}
