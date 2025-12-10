package com.jack.bullpenbook.repository;

import com.jack.bullpenbook.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
