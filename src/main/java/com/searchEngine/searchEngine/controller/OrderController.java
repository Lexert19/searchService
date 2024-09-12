package com.searchEngine.searchEngine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.AddProductModel;
import com.searchEngine.searchEngine.model.OrderModel;
import com.searchEngine.searchEngine.model.PayOrderModel;
import com.searchEngine.searchEngine.service.OrderService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/panel/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public String createOrder(
            Model model,
            @AuthenticationPrincipal User userDetails,
            @ModelAttribute OrderModel orderModel) throws Exception {
        orderService.addOrder(orderModel, userDetails);

        return "user/order";
    }

    @PostMapping("/add")
    public String addProduct(
            Model model,
            @AuthenticationPrincipal User userDetails,
            @ModelAttribute AddProductModel AddProductModel) throws Exception {

        orderService.addProduct(AddProductModel, userDetails);

        return "user/order";
    }

    @PostMapping("/pay")
    public String payOrder(
            Model model,
            @AuthenticationPrincipal User userDetails,
            @ModelAttribute PayOrderModel payOrderModel,
            HttpServletResponse response) throws Exception {
        try {
            orderService.payOrder(payOrderModel, userDetails);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return "user/order";
    }
}
