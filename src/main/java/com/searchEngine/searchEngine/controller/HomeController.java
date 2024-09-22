package com.searchEngine.searchEngine.controller;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.searchEngine.searchEngine.component.SearchService;
import com.searchEngine.searchEngine.model.SearchResult;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "hellow");

        return "home";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("search") String search,
            Model model) throws QueryNodeException, IOException {

        SearchService searchService = new SearchService("localhost");
        List<SearchResult> results = searchService.searchDocuments(search);
        model.addAttribute("results", results);
        return "search";
    }

}
