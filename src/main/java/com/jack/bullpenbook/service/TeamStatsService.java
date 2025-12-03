package com.jack.bullpenbook.service;

import com.jack.bullpenbook.dto.TeamAdvancedStatsDTO;

public interface TeamStatsService {

    TeamAdvancedStatsDTO getAdvancedStatsForTeam(Long teamId);
}