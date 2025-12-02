# Bullpen Book

A small baseball stats backend built with Java and Spring Boot.

## Tech Stack

- Java 17
- Spring Boot (Web, Data JPA, Validation)
- PostgreSQL
- Maven
- JPA/Hibernate ORM
- RESTful APIs with DTOs
- JUnit + Mockito for unit tests

## Domain Model

- **Team** – name, city, wins, losses, gamesPlayed
- **Player** – name, position, belongs to a Team
- **Game** – date, home team, away team, scores

## Features

- Create and list teams:
    - `GET /api/teams`
    - `POST /api/teams`

- Create and list players (with team assignment via DTO):
    - `GET /api/players`
    - `POST /api/players` (`PlayerRequest`)

- Record games between teams (using `GameRequest` DTO):
    - `GET /api/games`
    - `POST /api/games`

- View team standings (wins/losses, win%):
    - `GET /api/standings`

- View a team’s schedule / games:
    - `GET /api/teams/{teamId}/games`

## Validation & Error Handling

- DTO validation using Jakarta Bean Validation (`@NotNull`, `@NotBlank`, `@Min`)
- Global error handler (`@RestControllerAdvice`) returning clean JSON for:
    - Validation errors
    - Missing entities (e.g., unknown teamId)

## Tests

- `GameServiceImplTest` verifies that:
    - Games are created using DTO input
    - Standings (wins/losses/gamesPlayed) are updated correctly
