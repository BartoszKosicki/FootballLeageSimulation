package com.codecool.leaguestatistics.controller;

import com.codecool.leaguestatistics.RoundPair;
import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides all necessary methods for season simulation
 */
public class Season {

    private List<Team> WEST;
    private List<Team> CENTRAL;
    private List<Team> EAST;

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
        this.WEST = LeagueFactory.createLeague(12);
        this.CENTRAL = LeagueFactory.createLeague(12);
        this.EAST = LeagueFactory.createLeague(12);
        playAllGames();
        LeagueStatistics.getTopPlayersFromEachTeam(WEST);
        List<Team> allTeams = LeagueStatistics.getAllTeamsSorted(WEST);
        LeagueStatistics.printTable(allTeams);
        // Call Display methods below

    }

    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
        playSeason((ArrayList<Team>) WEST);
//        playSeason((ArrayList<Team>) CENTRAL);
//        playSeason((ArrayList<Team>) EAST);
    }

    /**
     * Plays single game between two teams and displays result after.
     */

    private void playSeason(ArrayList<Team> League){
        List<RoundPair> timeTable = Utils.generateTimeTable();
        for (RoundPair round: timeTable) {
            playMatch(League.get(round.getTeam1()), League.get(round.getTeam2()));
        }
    }

    private void playMatch(Team team1, Team team2) {
        team1.beforeMatch();
        team2.beforeMatch();
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
                    System.out.println(team1.getName() + " " + score[0] + team2.getName() + " " + score[1]);
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
                    System.out.println(team1.getName() + " " + score[0] + " : "+ score[1] + " " + team2.getName());
                }
            }
        }
        resolveMatch(team1, team2, score);
        System.out.println("Match is over.\n\n" +  team1.getName() + " " + score[0] +" : " + score[1] + " " + team2.getName());
        System.out.println(" ");
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
                player.setTimeCantPlay(new Playable(Utils.getRandomValue(1, 3)));
                team.setChangeSquad(true);
                team.setPlayerOff();
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
