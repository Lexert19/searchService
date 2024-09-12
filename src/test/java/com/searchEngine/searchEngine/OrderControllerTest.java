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
import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.service.OrderService;
import com.searchEngine.searchEngine.staticClass.RandomUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private User userDetails;
    @Autowired
    private SetupContext setupContext;
    @Autowired
    private OrderService orderService;

    @BeforeEach
    public void setUp() throws Exception {
        userDetails = setupContext.setupUser();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void testCreateOrder() throws Exception {
        List<Product> products = setupContext.setupProducts();

        MvcResult mvcResult = mockMvc.perform(post("/en/panel/order/create")
                .param("productsId[0]", products.get(0).getId().toString())
                .param("productsId[1]", products.get(1).getId().toString()))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("head"));

    }

    @Test
    public void testAddProduct() throws Exception {
        Order order = setupContext.setupOrder(userDetails);
        List<Product> products = setupContext.setupProducts();

        MvcResult mvcResult = mockMvc.perform(post("/en/panel/order/add")
                .param("productId", products.get(0).getId().toString())
                .param("orderId", order.getId().toString()))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("head"));
    }

    @Test
    public void testPayOrder() throws Exception {
        Order order = setupContext.setupOrder(userDetails);
        List<Product> products = setupContext.setupProducts();
        orderService.addProduct(products.get(0), order, userDetails);

        MvcResult mvcResult = mockMvc.perform(post("/en/panel/order/pay")
                .param("orderId", order.getId().toString()))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

        String content = response.getContentAsString();
        assertTrue(content.contains("head"));
    }

    @Test
    public void testPayOrderWithoutProduct() throws Exception {
        Order order = setupContext.setupOrder(userDetails);
        orderService.removeProduct(order.getProducts().get(0), order, userDetails);

        MvcResult mvcResult = mockMvc.perform(post("/en/panel/order/pay")
                .param("orderId", order.getId().toString()))
                .andExpect(status().is4xxClientError())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();

    }
}
