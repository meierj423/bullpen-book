package com.jack.bullpenbook.repository;

import com.jack.bullpenbook.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
