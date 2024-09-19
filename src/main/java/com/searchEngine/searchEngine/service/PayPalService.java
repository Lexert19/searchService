package com.searchEngine.searchEngine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Money;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;

import java.io.IOException;
import java.util.Collections;

@Service
public class PayPalService {
    @Value("${paypal.returnUrl}")
    private String returnUrl;

    @Value("${paypal.cancelUrl}")
    private String cancelUrl;

    @Autowired
    private PayPalHttpClient payPalHttpClient;

    public OrderRequest buildRequestBody(double value) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("YourBrandName")
                .landingPage("BILLING")
                .cancelUrl(cancelUrl)
                .returnUrl(returnUrl);

        // Money amount = new Money().currencyCode("USD").value("100.00");
        String formattedValue = String.format("%.2f", value);

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(formattedValue));

        orderRequest.applicationContext(applicationContext);
        orderRequest.purchaseUnits(Collections.singletonList(purchaseUnitRequest));

        return orderRequest;
    }

    public String executeOrder(OrderRequest orderRequest) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();

        request.requestBody(orderRequest);

        Order payment = payPalHttpClient.execute(request).result();

        String approvalLink = payment.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No approval link found"))
                .href();
        return approvalLink;

    }
}
