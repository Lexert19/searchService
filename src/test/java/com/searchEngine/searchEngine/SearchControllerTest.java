package com.searchEngine.searchEngine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.searchEngine.searchEngine.component.SearchService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class SearchControllerTest {
    @Autowired
    MockMvc mockMvc;

    private SearchService searchService;

    @BeforeEach
    public void setUp() throws IOException {
        this.searchService = new SearchService("example.com");
    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void testSearchDocuments() throws Exception {
        searchService.indexDocument("test", "test");

        mockMvc.perform(get("/engine/search")
                .param("query", "test")
                .param("domain", "example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("test"))
                .andExpect(jsonPath("$[0].content").value("test"));

    }

    @Test
    @WithMockUser(username = "user", roles = { "USER" })
    public void searchExample() throws Exception {
        searchService.indexDocument("example", "example");

        String result = mockMvc.perform(get("/engine/search")
                .param("query", "example")
                .param("domain", "example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("example")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(result);

    }
}
