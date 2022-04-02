package com.codecool.leaguestatistics.model;

import com.codecool.leaguestatistics.factory.NamesGenerator;

import java.util.*;

/**
 * Represents a team.
 */
public class Team {

    private String name;
    private int wins;
    private int draws;
    private int loses;
    private List<Player> players;
    private List<Goalkeeper> gk = new ArrayList<>();
    private List<Midfielder> midfielders = new ArrayList<>();
    private List<Defender> defenders = new ArrayList<>();
    private List<Striker> strikers = new ArrayList<>();
    private List<Player> squad;
    private int number;
    private boolean changeSquad = false;


    public Team(List<Player> players) {
        this.name = NamesGenerator.getTeamName();
        this.players = players;
        setPlayersByPosition();
        sortListByPlayersSkills();
    }

    public Team() {
    }

    private void setPlayersByPosition(){
        for (Player player : players) {
            if (player instanceof Goalkeeper) {
                gk.add((Goalkeeper) player);
            } else if (player instanceof Defender) {
                defenders.add((Defender) player);
            } else if (player instanceof Midfielder) {
                midfielders.add((Midfielder) player);
            } else {
                strikers.add((Striker) player);
            }
        }
    }

    private void sortListByPlayersSkills(){
        Collections.sort(gk);
        Collections.sort(defenders);
        Collections.sort(midfielders);
        Collections.sort(strikers);
    }

    private String setTactics(){
        HashMap<String, Integer> tactics = new HashMap<>();
        String bestTactics = "";
        tactics.put("442", getSumAllPlayerSkillsForChosenTactic("t442"));
        tactics.put("451", getSumAllPlayerSkillsForChosenTactic("t451"));
        tactics.put("541", getSumAllPlayerSkillsForChosenTactic("t541"));
        tactics.put("352", getSumAllPlayerSkillsForChosenTactic("t352"));
        for (Map.Entry<String, Integer> entry : tactics.entrySet()) {
            if (Objects.equals(entry.getValue(), Collections.max(tactics.values()))) {
                bestTactics = entry.getKey();
            }
        } return bestTactics;
    }

    private void setSquad(String bestTactics){
        switch (bestTactics){
            case "442"-> chosePlayerToSquad(4,4,2);
            case "451"-> chosePlayerToSquad(4,5,1);
            case "541"-> chosePlayerToSquad(5,4,1);
            case "352"-> chosePlayerToSquad(3,5,2);
        }
    }

    private void chosePlayerToSquad(int defCount, int mfdCount, int strCount){
        int gkCount = 1;
        for (int i = 0; i < gkCount; i++) {
            if (gk.get(i).canPlay) {
                squad.add(gk.get(i));
            } else if (gkCount < gk.size()) {
                gkCount++;
            } else squad.add(new Goalkeeper(30, 0, 0,10));
        }
        for (int i = 0; i < defCount; i++) {
            if (defenders.get(i).canPlay){
                squad.add(defenders.get(i));
            } else if (defCount < defenders.size()) {
                defCount++;
            }
        }
        for (int i = 0; i < mfdCount; i++) {
            if (midfielders.get(i).canPlay){
                squad.add(midfielders.get(i));
            } else if (mfdCount < midfielders.size()) {
                mfdCount++;
            }
        }
        for (int i = 0; i < strCount; i++) {
            if (strikers.get(i).canPlay){
                squad.add(strikers.get(i));
            } else if (strCount < strikers.size()) {
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
            if (!defenders.get(i).canPlay) {
                sum += defenders.get(i).getDefenceSkill();
            } else if (defendersCount< defenders.size()) defendersCount+=1;
        }
        for (int i = 0; i < midfieldersCount; i++) {
            if (!defenders.get(i).canPlay)
            sum+= midfielders.get(i).getAttackSkill() + midfielders.get(i).getDefenceSkill();
            else if (midfieldersCount < midfielders.size()) midfieldersCount += 1;
        }
        for (int i = 0; i < strikersCount; i++) {
            if (!defenders.get(i).canPlay)
            sum += strikers.get(i).getAttackSkill() + strikers.get(i).getOneOnOne();
            else if (strikersCount < strikers.size()) strikersCount +=1;
        }
        return sum;
    }

    /**
     * Helper method that finds best player with most scored goals in team
     */
    public Player getBestPlayer() {
        throw new RuntimeException("getBestPlayer method not implemented");
    }

    /**
     * CurrentPoints is a sum of wins and draws points. For each win 3 points, for draw 1 point.
     */
    public int getCurrentPoints() {
        throw new RuntimeException("getCurrentPoints method not implemented");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setChangeSquad(boolean changeSquad) {
        this.changeSquad = changeSquad;
    }
}
