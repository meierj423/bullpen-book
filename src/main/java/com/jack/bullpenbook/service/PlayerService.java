package com.jack.bullpenbook.service;

import com.jack.bullpenbook.model.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAllPlayers();

    Player createPlayer(Player player);
}
