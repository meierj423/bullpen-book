package com.jack.bullpenbook.service;

import com.jack.bullpenbook.model.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();

    Game createGame(Game game);
}