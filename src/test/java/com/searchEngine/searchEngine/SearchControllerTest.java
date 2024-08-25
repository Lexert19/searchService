package com.searchEngine.searchEngine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchEngine.searchEngine.component.SearchService;
import com.searchEngine.searchEngine.controller.SearchController;
import com.searchEngine.searchEngine.model.SearchResult;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SearchControllerTest {
    @Autowired
    private SearchController searchController;

    private SearchService searchService;

    @BeforeEach
    public void setUp() throws IOException {
        this.searchService = new SearchService("example.com");

        UserDetails userDetails = User
                .withUsername("test")
                .password("test")
                .roles("USER")
                .build();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void testSearchDocuments() throws Exception {
        searchService.indexDocument("test", "test");

        ResponseEntity<List<SearchResult>> result = searchController.searchDocuments("test", "example.com");

        assertEquals(200, result.getStatusCode().value());
        assertTrue(result.getBody().get(0).getTitle().equals("test"));
        assertTrue(result.getBody().get(0).getContent().equals("test"));

    }

    @Test
    public void searchExample() throws Exception {
        searchService.indexDocument("example", "example");

        ResponseEntity<List<SearchResult>> result = searchController.searchDocuments("example", "example.com");
        assertEquals(200, result.getStatusCode().value());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(result.getBody());
        assertTrue(jsonString.contains("example"));

    }

    @Test
    public void searchApache() throws Exception {
        String text = "flood";

        ResponseEntity<List<SearchResult>> result = searchController.searchDocuments(text, "httpd.apache.org");
        assertEquals(200, result.getStatusCode().value());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(result.getBody());
        assertTrue(jsonString.contains(text));

    }

}
