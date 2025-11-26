package com.jack.bullpenbook.api;

import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.repository.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/standings")
public class StandingsController {

    private final TeamRepository teamRepository;

    public StandingsController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Team> getStandings() {
        List<Team> teams = teamRepository.findAll();

        teams.sort(Comparator.comparingInt(Team::getWins).reversed());

        return teams;
    }
}