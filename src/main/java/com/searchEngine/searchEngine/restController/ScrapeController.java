package com.searchEngine.searchEngine.restController;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.searchEngine.searchEngine.service.ScrapperService;

@RestController
@RequestMapping("/api/scrape")
public class ScrapeController {
    @Autowired
    private final ScrapperService scrapperService;

    public ScrapeController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @GetMapping("/website")
    public ResponseEntity<String> indexWebsite(
            @RequestParam String url,
            @RequestParam String domain) throws IOException {

        String displayedText = scrapperService.scrapePage(url, domain);
        return ResponseEntity.ok(displayedText);

    }

    @GetMapping("/domain")
    public ResponseEntity<String> indexDomain(
            @RequestParam String domain,
            @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        List<String> links = scrapperService.scrapeDomain(domain, true);

        return ResponseEntity.ok("Domain indexed successfully. Number of links: " + links.size());
    }

}
