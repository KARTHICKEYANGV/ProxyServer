package com.example.CacheServer;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class CacheStore {

    private final ConcurrentHashMap<String, CachedResponse> cache =
            new ConcurrentHashMap<>();

    public CachedResponse get(String key) {
        return cache.get(key);
    }

    public void put(String key, CachedResponse response) {
        cache.put(key, response);
    }

    public void clear() {
        cache.clear();
    }
}