package com.codecool.leaguestatistics.footballLeague;

import com.codecool.leaguestatistics.model.players.model.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MatchHistory {

    private final String home;
    private final String away;
    private final List<Player> scorers;
    private final List<Player> injures;
    private final List<Player> redCardPlayers;


    public MatchHistory(String home, String away) {
        this.home = home;
        this.away = away;
        this.scorers = new ArrayList<>();
        this.injures = new ArrayList<>();
        this.redCardPlayers = new ArrayList<>();
    }

    public void addScorer(Player player){
        scorers.add(player);
    }
    public void addInjuredPlayer(Player player){
        injures.add(player);
    }
    public void addPlayerWithRedCard(Player player){
        redCardPlayers.add(player);
    }
}
