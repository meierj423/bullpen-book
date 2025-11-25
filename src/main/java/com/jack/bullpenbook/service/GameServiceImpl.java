package com.jack.bullpenbook.service;

import com.jack.bullpenbook.model.Game;
import com.jack.bullpenbook.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }
}