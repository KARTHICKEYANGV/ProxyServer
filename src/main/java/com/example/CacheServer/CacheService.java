package com.example.CacheServer;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final RestClient restClient;

    @Value("${proxy.origin}")
    private String origin;





    public ResponseEntity<?> fetchData(){
        ResponseEntity<?> response = restClient
                .get()
                .uri(origin)
                .retrieve()
                .toEntity(String.class);

        return response;
    }
}
