package com.jack.bullpenbook.api;

import com.jack.bullpenbook.model.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    @GetMapping("/api/teams/sample")
    public List<Team> sampleTeams() {
        return List.of(
                new Team(1L, "Mariners", "Seattle"),
                new Team(2L, "Dodgers", "Los Angeles")
        );
    }
}
