package com.jack.bullpenbook.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "message", "Welcome to the Bullpen Book Baseball API",
                "endpoints", List.of(
                        "GET  /api/teams",
                        "POST /api/teams",
                        "GET  /api/players",
                        "POST /api/players",
                        "GET  /api/games",
                        "POST /api/games",
                        "GET  /api/standings",
                        "GET  /api/teams/{teamId}/games",
                        "GET  /api/teams/{teamId}/advanced-stats"
                ),
                "github", "https://github.com/meierj423/bullpen-book"
        );
    }
}