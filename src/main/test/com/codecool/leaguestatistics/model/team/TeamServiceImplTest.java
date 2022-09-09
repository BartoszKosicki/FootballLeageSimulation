package com.codecool.leaguestatistics.model.team;

import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.players.Playable;
import com.codecool.leaguestatistics.model.players.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamServiceImplTest {

    TeamService teamService = new TeamServiceImpl();

    @Test
    void checkPlayerStatus_playerChangeStatusAndCanPlayNextGame_shouldReturnTrue() {
        //Arrange
        List<Player> players = List.of(Striker.builder().build(), Goalkeeper.builder().build(), Defender.builder().build(),
                Midfielder.builder().build(), Striker.builder().build());

        Team team = new Team(players, "Huragan", Division.East);
        team.getAllPlayers().get(0).setTimeCantPlay(new Playable(1));
        //Act

        boolean sut = teamService.checkPlayerStatus(team);
        //Assert
        assertTrue(sut);
    }
}