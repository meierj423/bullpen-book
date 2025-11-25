package com.jack.bullpenbook.repository;

import com.jack.bullpenbook.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}