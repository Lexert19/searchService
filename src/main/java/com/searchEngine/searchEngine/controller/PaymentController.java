package com.searchEngine.searchEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @GetMapping("/success")
    public String paymentSuccess(
        Model model
    ) {
        return "payment/success";
    }

    @GetMapping("/cancel")
    public String paymentCancel(
        Model model
    ) {
        return "payment/cancel";
    }
}
