package com.example.CacheServer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
public class CachedResponse {
    private final String body;
    private final HttpStatusCode statusCode;

    public CachedResponse(
            String body,
            HttpStatusCode statusCode) {

        this.body = body;
        this.statusCode = statusCode;
    }

    public String body() {
        return body;
    }


    public HttpStatusCode statusCode() {
        return statusCode;
    }
}
