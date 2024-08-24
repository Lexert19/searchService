package com.searchEngine.searchEngine.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.ApiKey;
import com.searchEngine.searchEngine.repository.ApiKeyRepository;

@Service
public class ApiKeyService {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public boolean apiKeyExists(String apiKey) {
        return apiKeyRepository.findByKey(apiKey).isPresent();
    }

    public ApiKey createApiKey() {
        String key = this.randomKey(30);
        ApiKey apiKey = new ApiKey(key);
        apiKeyRepository.save(apiKey);
        return apiKey;
    }

    private String randomKey(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        String key = "";

        for (int i = 0; i < length; i++) {
            key += characters.charAt(random.nextInt(characters.length()));
        }

        return key;
    }
}
