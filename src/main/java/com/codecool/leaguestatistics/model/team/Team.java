package com.codecool.leaguestatistics.model.team;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.players.*;
import com.codecool.leaguestatistics.model.players.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Represents a team.
 */
@Getter
@Setter
public class Team {

    private final String name;
    private int wins;
    private int draws;
    private int loses;
    private final Division division;

    private final List<Player> allPlayers;
    private final List<Goalkeeper> goalkeepers = new ArrayList<>();
    private final List<Midfielder> midfielders = new ArrayList<>();
    private final List<Defender> defenders = new ArrayList<>();
    private final List<Striker> strikers = new ArrayList<>();

    private List<Player> squad = new ArrayList<>();
    private List<Midfielder> currentMidfielders = new ArrayList<>();
    private List<Striker> currentStrikers = new ArrayList<>();
    private Goalkeeper currentGoalkeeper;
    private boolean changeSquad = false;
    private final List<String> teamTactic = new ArrayList<>();
    private String currentTactic;
    private int playerOff=0;
    private int attackPotential=0;
    private int defencePotential=0;

    public Team(List<Player> allPlayers, String name, Division division) {
        this.name = name;
        this.allPlayers = allPlayers;
        this.division = division;
        setPlayersByPosition();
        sortListByPlayersSkills();
        setSquad();
        getTeamPotential();
    }

    /**
     * resets team statistics at the start of a new season
     */
    public void resetStatistic(){
        wins = 0;
        draws = 0;
        loses = 0;
        playerOff = 0;
    }

    /**
     * checks before the game whether players can play, establishes the composition and potential of the team;
     */
    public void beforeMatch(){
        checkPlayerStatus();
        getSquad();
        setPotentials();
    }

    /**
     * check if squad is changed and refresh team potential;
     */
    private void setPotentials(){
        if (changeSquad){
            getTeamPotential();
        }
    }

    /**
     * set offensive and defensive team potential;
     */
    private void getTeamPotential(){
        defencePotential = 0;
        attackPotential = 0;
        for (Player player : squad) {
            if (player instanceof DefenderPotential p) {
                defencePotential += p.getDefSkill();
            }
            if (player instanceof AttackerPotential p) {
                attackPotential += p.getAttackPotential();
            }
        }
    }

    /**
     * check if team need new tactic due to player suspension;
     */
    private void checkPlayerStatus(){
        int changes=0;
        if (changeSquad){
            for (Player player: allPlayers) {
                if (!player.isCanPlay() && player.getTimeCantPlay().getCounter() > 0) {
                    player.getTimeCantPlay().decreaseCounter();
                    } else {
                    player.setCanPlay(true);
                    changes++;
                }
            }
        }
        changeSquad = changes > 0;
    }

    /**
     * sets the line-up for the next match if any player is off;
     */
//    public void getSquad(){
//        if (changeSquad) {
//            setSquad();
//        }
//        teamTactic.add(currentTactic);
//    }

    /**
     * classifies players according to their position;
     */
    private void setPlayersByPosition(){
        for (Player player : allPlayers) {
            if (player instanceof Goalkeeper) {
                goalkeepers.add((Goalkeeper) player);
            } else if (player instanceof Defender) {
                defenders.add((Defender) player);
            } else if (player instanceof Midfielder) {
                midfielders.add((Midfielder) player);
            } else {
                strikers.add((Striker) player);
            }
        }
    }

    /**
     * sort the best players according to their position;
     */
    private void sortListByPlayersSkills(){
        Collections.sort(goalkeepers);
        Collections.sort(defenders);
        Collections.sort(midfielders);
        Collections.sort(strikers);
    }

    /**
     * sum team potential for each tactic and set best one to the next game;
     * @return
     */
    private String setTactics(){
        HashMap<String, Integer> tactics = new HashMap<>();
        tactics.put("442", getSumAllPlayersPotentialsForChosenTactic("t442"));
        tactics.put("451", getSumAllPlayersPotentialsForChosenTactic("t451"));
        tactics.put("541", getSumAllPlayersPotentialsForChosenTactic("t541"));
        tactics.put("352", getSumAllPlayersPotentialsForChosenTactic("t352"));
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : tactics.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        currentTactic = maxEntry.getKey();
        return maxEntry.getKey();
    }

    /**
     * pick the best players for the most favourable tactic;
     */
    private void setSquad(){
        String bestTactics = setTactics();
        switch (bestTactics){
            case "442"-> chosePlayersToSquad(4,4,2);
            case "451"-> chosePlayersToSquad(4,5,1);
            case "541"-> chosePlayersToSquad(5,4,1);
            case "352"-> chosePlayersToSquad(3,5,2);
        }
    }

    /**
     * auxiliary function for setting squad to the next match
     * @param defCount count defenders in picked tactics;
     * @param mfdCount count midfielders in picked tactics;
     * @param strCount count strikers in picked tactics;
     */
    private void chosePlayersToSquad(int defCount, int mfdCount, int strCount){
        this.squad = new ArrayList<>();
        currentMidfielders = new ArrayList<>();
        currentStrikers = new ArrayList<>();
        int gkCount = 1;
        for (int i = 0; i < gkCount; i++) {
            if (goalkeepers.get(i).isCanPlay()) {
                squad.add(goalkeepers.get(i));
                currentGoalkeeper = goalkeepers.get(i);
            } else if (gkCount < goalkeepers.size()) {
                gkCount++;
            } else {
                Goalkeeper temporaryGk =new Goalkeeper(30, 0, 0, 10, 20, this.name, Position.GOALKEEPER);
                squad.add(temporaryGk);
                currentGoalkeeper = temporaryGk;
            }
        }
        for (int i = 0; i < defCount; i++) {
            if (defenders.get(i).isCanPlay()){
                squad.add(defenders.get(i));
            } else if (defCount < defenders.size()) {
                defCount++;
            }
        }
        for (int i = 0; i < mfdCount; i++) {
            if (midfielders.get(i).isCanPlay()){
                squad.add(midfielders.get(i));
                currentMidfielders.add(midfielders.get(i));
            } else if (mfdCount < midfielders.size()) {
                mfdCount++;
            }
        }
        for (int i = 0; i < strCount; i++) {
            if (strikers.get(i).isCanPlay()){
                squad.add(strikers.get(i));
                currentStrikers.add(strikers.get(i));
            } else if (strCount < strikers.size()) {
                strCount++;
            }
        }
    }

    /**
     * sum players potential for chosen tactic
     * @param tactics name of the tactics;
     * @return sum of players potential;
     */
    private int getSumAllPlayersPotentialsForChosenTactic(String tactics){
        switch (tactics){
            case "t442"-> {
                return sumPlayersPotential(4,4,2);
            } case "t451"-> {
                return sumPlayersPotential(4,5,1);
            } case "t541"-> {
                return sumPlayersPotential(5,4,1);
            } default-> {
                return sumPlayersPotential(3,5,2);
            }
        }
    }

    /**
     * sum player potential
     * @param defendersCount count of defenders in tactic;
     * @param midfieldersCount count of midfielders in tactic;
     * @param strikersCount count of strikers in tactic;
     * @return sum players potential in specific tactic;
     */
    private int sumPlayersPotential(int defendersCount, int midfieldersCount, int strikersCount){
        int sum = 0;
        for (int i = 0; i < defendersCount; i++) {
            if (defenders.get(i).isCanPlay()) {
                sum += defenders.get(i).getDefSkill();
            } else if (defendersCount< defenders.size()) defendersCount+=1;
        }
        for (int i = 0; i < midfieldersCount; i++) {
            if (midfielders.get(i).isCanPlay())
            sum+= midfielders.get(i).getAttackSkill() + midfielders.get(i).getDefenceSkill();
            else if (midfieldersCount < midfielders.size()) midfieldersCount += 1;
        }
        for (int i = 0; i < strikersCount; i++) {
            if (strikers.get(i).isCanPlay())
            sum += strikers.get(i).getAttackPotential();
            else if (strikersCount < strikers.size()) strikersCount +=1;
        }
        return sum;
    }

    /**
     * checks which player attempted the goal;
     * @return player who attempted the goal;
     */
    public Player searchForShotPlayer(){
        boolean isStriker = Utils.isMatchAction(65, 35);
        if (isStriker){
            if (currentStrikers.size() != 1) {
                return currentStrikers.get(Utils.getRandomValue(0, currentStrikers.size() - 1));
            } else {
                return currentStrikers.get(0);
            }
        } else {
            return currentMidfielders.get(Utils.getRandomValue(0, currentMidfielders.size()-1));
        }
    }
    /**
     * Helper method that finds the best player with most scored goals in team
     */
    public Player getBestScorer() {
        return allPlayers.stream()
                .max(Comparator.comparing(Player::getGoals)).get();
    }

    /**
     * CurrentPoints is a sum of wins and draws points. For each win 3 points, for draw 1 point.
     */
    public int getCurrentPoints() {
        return wins*3 + draws;
    }

    /**
     * add win to team statistics;
     */
    public void setWins() {
        this.wins ++;
    }

    /**
     * add draw to team statistics;
     */
    public void setDraws() {
        this.draws ++;
    }

    /**
     * add lose to team statistics;
     */
    public void setLoses() {
        this.loses ++;
    }

    /**
     * set if squad should be change
     * @param changeSquad
     */
    public void setChangeSquad(boolean changeSquad) {
        this.changeSquad = changeSquad;
    }

    /**
     * resolve counter of suspended player
     */
    public void setPlayerOff() {
        playerOff ++;
    }

    /**
     * get the squad of the team that plays in the match
     * @return
     */
    public List<Player> playingPlayers(){
        return this.squad;
    }

    /**
     *
     * @return get the player in the biggest potential in the team;
     */
    public Player getBestSkillPlayerInTeam(){
        Player bestPlayer = null;
        int skillSum = 0;
        for (Player player : allPlayers) {
            if (player.getTotalSkill() > skillSum) {
                skillSum = player.getTotalSkill();
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }
}
