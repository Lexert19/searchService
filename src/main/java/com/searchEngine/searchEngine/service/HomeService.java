package com.searchEngine.searchEngine.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HomeService {
    @Value("${searchService.key}")
    private String searchServiceKey;
    @Autowired
    private RestTemplate restTemplate;

    public Map<String, Object> search(String search) {
        String apiUrl = "http://localhost:8080/api/engine/search?query={search}&domain={domain}";
        Map<String, String> params = new HashMap<>();
        params.put("search", search);
        params.put("domain", "localhost");

        HttpHeaders headers = new HttpHeaders();
        headers.set("apiKey", searchServiceKey);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Map.class, params);

        Map<String, Object> results = response.getBody();

        return results;
    }
}
