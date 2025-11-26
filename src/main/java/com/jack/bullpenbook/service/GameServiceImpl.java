package com.jack.bullpenbook.service;

import com.jack.bullpenbook.dto.GameRequest;
import com.jack.bullpenbook.model.Game;
import com.jack.bullpenbook.model.Team;
import com.jack.bullpenbook.repository.GameRepository;
import com.jack.bullpenbook.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Game createGame(GameRequest request) {
        // 1) Fetch teams
        Team home = teamRepository.findById(request.getHomeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Home team not found"));

        Team away = teamRepository.findById(request.getAwayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Away team not found"));

        // 2) Convert gameDate string into LocalDate
        LocalDate date = LocalDate.parse(request.getGameDate());

        // 3) Create Game entity
        Game game = new Game()
                .setGameDate(date)
                .setHomeTeamScore(request.getHomeTeamScore())
                .setAwayTeamScore(request.getAwayTeamScore())
                .setHomeTeam(home)
                .setAwayTeam(away);

        // 4) Save the Game
        Game saved = gameRepository.save(game);

        // 5) Update games played
        home.setGamesPlayed(home.getGamesPlayed() + 1);
        away.setGamesPlayed(away.getGamesPlayed() + 1);

        // 6) Update team records
        if (saved.getHomeTeamScore() > saved.getAwayTeamScore()) {
            home.setWins(home.getWins() + 1);
            away.setLosses(away.getLosses() + 1);
        } else if (saved.getHomeTeamScore() < saved.getAwayTeamScore()) {
            away.setWins(away.getWins() + 1);
            home.setLosses(home.getLosses() + 1);
        }

        // 7) Persist the updated teams
        teamRepository.save(home);
        teamRepository.save(away);

        return saved;
    }
}