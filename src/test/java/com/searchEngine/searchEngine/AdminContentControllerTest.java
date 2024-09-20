package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.searchEngine.searchEngine.entity.User;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SetupContext setupContext;

    @BeforeEach
    public void setUp() throws Exception {
        User userDetails = setupContext.setupAdminUser();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void testNotFoundGetFields() throws Exception {
        mockMvc.perform(get("/admin/content/fields/en/test"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testGetFields() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/admin/content/fields/pl/test"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
        assertTrue(content.contains("test"));

    }

    @Test
    public void testPostFields() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/admin/content/fields/pl/test")
                .param("test", "test_value")
                .param("main_header", "hellow"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
        assertTrue(content.contains("hellow"));
    }
}
