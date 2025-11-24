package com.jack.bullpenbook.service;

import com.jack.bullpenbook.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TeamServiceImpl implements TeamService {

    private final List<Team> teams = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public TeamServiceImpl() {
        // seed a couple of teams so GET /api/teams returns something
        teams.add(new Team(idGenerator.getAndIncrement(), "Mariners", "Seattle"));
        teams.add(new Team(idGenerator.getAndIncrement(), "Dodgers", "Los Angeles"));
    }

    @Override
    public List<Team> getAllTeams() {
        return Collections.unmodifiableList(teams);
    }

    @Override
    public Team createTeam(Team team) {
        long id = idGenerator.getAndIncrement();
        team.setId(id);
        teams.add(team);
        return team;
    }
}
