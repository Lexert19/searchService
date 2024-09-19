package com.searchEngine.searchEngine;

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

import com.searchEngine.searchEngine.entity.Order;
import com.searchEngine.searchEngine.entity.User;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class PayPalControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private SetupContext setupContext;
    private User userDetails;

    @BeforeEach
    public void setUp() throws Exception {
        userDetails = setupContext.setupAdminUser();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void testPayPalPayment() throws Exception {
        Order order = setupContext.setupOrder(userDetails);

        MvcResult mvcResult = mockMvc.perform(post("/en/payment/paypal/createOrder")
                .param("orderId", order.getId().toString(0)))
                .andExpect(status().isOk())
                .andReturn();

         MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("h1"));
    }
}
