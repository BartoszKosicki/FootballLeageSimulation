package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.RoundPair;
import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.*;
import com.codecool.leaguestatistics.view.PrintFactory;

import java.util.*;

/**
 * Provides all necessary methods for season simulation
 */
public class Season {

    private List<Team> WEST;
    private List<Team> CENTRAL;
    private List<Team> EAST;
    int SeasonCount = 0;
    private final String westPathFile = "WestLeague.txt";
    private final String centralPathFile = "CentralLeague.txt";
    private final String eastPathFile = "EastLeague.txt";

    public Season() {
        WEST = new ArrayList<>();
        CENTRAL = new ArrayList<>();
        EAST = new ArrayList<>();
    }

    /**
     * Fills league with new teams and simulates all games in season.
     * After all games played calls table to be displayed.
     */
    public void run() {
        this.WEST = LeagueFactory.createLeague(12, Division.West);
        this.CENTRAL = LeagueFactory.createLeague(12, Division.Central);
        this.EAST = LeagueFactory.createLeague(12, Division.East);
        String next ="";
        do {
            SeasonCount++;
            playAllGames();
            saveToFile(WEST, westPathFile);
            saveToFile(CENTRAL, centralPathFile);
            saveToFile(EAST, eastPathFile);
            Scanner scanner = new Scanner(System.in);
            next = scanner.nextLine();

        } while (!Objects.equals(next, "q"));

//        LeagueStatistics.getTopPlayersFromEachTeam(WEST);
//        LeagueStatistics.getPlayersWithAtLeastXGoals(WEST, 3);
//        LeagueStatistics.getTopPlayersFromEachTeam(WEST);
//        PrintFactory.MostSuspendedTeam(LeagueStatistics.getTeamWithMostSuspendedPlayers(WEST));
//        PrintFactory.MostTalentedPlayer(LeagueStatistics.getMostTalentedPlayerInDivision(WEST), Division.West);
//        List<Player> allPlayers = LeagueStatistics.getAllPlayers(WEST);
//        PrintFactory.MostTalentedPlayers(LeagueStatistics.getMostTalentedPlayersInDivision(allPlayers, 20), Division.West);
        // Call Display methods below
    }

    private void saveToFile(List<Team> League, String filePath) {
        List<Team> allWestTeams = LeagueStatistics.getAllTeamsSorted(League);
        String dataToSaveWest = String.valueOf(PrintFactory.printTable(allWestTeams));
        SaveToFile.SaveToFile(dataToSaveWest, filePath);
        saveMostTalentedPlayer(League, filePath);
        saveBestScorers(League, filePath);
        saveBestScorersInTeam(League, filePath);

    }

    private void saveBestScorers(List<Team> League, String filePath) {
        List<Player> bestScorersList = LeagueStatistics.getPlayersWithAtLeastXGoals(League, 15);
        StringBuilder bestScorers = new StringBuilder("    BEST LEAGUE SCORERS   \n");
        for (int i = 0; i < bestScorersList.size(); i++) {
            Player players = bestScorersList.get(i);
            bestScorers.append(i+1).append(". ").append(players.getName()).append(" ").append(players.getPosition()).append(" from team ").append(players.getTeam()).append(" scores ").append(players.getGoals()).append("\n");
        }
        SaveToFile.SaveToFile(String.valueOf(bestScorers), filePath);
    }


    private void saveBestScorersInTeam(List<Team> League, String filePath) {
        List<Player> bestScorersInTeam = LeagueStatistics.getTopScorersFromEachTeam(League);
        StringBuilder bestScorers = new StringBuilder("\n    BEST TEAM SCORERS   \n");
        for (Player players: bestScorersInTeam) {
            bestScorers.append(players.getName()).append(" ").append(players.getPosition()).append(" from team ").append(players.getTeam()).append(" scores ").append(players.getGoals()).append("\n");
        }
        SaveToFile.SaveToFile(String.valueOf(bestScorers), filePath);
    }


    private void saveMostTalentedPlayer(List<Team> League, String filePath) {
        List<Player> allPlayers = LeagueStatistics.getAllPlayers(League);
        List<Player> mostTalentedPlayersInDivision = LeagueStatistics.getMostTalentedPlayersInDivision(allPlayers, 20);
        StringBuilder players = new StringBuilder("\n   MOST TALENTED PLAYERS   \n");
        for (int i = 0, mostTalentedPlayersInDivisionSize = mostTalentedPlayersInDivision.size(); i < mostTalentedPlayersInDivisionSize; i++) {
            Player player = mostTalentedPlayersInDivision.get(i);
            players.append(i + 1).append(". ").append(player.getName()).append(" from ").append(player.getTeam()).append(" with ").append(player.getTotalSkill()).append(" skill points. \n");
        }
        SaveToFile.SaveToFile(String.valueOf(players), filePath);
    }

    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
        playSeason((ArrayList<Team>) this.WEST, this.westPathFile);
        playSeason((ArrayList<Team>) CENTRAL, centralPathFile);
        playSeason((ArrayList<Team>) EAST,eastPathFile);
    }

    /**
     * Plays single game between two teams and displays result after.
     */

    private void playSeason(ArrayList<Team> League, String pathFile){
        startNewSeason(League);
        StringBuilder matchHistory = new StringBuilder("*****************\nSEASON " + SeasonCount + "\n*****************"
        +"\n\n\n");
        List<RoundPair> timeTable = Utils.generateTimeTable();
        for (RoundPair round: timeTable) {
            matchHistory.append(playMatch(League.get(round.getTeam1()), League.get(round.getTeam2())));
        }
        SaveToFile.SaveToFile(matchHistory.toString(), pathFile);
    }

    private void startNewSeason(ArrayList<Team> League){
        League.forEach(Team::startNewSeason);
        League.stream()
                .map(Team::getPlayers)
                .flatMap(Collection::stream)
                .forEach(Player::startNewSeason);
    }

    private StringBuilder playMatch(Team team1, Team team2) {
        team1.beforeMatch();
        team2.beforeMatch();
        StringBuilder matchHistory = new StringBuilder("");
        matchHistory.append("Match ").append(team1.getName()).append(" against ").append(team2.getName()).append("\n");
        int[] score =new int[] {0, 0};
        int gameRounds = 10;
        int homeHandicap = 15;
        for (int i = 0; i < gameRounds; i++) {
            boolean shot = Utils.isAction(team1.getAttackPotential()+homeHandicap, team2.getDefencePotential());
            if (shot){
                Player shooter = team1.searchForShotPlayer();
                boolean scores = checkShoot(shooter, team2.getCurrentGoalkeeper());
                if (scores) {
                    score[0]++;
                    PrintFactory.printMatchFact(team1, team2, score);
                    matchHistory.append(team1.getName()).append(" ").append(score[0]).append(" : ").append(score[1]).append(" ").append(team2.getName()).append("\n");
                    matchHistory.append(shooter.getName()).append(" scores ");
                }
            }
        }
        for (int i = 0; i < gameRounds; i++) {
            boolean shot = Utils.isAction(team2.getAttackPotential(), team1.getDefencePotential()+homeHandicap-5);
            if (shot){
                Player shooter = team2.searchForShotPlayer();
                boolean scores = checkShoot(shooter, team1.getCurrentGoalkeeper());
                if (scores) {
                    score[1]++;
                    PrintFactory.printMatchFact(team1, team2, score);
                    matchHistory.append(team1.getName()).append(" ").append(score[0]).append(" : ").append(score[1]).append(" ").append(team2.getName()).append("\n");
                    matchHistory.append(shooter.getName()).append(" scores ");
                }
            }
        }
        resolveMatch(team1, team2, score);
        PrintFactory.printMatchResult(team1, team2, score);
        return matchHistory.append("End of match \n\n");

    }



    private boolean checkShoot(Player player, Goalkeeper gk){
        boolean isGoal;
        if (player instanceof Striker){
            isGoal = Utils.isAction(((Striker) player).getOneOnOne(), gk.getOneOnOne());
        } else {
            isGoal = Utils.isAction(((Midfielder) player).getLongShot(), gk.getDefLongShots());
        }
        if (isGoal){
            player.setGoals();
            System.out.println(player.getName() + " scores!");
            return true;
        }
        return false;
    }

    private void resolveMatch(Team team1, Team team2, int[] score){
        checkPlayerForNextGame(team1);
        checkPlayerForNextGame(team2);
        givePointsForMatch(score, team1, team2);
    }

    private void checkPlayerForNextGame(Team team){
        for (Player player: team.playingPlayers()) {
            boolean isInjure = Utils.isAction(player.getInjuryPotential(),100 - player.getInjuryPotential());
            boolean isSuspended = Utils.isAction(player.getAggression(),100 - player.getAggression());
            if (isInjure || isSuspended) {
                int offForXTurn = Utils.getRandomValue(1, 3)+1;
                player.setTimeCantPlay(new Playable(offForXTurn));
                team.setChangeSquad(true);
                team.setPlayerOff();
                PrintFactory.playerCantPlayNextMatches(player, offForXTurn-1);
            }
        }
    }

    private void givePointsForMatch(int[] score, Team team1, Team team2){
        if (score[0]>score[1]) {
            setWinLose(team1, team2);
        } else if (score[0]<score[1]){
            setWinLose(team2, team1);
        } else{
            team1.setDraws();
            team2.setDraws();
        }
    }

    private void setWinLose(Team A, Team B){
        A.setWins();
        B.setLoses();
    }
}
