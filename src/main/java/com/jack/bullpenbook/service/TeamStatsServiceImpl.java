package com.jack.bullpenbook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.bullpenbook.dto.TeamAdvancedStatsDTO;
import com.jack.bullpenbook.model.Game;
import com.jack.bullpenbook.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TeamStatsServiceImpl implements TeamStatsService {

    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper;

    // Path to the C++ executable relative to project root
    private static final String STATS_EXECUTABLE = "./cpp/team_stats";

    public TeamStatsServiceImpl(GameRepository gameRepository, ObjectMapper objectMapper) {
        this.gameRepository = gameRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public TeamAdvancedStatsDTO getAdvancedStatsForTeam(Long teamId) {
        // 1) Load all games for this team
        List<Game> games = gameRepository.findByHomeTeamIdOrAwayTeamId(teamId, teamId);

        if (games.isEmpty()) {
            return new TeamAdvancedStatsDTO()
                    .setGames(0)
                    .setRunsScored(0)
                    .setRunsAllowed(0)
                    .setRunDifferential(0)
                    .setExpectedWinPct(0.0);
        }

        // 2) Build the input string for C++: "teamScore oppScore" per line
        StringBuilder inputBuilder = new StringBuilder();
        for (Game game : games) {
            boolean isHome = game.getHomeTeam().getId().equals(teamId);
            int teamScore = isHome ? game.getHomeTeamScore() : game.getAwayTeamScore();
            int oppScore = isHome ? game.getAwayTeamScore() : game.getHomeTeamScore();

            inputBuilder.append(teamScore)
                    .append(' ')
                    .append(oppScore)
                    .append('\n');
        }

        String input = inputBuilder.toString();

        try {
            // 3) Start the C++ process
            ProcessBuilder pb = new ProcessBuilder(STATS_EXECUTABLE);
            Process process = pb.start();

            // 4) Write input to the process's stdin
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream(), StandardCharsets.UTF_8))) {
                writer.write(input);
                writer.flush();
            }


            // 5) Read JSON output from stdout
            String json;
            try (InputStream is = process.getInputStream()) {
                json = new String(is.readAllBytes(), StandardCharsets.UTF_8).trim();
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new IllegalStateException("C++ stats engine exited with code " + exitCode);
            }

            if (json.isEmpty()) {
                // No games case: C++ prints zeroes anyway, but just in case
                return new TeamAdvancedStatsDTO()
                        .setGames(0)
                        .setRunsScored(0)
                        .setRunsAllowed(0)
                        .setRunDifferential(0)
                        .setExpectedWinPct(0.0);
            }

            // 6) Parse JSON into DTO
            return objectMapper.readValue(json, TeamAdvancedStatsDTO.class);

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new StatsEngineException("Failed to run C++ stats engine", e);
        }
    }
}