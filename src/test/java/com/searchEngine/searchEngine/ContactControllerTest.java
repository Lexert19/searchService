package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getContact() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void postContact() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/contact")
                .param("email", "dominikch19@gmail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "true")
                .param("privacyPolicty", "true"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void contactNoneExistentEmail() throws Exception {
        String email = generateRandomString(6) + "@wp.pl";

        MvcResult mvcResult = mockMvc.perform(post("/contact")
                .param("email", email)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "true")
                .param("privacyPolicty", "true"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void contactTermAndConditionsFalse() throws Exception {
        String email = generateRandomString(6) + "@wp.pl";

        mockMvc.perform(post("/contact")
                .param("email", email)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "false")
                .param("privacyPolicty", "true"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void contactPrivacyPolicyFalse() throws Exception {
        String email = generateRandomString(6) + "@wp.pl";

        mockMvc.perform(post("/contact")
                .param("email", email)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "true")
                .param("privacyPolicty", "false"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private static String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }
}
