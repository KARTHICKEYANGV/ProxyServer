package com.example.CacheServer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CacheController {
    private final CacheService cacheService;

    @RequestMapping("/**")
    public ResponseEntity<?> forwardRequest(HttpServletRequest request) {
        // Implement the logic to forward the request to the cache server
        // and return the response.
        return new ResponseEntity<>(cacheService.fetchData(request), HttpStatus.OK);
    }

}
