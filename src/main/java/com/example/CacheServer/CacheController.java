package com.example.CacheServer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CacheController {
    private final CacheService cacheService;

    @GetMapping("/forward")
    public ResponseEntity<?> forwardRequest(String request) {
        // Implement the logic to forward the request to the cache server
        // and return the response.
        return new ResponseEntity<>(cacheService.fetchData(), HttpStatus.OK);
    }

}
