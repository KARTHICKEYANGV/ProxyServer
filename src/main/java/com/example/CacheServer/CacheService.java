package com.example.CacheServer;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final RestClient restClient;

    @Value("${proxy.origin}")
    private String origin;





    public ResponseEntity<?> fetchData(HttpServletRequest request){
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        String target = origin + path + (queryString != null ? "?" + queryString : "");

        ResponseEntity<?> response = restClient
                .method(method)
                .uri(target)
                .retrieve()
                .toEntity(String.class);

        return response;
    }
}
