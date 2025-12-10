package com.jack.bullpenbook.api;

import com.jack.bullpenbook.dto.StandingsDTO;
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
    public List<StandingsDTO> getStandings() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream()
                .map(this::toDto)
                // sort by win% desc, then wins desc
                .sorted(Comparator
                        .comparingDouble(StandingsDTO::getWinPercentage).reversed()
                        .thenComparingInt(StandingsDTO::getWins).reversed())
                .toList();
    }

    private StandingsDTO toDto(Team team) {
        StandingsDTO dto = new StandingsDTO()
                .setTeamId(team.getId())
                .setTeamName(team.getName())
                .setCity(team.getCity())
                .setWins(team.getWins())
                .setLosses(team.getLosses())
                .setGamesPlayed(team.getGamesPlayed());

        int gp = team.getGamesPlayed();
        double pct = (gp == 0) ? 0.0 : (double) team.getWins() / gp;
        dto.setWinPercentage(pct);

        return dto;
    }
}