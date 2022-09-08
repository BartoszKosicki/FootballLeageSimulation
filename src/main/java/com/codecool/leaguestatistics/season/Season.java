package com.codecool.leaguestatistics.season;

import com.codecool.leaguestatistics.RoundPair;
import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.controller.FileManager;
import com.codecool.leaguestatistics.factory.LeagueFactory;
import com.codecool.leaguestatistics.footballLeague.League;
import com.codecool.leaguestatistics.model.*;
import com.codecool.leaguestatistics.model.players.*;
import com.codecool.leaguestatistics.model.players.model.Goalkeeper;
import com.codecool.leaguestatistics.model.players.model.Midfielder;
import com.codecool.leaguestatistics.model.players.model.Player;
import com.codecool.leaguestatistics.model.players.model.Striker;
import com.codecool.leaguestatistics.model.team.Team;
import com.codecool.leaguestatistics.view.PrintScoresTable;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

/**
 * Provides all necessary methods for season simulation
 */
@Getter
public class Season {

    private League WEST;
    private League CENTRAL;
    private League EAST;

    int SeasonCount = 0;
    private final String westPathFile = "WestLeague.txt";
    private final String centralPathFile = "CentralLeague.txt";
    private final String eastPathFile = "EastLeague.txt";
    private final LeagueFactory leagueFactory = new LeagueFactory();
    private final FileManager fileManager = new FileManager();
    private final Utils utils = new Utils();

    @Builder
    public Season(League WEST, League CENTRAL, League EAST) {
        this.WEST = WEST;
        this.CENTRAL = CENTRAL;
        this.EAST = EAST;
    }
    /**
     * Fills league with new teams and simulates all games in season.
     * After played all games calls table to be displayed.
     */
    public void run() {
        this.WEST = leagueFactory.createLeague(12, Division.West, westPathFile);
        this.CENTRAL = leagueFactory.createLeague(12, Division.Central, centralPathFile);
        this.EAST = leagueFactory.createLeague(12, Division.East, eastPathFile);
        String next ="";
        do {
            SeasonCount++;
            playAllGames();
//            fileManager.saveSeasonDataToFile(WEST, westPathFile);
//            fileManager.saveSeasonDataToFile(CENTRAL, centralPathFile);
//            fileManager.saveSeasonDataToFile(EAST, eastPathFile);
            Scanner scanner = new Scanner(System.in);
            next = scanner.nextLine();

        } while (!Objects.equals(next, "q"));
    }


    /**
     * Playing whole round. Everyone with everyone one time. Number of teams in league should be even.
     * Following solution represents the robin-round tournament.
     */
    private void playAllGames() {
//        playSeason((ArrayList<Team>) WEST, westPathFile);
//        playSeason((ArrayList<Team>) CENTRAL, centralPathFile);
//        playSeason((ArrayList<Team>) EAST,eastPathFile);
    }

    /**
     * Plays single game between two teams and displays result after.
     */
    private void playSeason(ArrayList<Team> League, String pathFile){
        startNewSeason(League);
        StringBuilder matchHistory = new StringBuilder("*****************\nSEASON " + SeasonCount + "\n*****************"
                +"\n\n\n");
        List<RoundPair> timeTable = utils.generateTimeTable();
        for (RoundPair round: timeTable) {
            matchHistory.append(playMatch(League.get(round.team1()), League.get(round.team2())));
        }
        fileManager.SaveToFile(matchHistory.toString(), pathFile);
    }

    /**
     * refresh statistics each team and players before the start of the new season
     * @param League list of all teams in league
     */
    private void startNewSeason(ArrayList<Team> League){
        League.forEach(Team::resetStatistic);
        League.stream()
                .map(Team::getPlayers)
                .flatMap(Collection::stream)
                .forEach(Player::startNewSeason);
    }

    /**
     * Resolve match against two teams.
     * @param team1 team which is hosting gets small handicap
     * @param team2 the team, who are guests
     * @return match result prepared for save
     */
    private StringBuilder playMatch(Team team1, Team team2) {
        team1.beforeMatch();
        team2.beforeMatch();
        StringBuilder matchHistory = new StringBuilder("");
        matchHistory.append("Match ").append(team1.getName()).append(" against ").append(team2.getName()).append("\n");
        int[] result =new int[] {0, 0};
        int gameRounds = 10;
        int homeHandicap = 15;
        matchRound(gameRounds, team1.getAttackPotential() + homeHandicap, team2.getDefencePotential(), team1, team1, team2, team2, result, 0, matchHistory);
        matchRound(gameRounds, team2.getAttackPotential(), team1.getDefencePotential() + homeHandicap - 5, team2, team1, team1, team2, result, 1, matchHistory);
        resolveMatch(team1, team2, result);
        PrintScoresTable.printMatchResult(team1, team2, result);
        return matchHistory.append("End of match \n\n");
    }

    /**
     * the match is divided into two main rounds, with the hosts attacking first and the visitors second;
     * @param gameRounds number of micro-cycles comprising the round of the match in which the shot is tested;
     * @param attackPotential sum of the offensive potential;
     * @param defencePotential sum of the defence potential;
     * @param attackTeam the team that attacks in a testing round
     * @param host team that has a handicap;
     * @param defendTeam team that is in defending mode;
     * @param guest team that don`t has a handicap;
     * @param score result of the match;
     * @param x position in score array;
     * @param matchHistory match log, including scorers and results;
     */
    private void matchRound(int gameRounds, int attackPotential, int defencePotential, Team attackTeam, Team host, Team defendTeam, Team guest, int[] score, int x, StringBuilder matchHistory) {
        for (int i = 0; i < gameRounds; i++) {
            boolean shot = Utils.isMatchAction(attackPotential, defencePotential);
            if (shot){
                Player shooter = attackTeam.searchForShotPlayer();
                boolean scores = checkShoot(shooter, defendTeam.getCurrentGoalkeeper());
                if (scores) {
                    score[x]++;
                    PrintScoresTable.printMatchFact(host, guest, score);
                    matchHistory.append(host.getName()).append(" ").append(score[0]).append(" : ").append(score[1]).append(" ").append(guest.getName()).append("\n");
                    matchHistory.append(shooter.getName()).append(" scores ");
                }
            }
        }
    }

    /**
     * auxiliary function to check goal chances and test if player scores
     * @param player player who try score;
     * @param gk goalkeeper who try to save shot;
     * @return result of the shot;
     */
    private boolean checkShoot(Player player, Goalkeeper gk){
        boolean isGoal;
        if (player instanceof Striker){
            isGoal = Utils.isMatchAction(((Striker) player).getOneOnOne(), gk.getOneOnOne());
        } else {
            isGoal = Utils.isMatchAction(((Midfielder) player).getLongShot(), gk.getDefLongShots());
        }
        if (isGoal){
            player.setGoals();
            System.out.println(player.getName() + " scores!");
            return true;
        }
        return false;
    }

    /**
     * check for player suspension and add points to the teams;
     * @param team1 team who was host;
     * @param team2 team who was guest;
     * @param score result of the match;
     */
    private void resolveMatch(Team team1, Team team2, int[] score){
        checkPlayerForNextGame(team1);
        checkPlayerForNextGame(team2);
        givePointsForMatch(score, team1, team2);
    }

    /**
     * test every player in the team if they are injured or suspended for red cards;
     * @param team team which players are tested;
     */
    private void checkPlayerForNextGame(Team team){
        for (Player player: team.playingPlayers()) {
            boolean isInjure = Utils.isMatchAction(player.getInjuryPotential(),100 - player.getInjuryPotential());
            boolean isSuspended = Utils.isMatchAction(player.getAggression(),100 - player.getAggression());
            if (isInjure || isSuspended) {
                int offForXTurn = Utils.getRandomValue(1, 3)+1;
                player.setTimeCantPlay(new Playable(offForXTurn));
                team.setChangeSquad(true);
                team.setPlayerOff();
                PrintScoresTable.playerCantPlayNextMatches(player, offForXTurn-1);
            }
        }
    }

    /**
     * awards match points to the teams;
     * @param score match result;
     * @param team1 host team;
     * @param team2 guest team;
     */
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

    /**
     * in the absence of a tie, adds points to the team
     * @param A winner;
     * @param B looser;
     */
    private void setWinLose(Team A, Team B){
        A.setWins();
        B.setLoses();
    }
}
