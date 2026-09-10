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
        System.out.println("Received request: " + request.getMethod() + " " + request.getRequestURI());
        // Implement the logic to forward the request to the cache server
        // and return the response.
        return cacheService.fetchData(request);
    }


    @GetMapping("/clear-cache")
    public ResponseEntity<String> clearCache() {
        cacheService.clearCache();
        return ResponseEntity.ok("Cache cleared");
    }



}
