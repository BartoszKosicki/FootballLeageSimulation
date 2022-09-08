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

    private final List<Player> players;
    private final List<Goalkeeper> gk = new ArrayList<>();
    private final List<Midfielder> allMidfielders = new ArrayList<>();
    private final List<Defender> allDefenders = new ArrayList<>();
    private final List<Striker> allStrikers = new ArrayList<>();

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


    public Team(List<Player> players, String name, Division division) {
        this.name = name;
        this.players = players;
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
            if (player instanceof Attacker p) {
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
            for (Player player: players) {
                if (!player.isCanPlay() && player.getTimeCantPlay().getCounter() > 0) {
                    player.getTimeCantPlay().setCounter();
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
    private void getSquad(){
        if (changeSquad) {
            setSquad();
        }
        teamTactic.add(currentTactic);
    }

    /**
     * classifies players according to their position;
     */
    private void setPlayersByPosition(){
        for (Player player : players) {
            if (player instanceof Goalkeeper) {
                gk.add((Goalkeeper) player);
            } else if (player instanceof Defender) {
                allDefenders.add((Defender) player);
            } else if (player instanceof Midfielder) {
                allMidfielders.add((Midfielder) player);
            } else {
                allStrikers.add((Striker) player);
            }
        }
    }

    /**
     * sort the best players according to their position;
     */
    private void sortListByPlayersSkills(){
        Collections.sort(gk);
        Collections.sort(allDefenders);
        Collections.sort(allMidfielders);
        Collections.sort(allStrikers);
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
            if (gk.get(i).isCanPlay()) {
                squad.add(gk.get(i));
                currentGoalkeeper = gk.get(i);
            } else if (gkCount < gk.size()) {
                gkCount++;
            } else {
                Goalkeeper temporaryGk =new Goalkeeper(30, 0, 0, 10, 20, this.name, Position.GOALKEEPER);
                squad.add(temporaryGk);
                currentGoalkeeper = temporaryGk;
            }
        }
        for (int i = 0; i < defCount; i++) {
            if (allDefenders.get(i).isCanPlay()){
                squad.add(allDefenders.get(i));
            } else if (defCount < allDefenders.size()) {
                defCount++;
            }
        }
        for (int i = 0; i < mfdCount; i++) {
            if (allMidfielders.get(i).isCanPlay()){
                squad.add(allMidfielders.get(i));
                currentMidfielders.add(allMidfielders.get(i));
            } else if (mfdCount < allMidfielders.size()) {
                mfdCount++;
            }
        }
        for (int i = 0; i < strCount; i++) {
            if (allStrikers.get(i).isCanPlay()){
                squad.add(allStrikers.get(i));
                currentStrikers.add(allStrikers.get(i));
            } else if (strCount < allStrikers.size()) {
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
            if (allDefenders.get(i).isCanPlay()) {
                sum += allDefenders.get(i).getDefSkill();
            } else if (defendersCount< allDefenders.size()) defendersCount+=1;
        }
        for (int i = 0; i < midfieldersCount; i++) {
            if (allMidfielders.get(i).isCanPlay())
            sum+= allMidfielders.get(i).getAttackSkill() + allMidfielders.get(i).getDefenceSkill();
            else if (midfieldersCount < allMidfielders.size()) midfieldersCount += 1;
        }
        for (int i = 0; i < strikersCount; i++) {
            if (allStrikers.get(i).isCanPlay())
            sum += allStrikers.get(i).getAttackPotential();
            else if (strikersCount < allStrikers.size()) strikersCount +=1;
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
        return players.stream()
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
        for (Player player : players) {
            if (player.getTotalSkill() > skillSum) {
                skillSum = player.getTotalSkill();
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }
}
