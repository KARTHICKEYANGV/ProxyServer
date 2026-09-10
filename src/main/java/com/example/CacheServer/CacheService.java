package com.example.CacheServer;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import jakarta.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final RestClient restClient;
    private final CacheStore cacheStore;

    @Value("${proxy.origin}")
    private String origin;

    public ResponseEntity<?> fetchData(HttpServletRequest request) {

        HttpMethod method = HttpMethod.valueOf(request.getMethod());

        String path = request.getRequestURI();
        String queryString = request.getQueryString();

        String target = origin + path +
                (queryString != null ? "?" + queryString : "");

        System.out.println("Request: " + method + " " + target);

        // Only GET requests are cached
        if (method == HttpMethod.GET) {

            CachedResponse cachedResponse = cacheStore.get(target);

            if (cachedResponse != null) {

                System.out.println("Cache HIT: " + target);

                HttpHeaders headers = new HttpHeaders();
                headers.set("X-Cache", "HIT");

                return new ResponseEntity<>(
                        cachedResponse.body(),
                        headers,
                        cachedResponse.statusCode()
                );
            }
        }

        // Cache MISS
        System.out.println("Cache MISS: " + target);

        ResponseEntity<String> response = restClient
                .method(method)
                .uri(target)
                .retrieve()
                .toEntity(String.class);

        // Store response in cache
        if (method == HttpMethod.GET) {

            CachedResponse cachedResponse = new CachedResponse(
                    response.getBody(),
                    response.getStatusCode()
            );

            cacheStore.put(target, cachedResponse);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Cache", "MISS");

        return new ResponseEntity<>(
                response.getBody(),
                headers,
                response.getStatusCode()
        );
    }

    public void clearCache() {
        cacheStore.clear();
    }

}
