package com.jack.bullpenbook.api;

import com.jack.bullpenbook.dto.GameSummaryDTO;
import com.jack.bullpenbook.dto.TeamAdvancedStatsDTO;
import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.service.GameService;
import com.jack.bullpenbook.service.TeamService;
import com.jack.bullpenbook.service.TeamStatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    private final GameService gameService;
    private final TeamStatsService teamStatsService;

    public TeamController(TeamService teamService, GameService gameService, TeamStatsService teamStatsService) {
        this.teamService = teamService;
        this.gameService = gameService;
        this.teamStatsService = teamStatsService;
    }

    // GET /api/teams
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{teamId}/games")
    public List<GameSummaryDTO> getGamesForTeam(@PathVariable Long teamId) {
        return gameService.getGamesForTeam(teamId);
    }

    @GetMapping("/{teamId}/advanced-stats")
    public TeamAdvancedStatsDTO getAdvancedStats(@PathVariable Long teamId) {
        return teamStatsService.getAdvancedStatsForTeam(teamId);
    }

    // POST /api/teams
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }
}