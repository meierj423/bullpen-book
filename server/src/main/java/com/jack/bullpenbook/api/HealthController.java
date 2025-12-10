package com.jack.bullpenbook.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, String> health() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "OK");
        result.put("app", "Bullpen Book");
        return result;
    }
}
