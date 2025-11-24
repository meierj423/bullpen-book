package com.jack.bullpenbook.api;

import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    // Spring injects TeamServiceImpl here
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // GET /api/teams
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    // POST /api/teams
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    // Optional: keep a sample endpoint if you like
    @GetMapping("/sample")
    public List<Team> sampleTeams() {
        return List.of(
                new Team(999L, "Sample A", "Sample City A"),
                new Team(1000L, "Sample B", "Sample City B")
        );
    }
}
