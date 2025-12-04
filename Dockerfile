# ---------- BUILD STAGE ----------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom and download dependencies first (better caching)
COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

# Copy source
COPY src ./src
COPY cpp ./cpp

# Make sure build tools exist for C++ (g++)
RUN apt-get update && apt-get install -y build-essential && rm -rf /var/lib/apt/lists/*

# Build Java app (skip tests if you want faster builds)
RUN mvn -q -e -B package -DskipTests

# Build C++ stats engine
WORKDIR /app/cpp
RUN g++ -std=c++17 -O2 -o team_stats team_stats.cpp

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy Spring Boot fat jar from build stage
COPY --from=build /app/target/bullpen-book-0.0.1-SNAPSHOT.jar app.jar

# Copy C++ binary
COPY --from=build /app/cpp/team_stats ./cpp/team_stats

# Make sure it's executable
RUN chmod +x ./cpp/team_stats

# Expose app port
EXPOSE 8080

# Environment vars (override in k8s/compose)
ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]