package com.jack.bullpenbook.service;

import com.jack.bullpenbook.model.Team;

import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team createTeam(Team team);
}
