package com.searchEngine.searchEngine.restController;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchEngine.searchEngine.component.SearchService;
import com.searchEngine.searchEngine.model.SearchResult;

@RestController
@RequestMapping("/api/engine")
public class SearchController {

    @GetMapping("/search")
    public ResponseEntity<List<SearchResult>> searchDocuments(
        @RequestParam String query,
        @RequestParam String domain
        ) throws QueryNodeException, IOException{
        SearchService searchService = new SearchService(domain);
        List<SearchResult> results = searchService.searchDocuments(query);
        return ResponseEntity.ok(results);
    }
    
}
