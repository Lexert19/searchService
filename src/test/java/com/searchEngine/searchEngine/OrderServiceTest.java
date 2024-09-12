package com.searchEngine.searchEngine;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.searchEngine.searchEngine.entity.Order;
import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.OrderModel;
import com.searchEngine.searchEngine.repository.ProductRepository;
import com.searchEngine.searchEngine.service.OrderService;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private SetupContext setupContext;
    @Autowired
    private ProductRepository productRepository;
    private User userDetails;

    @BeforeEach
    public void setup() throws Exception {
        setupContext.setupProducts();

        userDetails = setupContext.setupAdminUser();
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }

    @Test
    public void testAddService() throws Exception {
        OrderModel orderModel = new OrderModel();
        List<Product> products = productRepository.findAll();

        orderModel.getProductsId().add(products.get(0).getId());
        orderModel.getProductsId().add(products.get(1).getId());
        orderModel.getProductsId().add(products.get(2).getId());

        Order order = orderService.addOrder(orderModel, userDetails);

        assertTrue(order.getProducts().size() == 3);
        assertTrue(order.getUser().getId() == userDetails.getId());

    }

    @Test
    public void testAddProduct() throws Exception {
        Order order = setupContext.setupOrder(userDetails);

        List<Product> products = setupContext.setupProducts();

        orderService.addProduct(products.get(0), order, userDetails);
        assertTrue(order.getProducts().size() == 2);
    }

    @Test
    public void testRemoveProduct() throws Exception {
        Order order = setupContext.setupOrder(userDetails);

        List<Product> products = setupContext.setupProducts();

        orderService.addProduct(products.get(0), order, userDetails);
        orderService.removeProduct(products.get(0), order, userDetails);
        assertTrue(order.getProducts().size() == 1);
    }

    @Test
    public void testPayOrder() throws Exception {
        Order order = setupContext.setupOrder(userDetails);

        List<Product> products = setupContext.setupProducts();

        orderService.addProduct(products.get(0), order, userDetails);
        orderService.payOrder(order, userDetails);

        assertTrue(order.isPayed());
    }

    @Test
    public void testPayEmptyOrder() throws Exception {
        Order order = setupContext.setupOrder(userDetails);
        orderService.removeProduct(order.getProducts().get(0), order, userDetails);

        assertThrows(Exception.class, () -> {
            orderService.payOrder(order, userDetails);

        });

        assertTrue(!order.isPayed());
    }
}
