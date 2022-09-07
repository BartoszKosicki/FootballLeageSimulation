package com.codecool.leaguestatistics.factory;

import com.codecool.leaguestatistics.footballLeague.League;
import com.codecool.leaguestatistics.model.Division;
import com.codecool.leaguestatistics.model.team.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeagueFactoryTest {

    LeagueFactory leagueFactory = new LeagueFactory();

    @Test
    void createLeague_testSizeLeague_shouldGenerateAListOfTeamsOfTheGivenSize() {
        //Arrange
        int expectedResult = 10;

        //Act
        int sut = leagueFactory.createLeague(10, Division.East).getTeams().size();
        //Assert

        assertEquals(expectedResult, sut);
    }

    @Test
    void createLeague_testIfThrowIllegalArgumentExceptionWhenTeamsInDivisionIsNegative_shouldGenerateAListOfTeamsOfTheGivenSize() {
        //Assert
        assertThrows(IllegalArgumentException.class, ()-> leagueFactory.createLeague(-10, Division.East));
        assertThrows(IllegalArgumentException.class, ()-> leagueFactory.createLeague(30, Division.East));
        assertThrows(IllegalArgumentException.class, ()-> leagueFactory.createLeague(0, Division.East));
    }
    @Test
    void createLeague_testIfThrowIllegalArgumentExceptionWhenTeamsInDivisionIsZero_shouldGenerateAListOfTeamsOfTheGivenSize() {
        //Assert
        assertThrows(IllegalArgumentException.class, ()-> leagueFactory.createLeague(0, Division.East));
    }
    @Test
    void createLeague_testIfThrowIllegalArgumentExceptionWhenTeamsInDivisionIsOverTheLimit_shouldGenerateAListOfTeamsOfTheGivenSize() {
        //Assert
        assertThrows(IllegalArgumentException.class, ()-> leagueFactory.createLeague(30, Division.East));
    }
}