package com.jack.bullpenbook.api;

import com.jack.bullpenbook.dto.PlayerRequest;
import com.jack.bullpenbook.model.Player;
import com.jack.bullpenbook.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    public Player createPlayer(@RequestBody @Valid PlayerRequest request) {
        return playerService.createPlayer(request);
    }
}
