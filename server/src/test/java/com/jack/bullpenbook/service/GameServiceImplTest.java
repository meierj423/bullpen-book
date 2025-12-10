package com.jack.bullpenbook.service;

import com.jack.bullpenbook.dto.GameRequest;
import com.jack.bullpenbook.model.Game;
import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.repository.GameRepository;
import com.jack.bullpenbook.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    @Test
    void createGame_updatesStandingsForHomeWin() {
        Long homeId = 1L;
        Long awayId = 2L;

        Team home = new Team();
        home.setId(homeId);
        home.setName("Cubs");
        home.setGamesPlayed(0);
        home.setWins(0);
        home.setLosses(0);

        Team away = new Team();
        away.setId(awayId);
        away.setName("Mariners");
        away.setGamesPlayed(0);
        away.setWins(0);
        away.setLosses(0);

        when(teamRepository.findById(homeId)).thenReturn(Optional.of(home));
        when(teamRepository.findById(awayId)).thenReturn(Optional.of(away));

        // mock gameRepository.save to just echo back the game with an id
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game g = invocation.getArgument(0);
            g.setId(10L);
            return g;
        });

        GameRequest request = new GameRequest();
        request.setGameDate("2025-04-01");
        request.setHomeTeamId(homeId);
        request.setAwayTeamId(awayId);
        request.setHomeTeamScore(5);
        request.setAwayTeamScore(3);

        // act
        Game result = gameService.createGame(request);

        // assert: game saved with the right teams and scores
        assertNotNull(result.getId());
        assertEquals(5, result.getHomeTeamScore());
        assertEquals(3, result.getAwayTeamScore());
        assertEquals(homeId, result.getHomeTeam().getId());
        assertEquals(awayId, result.getAwayTeam().getId());

        // assert: standings updated
        assertEquals(1, home.getGamesPlayed());
        assertEquals(1, home.getWins());
        assertEquals(0, home.getLosses());

        assertEquals(1, away.getGamesPlayed());
        assertEquals(0, away.getWins());
        assertEquals(1, away.getLosses());

        // and we saved the teams back
        verify(teamRepository, times(1)).save(home);
        verify(teamRepository, times(1)).save(away);
    }
}