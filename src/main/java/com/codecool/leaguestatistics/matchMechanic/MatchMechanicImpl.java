package com.codecool.leaguestatistics.matchMechanic;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.footballLeague.MatchHistory;
import com.codecool.leaguestatistics.model.players.Playable;
import com.codecool.leaguestatistics.model.players.model.Goalkeeper;
import com.codecool.leaguestatistics.model.players.model.Midfielder;
import com.codecool.leaguestatistics.model.players.model.Player;
import com.codecool.leaguestatistics.model.players.model.Striker;
import com.codecool.leaguestatistics.model.team.Team;
import com.codecool.leaguestatistics.model.team.TeamService;
import com.codecool.leaguestatistics.model.team.TeamServiceImpl;
import com.codecool.leaguestatistics.view.PrintScoresTable;

public class MatchMechanicImpl implements MatchMechanic{

    private final TeamService teamService;

    public MatchMechanicImpl(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public MatchHistory playMatch(Team home, Team away) {
        teamService.prepareToMatch(home);
        teamService.prepareToMatch(away);
        int gameRounds = 10;
        matchRound(gameRounds, home, away, 15);
        matchRound(gameRounds, away, home, 0);
//        resolveMatch(team1, team2, result);
//        PrintScoresTable.printMatchResult(team1, team2, result);
//        return matchHistory.append("End of match \n\n");
        return null;
        }

    private void matchRound(int gameRounds, Team attackingTeam, Team defendingTeam, int handicap) {
        for (int i = 0; i < gameRounds; i++) {
            boolean teamShot = Utils.isMatchAction(attackingTeam.getAttackPotential()+handicap, defendingTeam.getDefencePotential());
            if(teamShot){
                Player shooter = attackingTeam.searchForShotPlayer();
                boolean scores = checkShoot(shooter, defendingTeam.getCurrentGoalkeeper());
                if (scores) {
//                    score[x]++;
//                    PrintScoresTable.printMatchFact(host, guest, score);
//                    matchHistory.append(host.getName()).append(" ").append(score[0]).append(" : ").append(score[1]).append(" ").append(guest.getName()).append("\n");
//                    matchHistory.append(shooter.getName()).append(" scores ");
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
