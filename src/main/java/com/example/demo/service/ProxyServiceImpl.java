package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ProxyServiceImpl implements ProxyService {
    
    private final RestTemplate restTemplate;
    private final String originUrl;
    
    public ProxyServiceImpl(RestTemplate restTemplate, @Value("${global.origin}") String originUrl) {
        this.restTemplate = restTemplate;
        this.originUrl = originUrl;
    }
    
    @Override
    public ResponseEntity<String> getProducts() {
        // Keep the existing method for backward compatibility
        return getResource("/products");
    }
    
    @Override
    public ResponseEntity<String> getResource(String path) {
        // Build the full URL by combining origin URL with the requested path
        String fullUrl = UriComponentsBuilder
            .fromHttpUrl(originUrl)
            .path(path)
            .build()
            .toUriString();
            
        // Create a GET request to the origin server
        RequestEntity<Void> request = RequestEntity
            .method(HttpMethod.GET, URI.create(fullUrl))
            .build();
            
        // Forward the request to the origin server
        return restTemplate.exchange(request, String.class);
    }
}
