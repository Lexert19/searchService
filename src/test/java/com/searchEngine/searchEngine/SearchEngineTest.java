package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.searchEngine.searchEngine.component.SearchService;
import com.searchEngine.searchEngine.model.SearchResult;

public class SearchEngineTest {
    private SearchService searchEngine;

    @BeforeEach
    public void setUp() throws Exception {
        searchEngine = new SearchService("example.com");
    }

    @Test
    public void testIndexDocument() throws IOException {
        searchEngine.indexDocument("Test Title", "This is a test content");
    }

    @Test
    public void testSearchDocuments() throws Exception {
        searchEngine.indexDocument("Java Programming", "Java is a popular programming language");
        searchEngine.indexDocument("Python Programming", "Python is known for its simplicity");

        List<SearchResult> results = searchEngine.searchDocuments("Java");

        assertFalse(results.isEmpty());
        assertEquals("Java Programming", results.get(0).getTitle());
        assertTrue(results.get(0).getContent().contains("Java"));
    }

    @Test
    public void testSearchDocumentsNoResults() throws Exception {
        searchEngine.indexDocument("Java Programming", "Java is a popular programming language");

        List<SearchResult> results = searchEngine.searchDocuments("C++");

        assertTrue(results.isEmpty());
    }

    @Test
    public void testIndexMultipleDocuments() throws IOException {
        searchEngine.indexDocument("Title 1", "Content 1");
        searchEngine.indexDocument("Title 2", "Content 2");
        searchEngine.indexDocument("Title 3", "Content 3");
    }

    @Test
    public void testSearchDocumentsReturnsCorrectNumberOfResults() throws Exception {
        for (int i = 0; i < 15; i++) {
            searchEngine.indexDocument("Title " + i, "Content " + i);
        }

        List<SearchResult> results = searchEngine.searchDocuments("Content");

        assertEquals(10, results.size()); 
    }
}
