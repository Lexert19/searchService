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

import com.searchEngine.searchEngine.restController.ScrapeController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ScrapeControllerTest {
    @Autowired
    private ScrapeController scrapeController;
    private UserDetails userDetails;

    @BeforeEach
    public void setUp() {
        userDetails = User
                .withUsername("test")
                .password("test")
                .roles("USER")
                .build();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void scrapeExampleCom() throws Exception {
        String testUrl = "https://example.com";
        String scrapedText = "Example Domain";

        ResponseEntity<String> result = scrapeController.indexWebsite(testUrl, "example.com");

        assertEquals(200, result.getStatusCode().value());
        assertTrue(result.getBody().contains(scrapedText));

    }

    @Test
    public void scrapeDomain() throws Exception {
        String domain = "example.com";

        ResponseEntity<String> result = scrapeController.indexDomain(domain, userDetails);
        assertEquals(200, result.getStatusCode().value());

    }

    //@Test
    public void scrapeHttpd_apache_org() throws Exception {
        String domain = "httpd.apache.org";

        ResponseEntity<String> result = scrapeController.indexDomain(domain, userDetails);
        assertEquals(200, result.getStatusCode().value());

        System.out.println(result);

    }

}
