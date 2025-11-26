package com.jack.bullpenbook.service;

import com.jack.bullpenbook.model.Game;
import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.repository.GameRepository;
import com.jack.bullpenbook.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameServiceImpl(GameRepository gameRepository,
                           TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game createGame(Game game) {
        // 1) Extract IDs from the incoming Game object
        Long homeId = game.getHomeTeam().getId();
        Long awayId = game.getAwayTeam().getId();

        // 2) Fetch the Team entities from the DB
        Team home = teamRepository.findById(homeId)
                .orElseThrow(() -> new IllegalArgumentException("Home team not found: " + homeId));

        Team away = teamRepository.findById(awayId)
                .orElseThrow(() -> new IllegalArgumentException("Away team not found: " + awayId));

        // 3) Attach managed Team entities to the Game
        game.setHomeTeam(home);
        game.setAwayTeam(away);

        // 4) Save the Game
        Game saved = gameRepository.save(game);

        // 5) Update stats using the fully-populated Team entities
        home.setGamesPlayed(home.getGamesPlayed() + 1);
        away.setGamesPlayed(away.getGamesPlayed() + 1);

        if (saved.getHomeTeamScore() > saved.getAwayTeamScore()) {
            home.setWins(home.getWins() + 1);
            away.setLosses(away.getLosses() + 1);
        } else if (saved.getHomeTeamScore() < saved.getAwayTeamScore()) {
            away.setWins(away.getWins() + 1);
            home.setLosses(home.getLosses() + 1);
        }

        // 6) Persist the updated teams
        teamRepository.save(home);
        teamRepository.save(away);

        return saved;
    }
}