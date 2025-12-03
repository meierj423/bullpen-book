package com.jack.bullpenbook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.bullpenbook.dto.TeamAdvancedStatsDTO;
import com.jack.bullpenbook.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamStatsServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private TeamStatsServiceImpl teamStatsService;

    @Test
    void getAdvancedStatsForTeam_returnsZeroStatsWhenNoGames() {
        when(gameRepository.findByHomeTeamIdOrAwayTeamId(anyLong(), anyLong()))
                .thenReturn(Collections.emptyList());

        Long teamId = 1L;

        // act
        TeamAdvancedStatsDTO stats = teamStatsService.getAdvancedStatsForTeam(teamId);

        // assert
        assertEquals(0, stats.getGames());
        assertEquals(0L, stats.getRunsScored());
        assertEquals(0L, stats.getRunsAllowed());
        assertEquals(0L, stats.getRunDifferential());
        assertEquals(0.0, stats.getExpectedWinPct());
    }
}