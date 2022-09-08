package com.codecool.leaguestatistics.model.players;

import com.codecool.leaguestatistics.model.players.model.Player;

public class PlayerServiceImpl implements PlayerService{
    @Override
    public void resetPlayerStatistics(Player player) {
        player.setGoals(0);
        player.setCanPlay(true);
        player.setTimeCantPlay(new Playable());
    }
}
