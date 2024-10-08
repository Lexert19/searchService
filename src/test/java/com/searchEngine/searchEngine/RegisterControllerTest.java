package com.searchEngine.searchEngine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.repository.UserRepository;
import com.searchEngine.searchEngine.staticClass.RandomUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getRegister() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));

    }

    @Test
    public void postRegister() throws Exception {
        String email = RandomUtil.generateRandomString(6) + "@wp.pl";

        mockMvc.perform(post("/auth/register")
                .param("username", "username")
                .param("email", email)
                .param("birthDate", "2002-12-12")
                .param("firstName", "firstName")
                .param("lastName", "lastName")
                .param("password", "test"))
                .andExpect(status().is3xxRedirection())
                .andReturn();

        // MockHttpServletResponse response = mvcResult.getResponse();

        // String content = response.getContentAsString();
        // assertTrue(content.contains("h1"));

        Optional<User> user = userRepository.findByEmail(email);
        assertTrue(user.isPresent());
    }
}
