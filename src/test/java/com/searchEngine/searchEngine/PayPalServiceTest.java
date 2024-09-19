package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.paypal.orders.OrderRequest;
import com.searchEngine.searchEngine.entity.Order;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.service.OrderService;
import com.searchEngine.searchEngine.service.PayPalService;

@SpringBootTest
public class PayPalServiceTest {
    @Autowired
    private PayPalService payPalService;
    @Autowired
    private SetupContext setupContext;
    private User userDetails;
    @Autowired
    private OrderService orderService;

    @BeforeEach
    public void setUp() throws Exception {
        userDetails = setupContext.setupAdminUser();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void testCreateOrderAndExecute() throws Exception {
        Order order = setupContext.setupOrder(userDetails);
        double amount = orderService.getOrderAmount(order);

        OrderRequest orderRequest = payPalService.buildRequestBody(amount);
        String link = payPalService.executeOrder(orderRequest);

        assertTrue(link.length() > 1);
    }
}
