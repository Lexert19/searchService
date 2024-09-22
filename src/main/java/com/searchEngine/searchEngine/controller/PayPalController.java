package com.searchEngine.searchEngine.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paypal.orders.OrderRequest;
import com.searchEngine.searchEngine.model.PayOrderModel;
import com.searchEngine.searchEngine.repository.OrderRepository;
import com.searchEngine.searchEngine.service.OrderService;
import com.searchEngine.searchEngine.service.PayPalService;

@Controller
@RequestMapping("/payment/paypal")
public class PayPalController {
   
    @Autowired
    private PayPalService payPalService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

   
    

    @PostMapping("/createOrder")
    public String createOrder(
        @ModelAttribute PayOrderModel orderModel,
        Model model
    ) throws IOException {
        com.searchEngine.searchEngine.entity.Order order = orderRepository.findById(orderModel.getOrderId()).get();


        OrderRequest orderRequest = payPalService.buildRequestBody(orderService.getOrderAmount(order));
        String link = payPalService.executeOrder(orderRequest);

        model.addAttribute("link", link);
        return "payment/order";
    }
}
