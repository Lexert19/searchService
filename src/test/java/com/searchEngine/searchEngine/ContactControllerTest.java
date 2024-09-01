package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.searchEngine.searchEngine.staticClass.RandomUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getContact() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/en/contact"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void postContact() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/en/contact")
                .param("email", "dominikch19@gmail.com")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "true")
                .param("privacyPolicty", "true")
                .param("g-recaptcha-response", ""))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void contactNoneExistentEmail() throws Exception {
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";

        MvcResult mvcResult = mockMvc.perform(post("/en/contact")
                .param("email", email)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "true")
                .param("privacyPolicty", "true")
                .param("g-recaptcha-response", ""))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void contactTermAndConditionsFalse() throws Exception {
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";

        mockMvc.perform(post("/en/contact")
                .param("email", email)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "false")
                .param("privacyPolicty", "true")
                .param("g-recaptcha-response", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void contactPrivacyPolicyFalse() throws Exception {
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";

        mockMvc.perform(post("/en/contact")
                .param("email", email)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("content", "content")
                .param("termsAndConditions", "true")
                .param("privacyPolicty", "false")
                .param("g-recaptcha-response", ""))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    
}
