package com.searchEngine.searchEngine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class ScrapeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void scrapeExampleCom() throws Exception {
        String testUrl = "https://example.com";
        String scrapedText = "Example Domain";

        mockMvc.perform(MockMvcRequestBuilders.get("/scrape/website")
                .param("url", testUrl)
                .param("domain", "example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(scrapedText)));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void scrapeDomain() throws Exception {
        String domain = "example.com";

        String result = mockMvc.perform(MockMvcRequestBuilders.get("/scrape/domain")
                .param("domain", domain))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);

    }
}
