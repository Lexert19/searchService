package com.searchEngine.searchEngine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.Order;
import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.AddProductModel;
import com.searchEngine.searchEngine.model.OrderModel;
import com.searchEngine.searchEngine.model.PayOrderModel;
import com.searchEngine.searchEngine.repository.OrderRepository;
import com.searchEngine.searchEngine.repository.ProductRepository;


@Service
public class OrderService {
    @Autowired
    private OrderModelService orderModelService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    
    public Order addOrder(OrderModel orderModel, User user) throws Exception{
        Order order = orderModelService.createOrder(orderModel, user);

        orderRepository.saveAndFlush(order);

        return order;
    }

    public void addProduct(Product product, Order order, User user) throws Exception{
        if(order.getUser().equals(user)){
            order.getProducts().add(product);

        } else{
            throw new Exception("brak uprawnien do zamowienia");
        }

    }

    public void addProduct(AddProductModel addProductModel, User user) throws Exception{
        Optional<Product> product = productRepository.findById(addProductModel.getProductId());
        Optional<Order> order = orderRepository.findById(addProductModel.getOrderId());

        addProduct(product.get(), order.get(), user);
    }

    public void removeProduct(Product product, Order order, User user) throws Exception{
        if(!order.getUser().equals(user))
            throw new Exception("brak uprawnien do zamowineia");

        order.getProducts().remove(product);

        orderRepository.save(order);
    }

    public Order payOrder(Order order, User user) throws Exception{
        if(order.getProducts().size() == 0)
            throw new Exception("empty order");
        if(!order.getUser().equals(user))
            throw new Exception("brak uprawnien do zamowienia");
        order.setPayed(true);

        return order;
    }

    public Order payOrder(PayOrderModel payOrderModel, User user) throws Exception{
        Optional<Order> order = orderRepository.findById(payOrderModel.getOrderId());
        return payOrder(order.get(), user);

    }
}
