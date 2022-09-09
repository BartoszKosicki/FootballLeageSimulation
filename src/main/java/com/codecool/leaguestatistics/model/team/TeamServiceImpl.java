package com.codecool.leaguestatistics.model.team;

import com.codecool.leaguestatistics.Utils;
import com.codecool.leaguestatistics.model.players.Position;
import com.codecool.leaguestatistics.model.players.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TeamServiceImpl implements TeamService{
    @Override
    public void resetStatistics(Team team) {
        team.setWins(0);
        team.setDraws(0);
        team.setLoses(0);
        team.setPlayerOff(0);
    }

    @Override
    public Player searchForShotPlayer(Team team) {
        boolean isStriker = Utils.isMatchAction(65, 35);
        if (isStriker){
            if (team.getCurrentStrikers().size() != 1) {
                return team.getCurrentStrikers().get(Utils.getRandomValue(0, team.getCurrentStrikers().size() - 1));
            } else {
                return team.getCurrentStrikers().get(0);
            }
        } else {
            return team.getMidfielders().get(Utils.getRandomValue(0, team.getMidfielders().size()-1));
        }
    }

    @Override
    public void prepareToMatch(Team team) {
        boolean tacticShouldBeChanged = checkPlayerStatus(team);
        if(tacticShouldBeChanged){
            List<Player> squad = setTeamSquad(team);
            team.setSquad(squad);
            int defPotential = getTeamDefenderPotential(team);
            int offensivePotential = getTeamOffensivePotential(team);
            team.setDefencePotential(defPotential);
            team.setAttackPotential(offensivePotential);
        }
    }
    public boolean checkPlayerStatus(Team team){
        int changes=0;
        if (team.isChangeSquad()){
            for (Player player: team.getAllPlayers()) {
                if (!player.isCanPlay() && player.getTimeCantPlay().getCounter() > 0) {
                    player.getTimeCantPlay().decreaseCounter();
                } else {
                    player.setCanPlay(true);
                    changes++;
                }
            }
        }
        return changes > 0;
    }

    @Override
    public int getTeamDefenderPotential(Team team) {
        return team.getSquad().stream()
                .filter(player -> player instanceof DefenderPotential)
                .map(player -> (DefenderPotential) player)
                .map(DefenderPotential::getDefSkill)
                .reduce(Integer::sum).get();

    }

    @Override
    public int getTeamOffensivePotential(Team team) {
        return team.getSquad().stream()
                .filter(player -> player instanceof AttackerPotential)
                .map(player -> (AttackerPotential)player)
                .map(AttackerPotential::getAttackPotential)
                .reduce(Integer::sum).get();
    }

    /**
     * pick the best players for the most favourable tactic;
     */
    private List<Player> setTeamSquad(Team team){
        String bestTactics = setTactics(team);
        switch (bestTactics){
            case "442"-> chosePlayersToSquad(4,4,2, team);
            case "451"-> chosePlayersToSquad(4,5,1, team);
            case "541"-> chosePlayersToSquad(5,4,1, team);
            default-> chosePlayersToSquad(3,5,2, team);
        }
        return null;
    }

    /**
     * sum team potential for each tactic and set best one to the next game;
     * @return
     */
    private String setTactics(Team team){
        HashMap<String, Integer> tactics = new HashMap<>();
        tactics.put("442", sumPlayersPotential(4,4,2, team));
        tactics.put("451", sumPlayersPotential(4,5,1, team));
        tactics.put("541", sumPlayersPotential(5,4,1, team));
        tactics.put("352", sumPlayersPotential(3,5,2, team));
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : tactics.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        team.setCurrentTactic(maxEntry.getKey());
        return maxEntry.getKey();
    }

    /**
     * sum player potential
     * @param defendersCount count of defenders in tactic;
     * @param midfieldersCount count of midfielders in tactic;
     * @param strikersCount count of strikers in tactic;
     * @return sum players potential in specific tactic;
     */
    private int sumPlayersPotential(int defendersCount, int midfieldersCount, int strikersCount, Team team){
        int sum = 0;
        sum = sumDefendersSkills(defendersCount, midfieldersCount, team, sum);
        sum = sumMidfieldersSkills(midfieldersCount, team, sum);
        sum = getStrikersSkills(strikersCount, team, sum);
        return sum;
    }

    private int getStrikersSkills(int strikersCount, Team team, int sum) {
        if(strikersCount <= team.getStrikers().size()) {
            for (int i = 0; i < strikersCount; i++) {
                if (team.getStrikers().get(i).isCanPlay())
                    sum += team.getStrikers().get(i).getAttackPotential();
                else if (strikersCount < team.getStrikers().size()) {
                    strikersCount += 1;
                }
            }
        }
        return sum;
    }

    private int sumMidfieldersSkills(int midfieldersCount, Team team, int sum) {
        if(midfieldersCount <= team.getMidfielders().size()) {
            for (int i = 0; i < midfieldersCount; i++) {
                if (team.getMidfielders().get(i).isCanPlay()) {
                    sum += team.getMidfielders().get(i).getAttackSkill() + team.getMidfielders().get(i).getDefenceSkill();
                } else if (midfieldersCount < team.getMidfielders().size()) {
                    midfieldersCount += 1;
                }
            }
        }
        return sum;
    }

    private int sumDefendersSkills(int defendersCount, int midfieldersCount, Team team, int sum) {
        if (defendersCount <= team.getDefenders().size()) {
            for (int i = 0; i < defendersCount; i++) {
                if (team.getDefenders().get(i).isCanPlay()) {
                    sum += team.getDefenders().get(i).getDefSkill();
                } else if (midfieldersCount < team.getDefenders().size()) {
                    defendersCount += 1;
                }
            }
        }
        return sum;
    }

    /**
     * auxiliary function for setting squad to the next match
     * @param defCount count defenders in picked tactics;
     * @param mfdCount count midfielders in picked tactics;
     * @param strCount count strikers in picked tactics;
     */
    private List<Player> chosePlayersToSquad(int defCount, int mfdCount, int strCount, Team team){
        List<Player> players = new ArrayList<>();
        Goalkeeper gk = getTheBestGkWhoCanPlay(team);
        List<Defender> theBestDefendersWhoCanPlay = getTheBestDefendersWhoCanPlay(defCount, team);
        List<Midfielder> theBestMidfieldersWhoCanPlay = getTheBestMidfieldersWhoCanPlay(mfdCount, team);
        List<Striker> theBestStrikersWhoCanPlay = getTheBestStrikersWhoCanPlay(strCount, team);
        players.add(gk);
        players.addAll(theBestDefendersWhoCanPlay);
        players.addAll(theBestMidfieldersWhoCanPlay);
        players.addAll(theBestStrikersWhoCanPlay);
        return players;
    }

    private List<Striker> getTheBestStrikersWhoCanPlay(int strCount, Team team) {
        List<Striker> strikers = new ArrayList<>();
        for (int i = 0; i < strCount; i++) {
            if (team.getStrikers().get(i).isCanPlay()){
                strikers.add(team.getStrikers().get(i));
            } else if (strCount < team.getStrikers().size()) {
                strCount++;
            }
        }
        return strikers;
    }

    private List<Midfielder> getTheBestMidfieldersWhoCanPlay(int mfdCount, Team team) {
        List<Midfielder> midfielders = new ArrayList<>();
        for (int i = 0; i < mfdCount; i++) {
            if (team.getMidfielders().get(i).isCanPlay()){
                midfielders.add(team.getMidfielders().get(i));
            } else if (mfdCount < team.getMidfielders().size()) {
                mfdCount++;
            }
        }
        return midfielders;
    }

    private List<Defender> getTheBestDefendersWhoCanPlay(int defCount, Team team) {
        List<Defender> defenders = new ArrayList<>();
        for (int i = 0; i < defCount; i++) {
            if (team.getDefenders().get(i).isCanPlay()){
                defenders.add(team.getDefenders().get(i));
            } else if (defCount < team.getDefenders().size()) {
                defCount++;
            }
        }
        return defenders;
    }

    private Goalkeeper getTheBestGkWhoCanPlay(Team team) {
        int gkCount = 1;
        for (int i = 0; i < gkCount; i++) {
            if (team.getGoalkeepers().get(i).isCanPlay()) {
                return team.getGoalkeepers().get(i);
            } else if (gkCount < team.getGoalkeepers().size()) {
                gkCount++;
            }
        }
        return new Goalkeeper(30, 0, 0, 10, 20, team.getName(), Position.GOALKEEPER);
    }

}
