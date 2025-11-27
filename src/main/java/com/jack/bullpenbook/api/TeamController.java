package com.jack.bullpenbook.api;

import com.jack.bullpenbook.dto.GameSummaryDTO;
import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.service.GameService;
import com.jack.bullpenbook.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;
    private final GameService gameService;

    // Spring injects TeamServiceImpl here
    public TeamController(TeamService teamService, GameService gameService) {
        this.teamService = teamService;
        this.gameService = gameService;
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

    // POST /api/teams
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }
}
