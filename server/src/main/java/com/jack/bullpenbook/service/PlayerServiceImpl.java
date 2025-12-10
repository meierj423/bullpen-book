package com.jack.bullpenbook.service;

import com.jack.bullpenbook.dto.PlayerRequest;
import com.jack.bullpenbook.model.Player;
import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.repository.PlayerRepository;
import com.jack.bullpenbook.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player createPlayer(PlayerRequest request) {
        // 1) Fetch team
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + request.getTeamId()));

        // 2) Create player
        Player player = new Player()
                .setName(request.getName())
                .setPosition(request.getPosition())
                .setTeam(team);

        return playerRepository.save(player);
    }
}
