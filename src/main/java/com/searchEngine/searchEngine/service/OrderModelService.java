
package com.searchEngine.searchEngine.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.entity.Order;
import com.searchEngine.searchEngine.entity.Product;
import com.searchEngine.searchEngine.entity.User;
import com.searchEngine.searchEngine.model.OrderModel;
import com.searchEngine.searchEngine.repository.ProductRepository;

@Service
public class OrderModelService {
    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(OrderModel orderModel, User user) throws Exception{
        Order order = new Order();
        List<Product> products = productRepository.findAllById(orderModel.getProductsId());
        if(products.isEmpty())
            throw new Exception("Product doesn't exist!");
        
        double totalCost = 0;
        for(Product product : products){
            totalCost += product.getCost();
        }
        order.setCost(totalCost);
        order.setUser(user);
        order.setProducts(products);
        

        return order;
    }

}
