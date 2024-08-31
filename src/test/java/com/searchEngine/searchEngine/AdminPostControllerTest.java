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

import com.searchEngine.searchEngine.entity.Post;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.staticClass.RandomUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminPostControllerTest {
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
    public void addPost() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/en/admin/post/add")
                .param("title", RandomUtil.generateRandomString(6))
                .param("content", "content")
                .param("locale", "pl"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    public void getAddPostForm() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/en/admin/post/add"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void getEditPostForm() throws Exception {
        setupContext.setupUser();
        Post post = setupContext.setupPost(RandomUtil.generateRandomString(6));

        MvcResult mvcResult = mockMvc.perform(get("/en/admin/post/edit/" + post.getId()))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }

    @Test
    public void editPost() throws Exception {
        Post post = setupContext.setupPost(RandomUtil.generateRandomString(6));

        MvcResult mvcResult = mockMvc.perform(post("/en/admin/post/edit")
                .param("title", "Edited" + RandomUtil.generateRandomString(6))
                .param("content", "content")
                .param("id", post.getId().toString())
                .param("locale", "pl"))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }
}
