package com.searchEngine.searchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searchEngine.searchEngine.service.ApiKeyService;

@RestController
@RequestMapping("/key")
public class ApiKeyController {
    @Autowired
    private ApiKeyService apiKeyService;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerApiKey(){
        String key = apiKeyService.createApiKey().getKey();
        return ResponseEntity.ok().body(key);
    }
}
