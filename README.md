# Bullpen Book – Baseball Stats Engine (Spring Boot , C++ , Docker , K8s)

Bullpen Book is a lightweight baseball statistics backend featuring a hybrid architecture:
Spring Boot (Java) for REST APIs & persistence, and a C++ stats engine for advanced analytics.
The project includes full Docker support, and Kubernetes manifests.

---

## Tech Stack

### **Backend**

* Java 17
* Spring Boot (Web, Data JPA, Validation)
* JPA/Hibernate ORM
* PostgreSQL (prod), H2 (demo)
* Maven

### **Native Stats Engine**

* C++17
* CMake build
* Communicates via subprocess execution from Java

### **Infrastructure**

* Docker & Docker Compose
* Kubernetes manifests (`postgres.yml`, `app.yml`)
* Secrets + ConfigMaps + PersistentVolume
* NodePort exposure for local K8s cluster

### **Testing**

* JUnit 5
* Mockito
* Unit test coverage for services (e.g., TeamStatsServiceImpl)

---

## Domain Model

| Entity     | Description                           |
| ---------- | ------------------------------------- |
| **Team**   | name, city, wins, losses, gamesPlayed |
| **Player** | name, position, assigned to a Team    |
| **Game**   | date, home/away teams, scores         |

---

## REST API Endpoints

### **Teams**

* `GET /api/teams` — list all teams
* `POST /api/teams` — create a team
* `GET /api/teams/{id}/games` — view schedule
* `GET /api/teams/{id}/advanced-stats` — calls the C++ analytics engine

### **Players**

* `GET /api/players`
* `POST /api/players` — uses `PlayerRequest` DTO validation

### **Games**

* `GET /api/games`
* `POST /api/games` — uses `GameRequest` DTO

### **Standings**

* `GET /api/standings` — win/loss records, win %

---

## Advanced Stats (C++ Engine Integration)

The endpoint:

```
GET /api/teams/{teamId}/advanced-stats
```

Triggers the following workflow:

1. Java writes all game records into a temporary input file
2. Java executes the C++ binary (`team_stats`)
3. C++ parses the stats, computes rates & metrics
4. C++ prints JSON
5. Java deserializes into `TeamStatsDTO` using Jackson

---

##  Validation & Error Handling

* Jakarta Bean Validation (`@NotNull`, `@NotBlank`, `@Min`, etc.)
* Centralized exception handling using `@RestControllerAdvice`
* Clean JSON error responses for:

  * Validation errors
  * Missing resources
  * C++ engine failures

---

## Tests

### Included:

* `TeamStatsServiceImplTest`

  * Ensures C++ engine invocation logic behaves correctly
  * Validates JSON deserialization
  * Mocks filesystem interactions

### Additional tests:

* Game creation & standings updates
* DTO validation tests

---

## Docker Support

Build container:

```bash
docker build -t bullpen-book:dev .
```

Run via Docker Compose (Postgres + app):

```bash
docker-compose up
```

The app inside the container listens on **8080**.

---

## Kubernetes Deployment (Local)

Manifests included in `k8s/`:

* `postgres.yml` — Deployment, Service, PVC, Secret, ConfigMap
* `app.yml` — Spring Boot Deployment + NodePort service

Apply:

```bash
kubectl apply -f k8s/postgres.yml
kubectl apply -f k8s/app.yml
```

Access the app at:

```
http://localhost:30080/api/teams
```