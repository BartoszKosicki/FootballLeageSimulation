package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.Utils;
import lombok.Getter;

import java.util.*;

/**
 * Represents a team.
 */
@Getter
public class Team {

    private final String name;
    private int wins;
    private int draws;
    private int loses;
    private final Division division;

    private List<Player> players;
    private List<Goalkeeper> gk = new ArrayList<>();
    private List<Midfielder> allMidfielders = new ArrayList<>();
    private List<Defender> allDefenders = new ArrayList<>();
    private List<Striker> allStrikers = new ArrayList<>();

    private List<Player> squad = new ArrayList<>();
    private List<Midfielder> currentMidfielders = new ArrayList<>();
    private List<Striker> currentStrikers = new ArrayList<>();
    private Goalkeeper currentGoalkeeper;

    private boolean changeSquad = false;
    private List<String> teamTactic = new ArrayList<>();
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

    public void startNewSeason(){
        wins = 0;
        draws = 0;
        loses = 0;
        playerOff = 0;
    }

    public void beforeMatch(){
        checkPlayerStatus();
        getSquad();
        setPotentials();
    }

    private void setPotentials(){
        if (changeSquad){
            getTeamPotential();
        }
    }

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

    private void checkPlayerStatus(){
        int changes=0;
        if (changeSquad){
            for (Player player: players) {
                if (!player.canPlay && player.timeCantPlay.getCounter() > 0) {
                    player.timeCantPlay.setCounter();
                    } else {
                    player.setCanPlay(true);
                    changes++;
                }
            }
        }
        changeSquad = changes>0;
    }

    private void getSquad(){
        if (changeSquad) {
            setSquad();
        }
        teamTactic.add(currentTactic);
    }

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

    private void sortListByPlayersSkills(){
        Collections.sort(gk);
        Collections.sort(allDefenders);
        Collections.sort(allMidfielders);
        Collections.sort(allStrikers);
    }

    private String setTactics(){
        HashMap<String, Integer> tactics = new HashMap<>();
        tactics.put("442", getSumAllPlayerSkillsForChosenTactic("t442"));
        tactics.put("451", getSumAllPlayerSkillsForChosenTactic("t451"));
        tactics.put("541", getSumAllPlayerSkillsForChosenTactic("t541"));
        tactics.put("352", getSumAllPlayerSkillsForChosenTactic("t352"));
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : tactics.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        currentTactic = maxEntry.getKey();
        return maxEntry.getKey();
    }

    private void setSquad(){
        String bestTactics = setTactics();
        switch (bestTactics){
            case "442"-> chosePlayerToSquad(4,4,2);
            case "451"-> chosePlayerToSquad(4,5,1);
            case "541"-> chosePlayerToSquad(5,4,1);
            case "352"-> chosePlayerToSquad(3,5,2);
        }
    }

    private void chosePlayerToSquad(int defCount, int mfdCount, int strCount){
        this.squad = new ArrayList<>();
        currentMidfielders = new ArrayList<>();
        currentStrikers = new ArrayList<>();
        int gkCount = 1;
        for (int i = 0; i < gkCount; i++) {
            if (gk.get(i).canPlay) {
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
            if (allDefenders.get(i).canPlay){
                squad.add(allDefenders.get(i));
            } else if (defCount < allDefenders.size()) {
                defCount++;
            }
        }
        for (int i = 0; i < mfdCount; i++) {
            if (allMidfielders.get(i).canPlay){
                squad.add(allMidfielders.get(i));
                currentMidfielders.add(allMidfielders.get(i));
            } else if (mfdCount < allMidfielders.size()) {
                mfdCount++;
            }
        }
        for (int i = 0; i < strCount; i++) {
            if (allStrikers.get(i).canPlay){
                squad.add(allStrikers.get(i));
                currentStrikers.add(allStrikers.get(i));
            } else if (strCount < allStrikers.size()) {
                strCount++;
            }
        }
    }

    private int getSumAllPlayerSkillsForChosenTactic(String tactics){
        switch (tactics){
            case "t442"-> {
                return sumPlayerSkills(4,4,2);
            } case "t451"-> {
                return sumPlayerSkills(4,5,1);
            } case "t541"-> {
                return sumPlayerSkills(5,4,1);
            } default-> {
                return sumPlayerSkills(3,5,2);
            }
        }
    }

    private int sumPlayerSkills(int defendersCount, int midfieldersCount, int strikersCount){
        int sum = 0;
        for (int i = 0; i < defendersCount; i++) {
            if (allDefenders.get(i).canPlay) {
                sum += allDefenders.get(i).getDefSkill();
            } else if (defendersCount< allDefenders.size()) defendersCount+=1;
        }
        for (int i = 0; i < midfieldersCount; i++) {
            if (allMidfielders.get(i).canPlay)
            sum+= allMidfielders.get(i).getAttackSkill() + allMidfielders.get(i).getDefenceSkill();
            else if (midfieldersCount < allMidfielders.size()) midfieldersCount += 1;
        }
        for (int i = 0; i < strikersCount; i++) {
            if (allStrikers.get(i).canPlay)
            sum += allStrikers.get(i).getAttackPotential();
            else if (strikersCount < allStrikers.size()) strikersCount +=1;
        }
        return sum;
    }

    public Player searchForShotPlayer(){
        boolean isStriker = Utils.isAction(65, 35);
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
     * Helper method that finds best player with most scored goals in team
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

    public void setWins() {
        this.wins ++;
    }

    public void setDraws() {
        this.draws ++;
    }


    public void setLoses() {
        this.loses ++;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setChangeSquad(boolean changeSquad) {
        this.changeSquad = changeSquad;
    }

    public void setPlayerOff() {
        playerOff ++;
    }
    public List<Player> playingPlayers(){
        return this.squad;
    }

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
