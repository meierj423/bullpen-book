package com.jack.bullpenbook.api;

import com.jack.bullpenbook.dto.GameRequest;
import com.jack.bullpenbook.model.Game;
import com.jack.bullpenbook.service.GameService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @PostMapping
    public Game createGame(@RequestBody @Valid GameRequest request) {
        return gameService.createGame(request);
    }
}