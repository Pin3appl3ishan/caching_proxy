package com.example.demo.controller;

import com.example.demo.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final ProxyService proxyService;

    //Spring uses constructor injection to inject an instance of ProxyService into this controller.
    @Autowired
    public Controller(final ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping("/products")
    @Cacheable(cacheNames = "cache1", cacheResolver = "cacheResolver", key = "#root.methodName")
    public ResponseEntity<String> products() {
        return proxyService.getProducts();
    }
    
    @GetMapping("/{path:.*}")
    @Cacheable(cacheNames = "cache1", cacheResolver = "cacheResolver", key = "#path")
    public ResponseEntity<String> proxyRequest(@PathVariable String path) {
        return proxyService.getResource("/" + path);
    }

}
