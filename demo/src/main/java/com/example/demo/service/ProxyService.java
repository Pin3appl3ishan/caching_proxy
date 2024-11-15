package com.example.demo.service;

// this class is to forward http to origin server
// cache the response
// add appropriate headers (X-Cache: HIT or X-Cache: MISS)

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProxyService {
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public String handleRequest(String url) {
        if (cache.containsKey(url)) {
            return "Cached Response: " + cache.get(url) + " (X-Cache: HIT)";
        }

        String response = forwardRequest(url);
        cache.put(url, response);
        return "Origin Response: " + response + " (X-Cache: MISS)";
    }

    public String clearCache() {
        cache.clear();
        return "Cache cleared successfully.";
    }

    private String forwardRequest(String url) {
        return "Response from origin server for URL: " + url;
    }
}
