package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SetupContext setupContext;
    private User userDetails;

    @BeforeEach
    public void setup() throws Exception {
        
        try{
            setupContext.setupPost("aaaaaa");

        }catch(Exception e){

        }
        // userDetails = setupContext.setupUser();
        // UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
        //         userDetails.getAuthorities());

        // SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void getPostByTitle() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/blog/aaaaaa"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

}
