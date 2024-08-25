package com.searchEngine.searchEngine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.searchEngine.searchEngine.controller.ApiKeyController;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApiKeyControllerTest {
    @Autowired
    private ApiKeyController apiKeyController;

    @Test
    public void registerApiKey() throws Exception {

        ResponseEntity<String> result = apiKeyController.registerApiKey();
        assertEquals(200, result.getStatusCode().value());

    }
}
